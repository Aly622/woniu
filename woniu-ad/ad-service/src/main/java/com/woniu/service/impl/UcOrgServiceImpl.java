package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dao.custom.OrgMapper;
import com.woniu.dto.OrgListPageResultDTO;
import com.woniu.dto.OrgTreeResultDTO;
import com.woniu.entity.UcOrg;
import com.woniu.dao.UcOrgMapper;
import com.woniu.entity.UcOrgDetail;
import com.woniu.enums.IsCurrentEnum;
import com.woniu.exception.ServiceException;
import com.woniu.response.UCResponseCode;
import com.woniu.service.UcOrgDetailService;
import com.woniu.service.UcOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.service.UcUserOrgRelationService;
import com.woniu.utils.ObjectCopier;
import com.woniu.vo.CreateOrgVO;
import com.woniu.vo.OrgListPageQueryVO;
import com.woniu.vo.UpdateOrgVO;
import groovy.util.logging.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组织架构表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UcOrgServiceImpl extends ServiceImpl<UcOrgMapper, UcOrg> implements UcOrgService {

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private UcOrgDetailService ucOrgDetailService;

    @Autowired
    private UcUserOrgRelationService ucUserOrgRelationService;

    @Override
    public OrgTreeResultDTO getOrgTree() {
        OrgTreeResultDTO orgTreeResult = null;
        Long parentId = null;
        List<UcOrg> ucOrgList = orgMapper.selectOrgListForOrgTree();
        for(UcOrg ucOrg : ucOrgList) {
            if(ucOrg.getParentId() == 0) {
                orgTreeResult = packageOrgTreeDTO(ucOrg);
                parentId = ucOrg.getId();
                break;
            }
        }
        if(parentId != null) {
            List<OrgTreeResultDTO> orgTreeResultList = getOrgTree(parentId, ucOrgList);
            orgTreeResult.setChildOrgList(orgTreeResultList);
        }
        return orgTreeResult;
    }

    @Override
    public OrgTreeResultDTO getAbleOrgTree() {
        OrgTreeResultDTO orgTreeResult = null;
        Long parentId = null;
        List<UcOrg> ucOrgList = orgMapper.selectAbleOrgListForOrgTree();
        for(UcOrg ucOrg : ucOrgList) {
            if(ucOrg.getParentId() == 0) {
                orgTreeResult = packageOrgTreeDTO(ucOrg);
                parentId = ucOrg.getId();
                break;
            }
        }
        if(parentId != null) {
            List<OrgTreeResultDTO> orgTreeResultList = getOrgTree(parentId, ucOrgList);
            orgTreeResult.setChildOrgList(orgTreeResultList);
        }
        return orgTreeResult;
    }

    @Override
    public Page<OrgListPageResultDTO> getOrgListPage(OrgListPageQueryVO orgListPageQuery) {
        List<Long> childOrgLIdList = new ArrayList<>();

        List<UcOrg> ucOrgList = orgMapper.selectOrgListForOrgTree();
        if(orgListPageQuery.getParentId() == null) {
            childOrgLIdList = ucOrgList.stream().map(ucOrg -> ucOrg.getId()).collect(Collectors.toList());
        } else {
            childOrgLIdList.add(orgListPageQuery.getParentId());
            recursionGetChildOrgIdList(ucOrgList, orgListPageQuery.getParentId(), childOrgLIdList);
        }
        orgListPageQuery.setOrgIdList(childOrgLIdList);
        return orgMapper.selectOrgListPageResult(orgListPageQuery);
    }

    private static List<Long> recursionGetChildOrgIdList(List<UcOrg> ucOrgList, Long id, List<Long>  childOrgLIdList) {
        for(UcOrg ucOrg : ucOrgList) {
            if(ucOrg.getParentId().equals(id)) {
                recursionGetChildOrgIdList(ucOrgList, ucOrg.getId(), childOrgLIdList);
                childOrgLIdList.add(ucOrg.getId());
            }
        }
        return childOrgLIdList;
    }

    /**
     * @param orgId
     * @param ucOrgList
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.OrgTreeResultDTO>
     * @desc: 递归获取组织架构数
     * @date: 2022/2/25 15:39
     */
    private List<OrgTreeResultDTO> getOrgTree(Long orgId, List<UcOrg> ucOrgList) {
        List<OrgTreeResultDTO> childOrgList = new ArrayList<>();
        for (UcOrg ucOrg : ucOrgList) {
            if (orgId.equals(ucOrg.getParentId())) {
                childOrgList.add(packageOrgTreeDTO(ucOrg));
            }
        }
        for (OrgTreeResultDTO childOrg : childOrgList) {
            childOrg.setChildOrgList(getOrgTree(childOrg.getId(), ucOrgList));
        }

        if (CollectionUtils.isEmpty(childOrgList)) {
            return null;
        }
        return childOrgList;
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED)
    public Boolean createOrg(CreateOrgVO createOrg) {
        Integer orgCount = orgMapper.selectOrgCountByName(createOrg.getName());
        if(orgCount > 0) {
            throw new ServiceException(UCResponseCode.ORG_NAME_IS_EXIST);
        }
        UcOrg ucOrgCreate = ObjectCopier.copyObject(createOrg, UcOrg.class);
        save(ucOrgCreate);
        dealUcOrgDetail();
        return true;
    }

    @Override
    public UcOrg getUcOrg(Long id) {
        UcOrg ucOrg = getById(id);
        if(ucOrg == null) {
            throw new ServiceException(UCResponseCode.UC_ORG_IS_NOT_EXIST);
        }
        return ucOrg;
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED)
    public Boolean updateUcOrg(UpdateOrgVO updateOrg) {
        Integer orgCount = orgMapper.selectOrgCountByIdAndName(updateOrg.getId(), updateOrg.getName());
        if(orgCount > 0) {
            throw new ServiceException(UCResponseCode.ORG_NAME_IS_EXIST);
        }
        UcOrg ucOrg = getById(updateOrg.getId());
        if(ucOrg == null) {
            throw new ServiceException(UCResponseCode.UC_ORG_IS_NOT_EXIST);
        }
        Long parentId = ucOrg.getParentId();
        ucOrg = ObjectCopier.copyObject(updateOrg, UcOrg.class);
        updateById(ucOrg);
        if(updateOrg.getParentId().equals(parentId)) {
            dealUcOrgDetail();
        }
        return true;
    }

    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED)
    public Boolean deleteUcOrg(Long id) {
        Boolean deleteFlag = true;
        List<Long> childOrgLIdList = new ArrayList<>();
        List<UcOrg> ucOrgList = orgMapper.selectOrgListForOrgTree();
        childOrgLIdList.add(id);
        recursionGetChildOrgIdList(ucOrgList, id, childOrgLIdList);

        for(Long tempId : childOrgLIdList) {
            Integer userCount = ucUserOrgRelationService.getUserCountByOrgId(tempId);
            if(userCount > 0) {
                deleteFlag = false;
                break;
            }
        }
        if(deleteFlag) {
            removeByIds(childOrgLIdList);
            dealUcOrgDetail();
        } else {
            throw new ServiceException(UCResponseCode.CURRENT_ORG_HAS_USER);
        }
        return true;
    }

    /**
     * @param
     * @author: mike.ma
     * @return: void
     * @desc: 处理组织架构子表数据
     * @date: 2022/2/25 16:37
     */
    private void dealUcOrgDetail() {
        List<UcOrgDetail> ucOrgDetailList = new ArrayList<>();
        List<UcOrg> ucOrgList = orgMapper.selectOrgListForOrgTree();
        for(UcOrg ucOrg : ucOrgList) {
            ucOrgDetailList.add(packageUcOrgDetail(ucOrg.getId(), ucOrg.getId(), IsCurrentEnum.IS_CURRENT_YES.getCode()));
        }
        for(UcOrg ucOrgParent : ucOrgList) {
            for(UcOrg ucOrgchild : ucOrgList) {
                if(ucOrgParent.getId().equals(ucOrgchild.getParentId())) {
                    ucOrgDetailList.add(packageUcOrgDetail(ucOrgParent.getId(), ucOrgchild.getId(), IsCurrentEnum.IS_CURRENT_NO.getCode()));
                }
            }
        }
        ucOrgDetailService.remove(null);
        ucOrgDetailService.saveBatch(ucOrgDetailList);
    }



    /**
     * @param ucOrg
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.OrgTreeResultDTO
     * @desc: 构建组织架构返回对象
     * @date: 2022/2/25 15:40
     */
    private OrgTreeResultDTO packageOrgTreeDTO(UcOrg ucOrg) {
        OrgTreeResultDTO orgTreeResult = new OrgTreeResultDTO();
        orgTreeResult.setId(ucOrg.getId());
        orgTreeResult.setName(ucOrg.getName());
        orgTreeResult.setParentId(ucOrg.getParentId());
        return orgTreeResult;
    }

    /**
     * @param orgId
     * @param childOrgId
     * @param isCurrent
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.entity.UcOrgDetail
     * @desc: 构建组织架构子表对象
     * @date: 2022/2/25 15:59
     */
    private UcOrgDetail packageUcOrgDetail(Long orgId, Long childOrgId, Integer isCurrent) {
        UcOrgDetail ucOrgDetail = new UcOrgDetail();
        ucOrgDetail.setOrgId(orgId);
        ucOrgDetail.setChildOrgId(childOrgId);
        ucOrgDetail.setIsCurrent(isCurrent);
        return ucOrgDetail;
    }


}

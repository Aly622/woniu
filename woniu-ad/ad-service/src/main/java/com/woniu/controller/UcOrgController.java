package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dto.OrgListPageResultDTO;
import com.woniu.dto.OrgTreeResultDTO;
import com.woniu.entity.UcOrg;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.UcOrgService;
import com.woniu.vo.CreateOrgVO;
import com.woniu.vo.OrgListPageQueryVO;
import com.woniu.vo.UpdateOrgVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 组织架构表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/org")
public class UcOrgController extends SuperController {

    @Autowired
    private UcOrgService ucOrgService;

    @ApiOperation(value = "获取组织架构树形结构")
    @PostMapping("/getOrgTree")
    public WebResponse<OrgTreeResultDTO> getOrgTree() throws Exception {
        OrgTreeResultDTO orgTreeResult = ucOrgService.getOrgTree();
        return new WebResponse(UCResponseCode.SUCCESS, orgTreeResult);
    }

    @ApiOperation(value = "获取启用的组织架构树形结构")
    @PostMapping("/getAbleOrgTree")
    public WebResponse<OrgTreeResultDTO> getAbleOrgTree() throws Exception {
        OrgTreeResultDTO orgTreeResult = ucOrgService.getAbleOrgTree();
        return new WebResponse(UCResponseCode.SUCCESS, orgTreeResult);
    }

    @ApiOperation(value = "获取组织架构分页数据")
    @PostMapping("/getOrgListPage")
    public WebResponse<Page<OrgListPageResultDTO>> getOrgListPage(@RequestBody OrgListPageQueryVO orgListPageQuery) throws Exception {
        log.info("#### 获取组织架构分页数据入参：{}", ToStringBuilder.reflectionToString(orgListPageQuery));
        Page<OrgListPageResultDTO> page = ucOrgService.getOrgListPage(orgListPageQuery);
        return new WebResponse(UCResponseCode.SUCCESS, page);
    }

    @ApiOperation(value = "创建组织架构")
    @PostMapping("/createOrg")
    public WebResponse createOrg(@RequestBody @Valid CreateOrgVO createOrg) throws Exception {
        log.info("#### 创建组织架构入参：{}", ToStringBuilder.reflectionToString(createOrg));
        Boolean createFlag = ucOrgService.createOrg(createOrg);
        if(createFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "获取组织架构详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="组织架构ID",required=true),
    })
    @GetMapping("/getUcOrgById/{id}")
    public WebResponse<UcOrg> getUcOrgById(@PathVariable("id") Long id) throws Exception {
        log.info("#### 获取组织架构详情入参：id = {}", id);
        UcOrg ucOrg = ucOrgService.getUcOrg(id);
        return new WebResponse(UCResponseCode.SUCCESS, ucOrg);
    }

    @ApiOperation(value = "更新组织架构")
    @PostMapping("/updateOrg")
    public WebResponse updateOrg(@RequestBody @Valid UpdateOrgVO updateOrg) throws Exception {
        log.info("#### 更新组织架构入参：{}", ToStringBuilder.reflectionToString(updateOrg));
        Boolean updateFlag = ucOrgService.updateUcOrg(updateOrg);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "删除组织架构")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="组织ID",required=true),
    })
    @DeleteMapping("/deleteOrg/{id}")
    public WebResponse deleteOrg(@PathVariable("id") Long id) throws Exception {
        log.info("#### 删除组织架构入参：id = {}", id);
        Boolean deleteFlag = ucOrgService.deleteUcOrg(id);
        if(deleteFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

}


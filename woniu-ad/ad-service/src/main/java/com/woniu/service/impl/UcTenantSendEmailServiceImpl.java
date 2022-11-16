package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.woniu.dao.custom.TenantSendEmailMapper;
import com.woniu.entity.UcTenantSendEmail;
import com.woniu.dao.UcTenantSendEmailMapper;
import com.esmartwave.niumeng.diap.exception.ServiceException;
import com.woniu.response.UCResponseCode;
import com.woniu.service.UcTenantSendEmailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmartwave.niumeng.diap.utils.EncryptUtils;
import com.esmartwave.niumeng.diap.utils.ObjectCopier;
import com.woniu.vo.SaveTenantSendEmailVO;
import com.woniu.vo.TenantSendEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 租户表发送邮件邮箱配置表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-03-28
 */
@Service
public class UcTenantSendEmailServiceImpl extends ServiceImpl<UcTenantSendEmailMapper, UcTenantSendEmail> implements UcTenantSendEmailService {

    @Value("${encrypt.aseKey}")
    private String encryptAeskey;

    @Autowired
    private TenantSendEmailMapper tenantSendEmailMapper;



    @Override
    public boolean saveTenantSendEmail(TenantSendEmailVO tenantSendEmailVO) {
        //根据租户id删除租户邮箱配置信息
        tenantSendEmailMapper.delByTenantId(tenantSendEmailVO.getTenantId());

        if (CollectionUtils.isNotEmpty(tenantSendEmailVO.getEmailInfo())) {
            //判断是否有重复数据
            Set<SaveTenantSendEmailVO> set = new HashSet<>(tenantSendEmailVO.getEmailInfo());
            if (tenantSendEmailVO.getEmailInfo().size() != set.size()) throw new ServiceException(UCResponseCode.DUPLICATE_OBJECT);

            List<UcTenantSendEmail> ucTenantSendEmails = ObjectCopier.copyList(tenantSendEmailVO.getEmailInfo(), UcTenantSendEmail.class);
            ucTenantSendEmails.forEach(ucTenantSendEmail -> {
                ucTenantSendEmail.setTenantId(tenantSendEmailVO.getTenantId());
                if (StringUtils.isNotBlank(ucTenantSendEmail.getEmailPassword()))
                    //邮箱密码加密
                    ucTenantSendEmail.setEmailPassword(EncryptUtils.encryptAES(ucTenantSendEmail.getEmailPassword(),encryptAeskey));
            });
            boolean isSuccess = saveBatch(ucTenantSendEmails);
        }

        return true;
    }

    @Override
    public List<UcTenantSendEmail> getAllTenantEmail() {
        List<UcTenantSendEmail> emails = list();
        //解密邮箱密码
        emails.forEach(email ->{
            if (StringUtils.isNotBlank(email.getEmailPassword()))
            email.setEmailPassword(EncryptUtils.decryptAES(email.getEmailPassword(),encryptAeskey));
        });
        return emails;
    }

    @Override
    public List<UcTenantSendEmail> getEmailByTenantId(Long tenantId) {
        List<UcTenantSendEmail> emails =tenantSendEmailMapper.getEmailByTenantId(tenantId);
        //解密邮箱密码
        emails.forEach(email ->{
            if (StringUtils.isNotBlank(email.getEmailPassword()))
                email.setEmailPassword(EncryptUtils.decryptAES(email.getEmailPassword(),encryptAeskey));
        });
        return emails;
    }
}

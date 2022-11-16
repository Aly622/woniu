package com.woniu.code.bo;

import java.util.List;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName CodePackageBatch.java
 * @Description TODO
 * @createTime 2022年03月12日 14:28:00
 */
public class CodePackageBatch {
    private Long id;
    //活动ID
    private Long campaignId;
    //活动名称
    private String campaignName;
    //生码批次号
    private String batchNumber;

    //生码批次顺序 X:Y:Z
    private String batchOrder;

    //条码属性 1-标准码、2-微信码、3-三方码
    private Integer codeType;

    //微信码公众号 微信码时有效
    private String wxOfficialAccount;

    //微信码公众号 微信码时有效
    private String wxCodeBatchId;

    //第三方码导入文件路径
    private String externalCodeFile;

    //关联方式 1-前关联、2-后关联
    private Integer codeRelationStyle;

    //标签属性 1-单标、2-套标
    private Integer codeTagStyle;

    //层级 1-两级、2-三级
    private Integer codeLevel;

    //层级比例 X:Y:Z
    private String codeLevelRatio;

    //生码数量
    private Integer codeCnt;

    //1-待提交、2-审批中、3-待处理、4-生码中、5-压包中、6-已完成、7-已驳回、8-异常-任务中断、9-异常-生码失败、10-已取消、11-已作废
    private Integer status;

    //租户ID
    private Long tenantId;

    private List<CodeRule> codeRule;//规则
    
    private List<CodePackage> codePackages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getBatchOrder() {
        return batchOrder;
    }

    public void setBatchOrder(String batchOrder) {
        this.batchOrder = batchOrder;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public String getWxOfficialAccount() {
        return wxOfficialAccount;
    }

    public void setWxOfficialAccount(String wxOfficialAccount) {
        this.wxOfficialAccount = wxOfficialAccount;
    }

    public String getWxCodeBatchId() {
        return wxCodeBatchId;
    }

    public void setWxCodeBatchId(String wxCodeBatchId) {
        this.wxCodeBatchId = wxCodeBatchId;
    }

    public String getExternalCodeFile() {
        return externalCodeFile;
    }

    public void setExternalCodeFile(String externalCodeFile) {
        this.externalCodeFile = externalCodeFile;
    }

    public Integer getCodeRelationStyle() {
        return codeRelationStyle;
    }

    public void setCodeRelationStyle(Integer codeRelationStyle) {
        this.codeRelationStyle = codeRelationStyle;
    }

    public Integer getCodeTagStyle() {
        return codeTagStyle;
    }

    public void setCodeTagStyle(Integer codeTagStyle) {
        this.codeTagStyle = codeTagStyle;
    }

    public Integer getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(Integer codeLevel) {
        this.codeLevel = codeLevel;
    }

    public String getCodeLevelRatio() {
        return codeLevelRatio;
    }

    public void setCodeLevelRatio(String codeLevelRatio) {
        this.codeLevelRatio = codeLevelRatio;
    }

    public Integer getCodeCnt() {
        return codeCnt;
    }

    public void setCodeCnt(Integer codeCnt) {
        this.codeCnt = codeCnt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<CodeRule> getCodeRule() {
        return codeRule;
    }

    public void setCodeRule(List<CodeRule> codeRule) {
        this.codeRule = codeRule;
    }

    public List<CodePackage> getCodePackages() {
        return codePackages;
    }

    public void setCodePackages(List<CodePackage> codePackages) {
        this.codePackages = codePackages;
    }
}

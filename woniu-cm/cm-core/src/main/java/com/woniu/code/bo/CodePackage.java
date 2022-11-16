package com.woniu.code.bo;

/**
 * <p>
 * 码包表
 * </p>
 *
 * @author Admin
 * @since 2022-03-10
 */
public class CodePackage {

    private Long id;

    //活动ID
    private Long campaignId;

    //码包批次表IDcode_package_batch_id
    private Long codePackageBatchId;

    //码包顺序 X:Y:Z
    private String packageOrder;

    //码包名称
    private String name;

    //生码数量
    private Long codeTotalCnt;

    //已生成数量
    private Long codeGeneratedCnt;

    //码包状态 1-待处理  2-生码完成 3-下载完成
    private Integer status;

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

    public Long getCodePackageBatchId() {
        return codePackageBatchId;
    }

    public void setCodePackageBatchId(Long codePackageBatchId) {
        this.codePackageBatchId = codePackageBatchId;
    }

    public String getPackageOrder() {
        return packageOrder;
    }

    public void setPackageOrder(String packageOrder) {
        this.packageOrder = packageOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCodeTotalCnt() {
        return codeTotalCnt;
    }

    public void setCodeTotalCnt(Long codeTotalCnt) {
        this.codeTotalCnt = codeTotalCnt;
    }

    public Long getCodeGeneratedCnt() {
        return codeGeneratedCnt;
    }

    public void setCodeGeneratedCnt(Long codeGeneratedCnt) {
        this.codeGeneratedCnt = codeGeneratedCnt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

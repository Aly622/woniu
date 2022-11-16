package com.woniu.code.bo;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName CodeRule.java
 * @Description 生码规则
 * @createTime 2022年03月12日 13:54:00
 */
public class CodeRuleDetail {
    // 1-随机字母、2-随机字母+随机数字、3-随机数字、4-数字流水、5-随机字母+数字流水号
    private Integer type;
    // 是否忽略
    private Integer isIgnore;
    // 是否过滤干扰字符
    private Integer isFilterChar;
    // 是否区分大小写
    private Integer isCaseSensitive;
    // "I,L,0,O\"
    private String filterChar;
    // 1-纯大写、2-纯小写
    private Integer caseSensitive;
    // 数字起始值
    private Integer numFrom;
    // 数字长度
    private Integer numLength;
    // 冗余字段
    private String batchOrder;//批次顺序
    private String packageOrder;//码包顺序
    private Integer codeLength;//码长度 扣除前缀以后的码长度

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(Integer isIgnore) {
        this.isIgnore = isIgnore;
    }

    public Integer getIsFilterChar() {
        return isFilterChar;
    }

    public void setIsFilterChar(Integer isFilterChar) {
        this.isFilterChar = isFilterChar;
    }

    public Integer getIsCaseSensitive() {
        return isCaseSensitive;
    }

    public void setIsCaseSensitive(Integer isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
    }

    public String getFilterChar() {
        return filterChar;
    }

    public void setFilterChar(String filterChar) {
        this.filterChar = filterChar;
    }

    public Integer getCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(Integer caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Integer getNumFrom() {
        return numFrom;
    }

    public void setNumFrom(Integer numFrom) {
        this.numFrom = numFrom;
    }

    public Integer getNumLength() {
        return numLength;
    }

    public void setNumLength(Integer numLength) {
        this.numLength = numLength;
    }

    public String getBatchOrder() {
        return batchOrder;
    }

    public void setBatchOrder(String batchOrder) {
        this.batchOrder = batchOrder;
    }

    public String getPackageOrder() {
        return packageOrder;
    }

    public void setPackageOrder(String packageOrder) {
        this.packageOrder = packageOrder;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }
}

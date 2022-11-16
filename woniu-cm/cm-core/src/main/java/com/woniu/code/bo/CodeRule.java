package com.woniu.code.bo;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName CodeRule.java
 * @Description 生码规则
 * @createTime 2022年03月12日 13:54:00
 */
public class CodeRule {
    // 层级  123层级
    private Integer levelIdx;
    // 1-QR码、2-CODE128B
    private Integer codeSys;
    // 1-明码、2-暗码
    private Integer codePrint;
    // 默认前缀
    private String preFix;
    // 码长度
    private Integer codeLength;
    // 码标签类型 1-人码、2-货码、3-店码
    private Integer codeTag;
    // 规则明细
    private CodeRuleDetail ruleDetail;

    public Integer getLevelIdx() {
        return levelIdx;
    }

    public void setLevelIdx(Integer levelIdx) {
        this.levelIdx = levelIdx;
    }

    public Integer getCodeSys() {
        return codeSys;
    }

    public void setCodeSys(Integer codeSys) {
        this.codeSys = codeSys;
    }

    public Integer getCodePrint() {
        return codePrint;
    }

    public void setCodePrint(Integer codePrint) {
        this.codePrint = codePrint;
    }

    public String getPreFix() {
        return preFix;
    }

    public void setPreFix(String preFix) {
        this.preFix = preFix;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Integer getCodeTag() {
        return codeTag;
    }

    public void setCodeTag(Integer codeTag) {
        this.codeTag = codeTag;
    }

    public CodeRuleDetail getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(CodeRuleDetail ruleDetail) {
        this.ruleDetail = ruleDetail;
    }
}

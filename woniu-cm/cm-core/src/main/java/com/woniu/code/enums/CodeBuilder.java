package com.woniu.code.enums;

import com.woniu.code.bo.CodeRuleDetail;
import com.woniu.enums.YesOrNo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName CodeBuilder.java
 * @Description 生码策略类
 * @createTime 2022年03月17日 22:26:00
 */
@Slf4j
public enum CodeBuilder {
    NULL {
        @Override
        public List<String> getSourceList(CodeRuleDetail codeRuleDetail) {
            return null;
        }
        @Override
        public String codeGenerate(CodeRuleDetail codeRuleDetail, int codeSeq) {
            return null;
        }

        @Override
        public Boolean uniqueCheck() {
            return null;
        }
    },
    // 1-随机字母、2-随机字母+随机数字、3-随机数字、4-数字流水、5-随机字母+数字流水号
    CHAR_ONLY {
        @Override
        public List<String> getSourceList(CodeRuleDetail codeRuleDetail) {
            List<String> ret = new ArrayList<>();
            // 组建生码引擎
            if(YesOrNo.NO.ordinal() == codeRuleDetail.getIsIgnore()) {//该层级需要进行生码
                //是否区分大小写
                if(YesOrNo.NO.ordinal() == codeRuleDetail.getIsCaseSensitive()) {
                    ret.addAll(GENERATE_SOURCE_LCASE);
                    ret.addAll(GENERATE_SOURCE_UCASE);
                } else {
                    if(CaseSensitive.UPPERCASE.getCode().equals(codeRuleDetail.getCaseSensitive())) {
                        ret.addAll(GENERATE_SOURCE_UCASE);
                    } else if(CaseSensitive.LOWERCASE.getCode().equals(codeRuleDetail.getCaseSensitive())) {
                        ret.addAll(GENERATE_SOURCE_LCASE);
                    } else {
                        log.error("错误的大小写参数。");
                    }
                }
                //是否过滤字符
                if(YesOrNo.YES.ordinal() == codeRuleDetail.getIsFilterChar()) {
                    //清除过滤字符
                    ret.removeAll(Arrays.asList(codeRuleDetail.getFilterChar().split(",")));
                }
            }
            return ret;
        }
        @Override
        public String codeGenerate(CodeRuleDetail codeRuleDetail, int codeSeq) {
            List<String> sourceList = getSourceList(codeRuleDetail);
            //打乱元素排序，增加反推难度
            Collections.shuffle(sourceList);
            //默认增加批次顺序和码包顺序
            StringBuilder retStr = new StringBuilder(codeRuleDetail.getBatchOrder()).append(codeRuleDetail.getPackageOrder());
            int codeLen = codeRuleDetail.getCodeLength() - retStr.length();
            for (int i = REDUNDANT_LENGTH; i< REDUNDANT_LENGTH + codeLen; i++) {
                retStr.append(sourceList.get(i));
            }
            //更改下面两个数字可以取不同位数的随机数
            return retStr.toString();
        }
        @Override
        public Boolean uniqueCheck() {
            return true;
        }
    },
    CHAR_DIGIT {
        @Override
        public List<String> getSourceList(CodeRuleDetail codeRuleDetail) {
            List<String> ret = new ArrayList<>();
            // 组建生码引擎
            if(YesOrNo.NO.ordinal() == codeRuleDetail.getIsIgnore()) {//该层级需要进行生码
                //是否区分大小写
                if(YesOrNo.NO.ordinal() == codeRuleDetail.getIsCaseSensitive()) {
                    ret.addAll(GENERATE_SOURCE_LCASE);
                    ret.addAll(GENERATE_SOURCE_UCASE);
                } else {
                    if(CaseSensitive.UPPERCASE.getCode().equals(codeRuleDetail.getCaseSensitive())) {
                        ret.addAll(GENERATE_SOURCE_UCASE);
                    } else if(CaseSensitive.LOWERCASE.getCode().equals(codeRuleDetail.getCaseSensitive())) {
                        ret.addAll(GENERATE_SOURCE_LCASE);
                    } else {
                        log.error("错误的大小写参数。");
                    }
                }
                ret.addAll(GENERATE_SOURCE_DIGIT);
                //是否过滤字符
                if(YesOrNo.YES.ordinal() == codeRuleDetail.getIsFilterChar()) {
                    //清除过滤字符
                    ret.removeAll(Arrays.asList(codeRuleDetail.getFilterChar().split(",")));
                }
            }
            return ret;
        }
        @Override
        public String codeGenerate(CodeRuleDetail codeRuleDetail, int codeSeq) {
            List<String> sourceList = getSourceList(codeRuleDetail);
            //打乱元素排序，增加反推难度
            Collections.shuffle(sourceList);
            StringBuilder retStr = new StringBuilder(codeRuleDetail.getBatchOrder()).append(codeRuleDetail.getPackageOrder());
            int codeLen = codeRuleDetail.getCodeLength() - retStr.length();
            for (int i = REDUNDANT_LENGTH; i< REDUNDANT_LENGTH + codeLen; i++) {
                retStr.append(sourceList.get(i));
            }
            //更改下面两个数字可以取不同位数的随机数
            return retStr.toString();
        }
        @Override
        public Boolean uniqueCheck() {
            return true;
        }
    },
    DIGIT_ONLY {
        @Override
        public List<String> getSourceList(CodeRuleDetail codeRuleDetail) {
            List<String> ret = new ArrayList<>();
            List<String> tmp = new ArrayList<>();
            // 组建生码引擎
            if(YesOrNo.NO.ordinal() == codeRuleDetail.getIsIgnore()) {//该层级需要进行生码
                tmp.addAll(GENERATE_SOURCE_DIGIT);
                //是否过滤字符
                if(YesOrNo.YES.ordinal() == codeRuleDetail.getIsFilterChar()) {
                    //清除过滤字符
                    tmp.removeAll(Arrays.asList(codeRuleDetail.getFilterChar().split(",")));
                }
                //添加两份一样的数字以填充源，减少重复率
                ret.addAll(tmp);
                ret.addAll(tmp);
            }
            return ret;
        }
        @Override
        public String codeGenerate(CodeRuleDetail codeRuleDetail, int codeSeq) {
            StringBuilder retStr = new StringBuilder(codeRuleDetail.getBatchOrder()).append(codeRuleDetail.getPackageOrder());
            int codeLen = codeRuleDetail.getCodeLength() - retStr.length();
            List<String> sourceList = getSourceList(codeRuleDetail);
            //打乱元素排序，增加反推难度
            Collections.shuffle(sourceList);
            for (int i = REDUNDANT_LENGTH; i< REDUNDANT_LENGTH + codeLen; i++) {
                retStr.append(sourceList.get(i));
            }
            //更改下面两个数字可以取不同位数的随机数
            return retStr.toString();
        }
        @Override
        public Boolean uniqueCheck() {
            return true;
        }
    },
    DIGIT_SEQUENCE {
        // 该类型仅需通过码包顺序获取序号
        @Override
        public List<String> getSourceList(CodeRuleDetail codeRuleDetail) {
            List<String> ret = new ArrayList<>();
            return ret;
        }
        @Override
        public String codeGenerate(CodeRuleDetail codeRuleDetail, int codeSeq) {
            // 0 代表前面补充0
            // 4 代表长度为4
            // d 代表参数为正数型
            return String.format("%0"+codeRuleDetail.getCodeLength()+"d", codeSeq);
        }
        @Override
        public Boolean uniqueCheck() {
            return false;
        }
    },
    CHAR_DIGIT_SEQUENCE {
        // 该类型仅需通过码包顺序获取序号
        @Override
        public List<String> getSourceList(CodeRuleDetail codeRuleDetail) {
            List<String> ret = new ArrayList<>();
            // 组建生码引擎
            if(YesOrNo.NO.ordinal() == codeRuleDetail.getIsIgnore()) {//该层级需要进行生码
                //是否区分大小写
                if(YesOrNo.NO.ordinal() == codeRuleDetail.getIsCaseSensitive()) {
                    ret.addAll(GENERATE_SOURCE_LCASE);
                    ret.addAll(GENERATE_SOURCE_UCASE);
                } else {
                    if(CaseSensitive.UPPERCASE.getCode().equals(codeRuleDetail.getCaseSensitive())) {
                        ret.addAll(GENERATE_SOURCE_UCASE);
                    } else if(CaseSensitive.LOWERCASE.getCode().equals(codeRuleDetail.getCaseSensitive())) {
                        ret.addAll(GENERATE_SOURCE_LCASE);
                    } else {
                        log.error("错误的大小写参数。");
                    }
                }
                //是否过滤字符
                if(YesOrNo.YES.ordinal() == codeRuleDetail.getIsFilterChar()) {
                    //清除过滤字符
                    ret.removeAll(Arrays.asList(codeRuleDetail.getFilterChar().split(",")));
                }
            }
            return ret;
        }
        @Override
        public String codeGenerate(CodeRuleDetail codeRuleDetail, int codeSeq) {
            int seqLength = codeRuleDetail.getNumLength();
            StringBuilder retStr = new StringBuilder();
            // 先生成随机字母
            List<String> sourceList = getSourceList(codeRuleDetail);
            //打乱元素排序，增加反推难度
            Collections.shuffle(sourceList);
            int codeLen = codeRuleDetail.getCodeLength() - seqLength;//随机字母的长度 此时不需要批次和码包顺序
            for (int i = 0; i< codeLen; i++) {
                retStr.append(sourceList.get(i));
            }
            //再拼接序号
            return retStr.append(String.format("%0"+seqLength+"d", codeSeq)).toString();
        }
        @Override
        public Boolean uniqueCheck() {
            return false;
        }
    };

    /**
     * @param codeRuleDetail 根据码规则获取生成码的源字符数据
     * @return
     */
    // 获取生成随机码的源字符
    public abstract List<String> getSourceList(CodeRuleDetail codeRuleDetail);

    /**
     * @param codeRuleDetail 码规则
     * @param codeSeq 自增码数字-仅限自增时使用
     * @return
     */
    // 获取生成随机码, 生码规则、码长度、生码序号(自增序列时使用)
    public abstract String codeGenerate(CodeRuleDetail codeRuleDetail, int codeSeq);

    // 是否校验码包内唯一
    public abstract Boolean uniqueCheck();

    public static CodeBuilder getInstance(Integer typeIdx) {
        if(typeIdx == null
                || typeIdx > CodeBuilder.values().length) {
            log.error("非法的generateType");
        }
        return CodeBuilder.values()[typeIdx];
    }

    /*
     * 字符源，可以剔除0L01,可以根据需要加入小写英文字母和特殊字符等
     * */
    private static List<String> GENERATE_SOURCE_DIGIT = Arrays.asList(new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    });
    private static List<String> GENERATE_SOURCE_UCASE = Arrays.asList(new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    });
    private static List<String> GENERATE_SOURCE_LCASE = Arrays.asList(new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    });
    private static Integer REDUNDANT_LENGTH = 3;
}

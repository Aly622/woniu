package com.woniu.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Oliver.liu
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2021/9/2417:16
 */
public class WhiteListUtils {
    // token全局白名单
    private static final Map<String, String> permitAllURLPatternMap = new HashMap<>();
    // 无token 但必须要有tokenuser的例外名单
    private static final Map<String, String> permitURLWithTokenUserPatternMap = new HashMap<>();
    // 对外API 无token 但必须要有header信息的例外名单
    private static final Map<String, String> permitAPIWithHeadTokenPatternMap = new HashMap<>();
    // entry 白名单
    private static final Map<String, String> permitEntryPatternMap = new HashMap<>();
    /**
     * 白名单及例外名单
     */
    static {
        permitAllURLPatternMap.put(".*login.*", "");//登录
        permitAllURLPatternMap.put(".*sendEmailVerificationCode.*", "");//发送邮箱验证码
        permitAllURLPatternMap.put(".*smsVeriCode.*", "");//发送邮箱验证码
        permitAllURLPatternMap.put(".*updatePasswordByEmail.*", "");//根据邮箱更新密码
        permitAllURLPatternMap.put(".*captchaImage.*", "");//获取图片验证码
        permitAllURLPatternMap.put(".*download.*", "");//下载
        permitAllURLPatternMap.put(".*feignDownload.*", "");//下载
        permitAllURLPatternMap.put(".*jump.*", ""); //长短链接
        permitAllURLPatternMap.put(".*transferFileToInputStream.*", ""); // 文件下载
        permitAllURLPatternMap.put(".*admin.*","");
        ///admin/swagger-ui.html,/admin/swagger-resources/,/admin//,/admin/v2/**
        permitAllURLPatternMap.put(".*actuator.*", ""); //监控actuator

        permitURLWithTokenUserPatternMap.put(".*mini.*", "");// 小程序
        permitURLWithTokenUserPatternMap.put(".*front.*", "");// 微信C端用户

        permitAPIWithHeadTokenPatternMap.put(".*api.*", "");

        permitEntryPatternMap.put(".*api-docs.*", ""); //swagger api-docs
        permitEntryPatternMap.put(".*swagger.*", ""); //swagger
        permitEntryPatternMap.put(".*webjars.*","");
    }

    public static boolean isInWhiteList(String url){
        boolean ret = false;
        Iterator iter = permitAllURLPatternMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            ret = ret || url.matches(key);
        }
        return ret;
    }

    public static boolean isInTokenUserList(String url){
        boolean ret = false;
        Iterator iter = permitURLWithTokenUserPatternMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            ret = ret || url.matches(key);
        }
        return ret;
    }

    public static boolean isInAPIHeadTokenList(String url){
        boolean ret = false;
        Iterator iter = permitAPIWithHeadTokenPatternMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            ret = ret || url.matches(key);
        }
        return ret;
    }

    public static boolean isEntryWhiteList(String url){
        boolean ret = false;
        Iterator iter = permitEntryPatternMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            ret = ret || url.matches(key);
        }
        return ret;
    }
}

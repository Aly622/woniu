package com.woniu.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UCResponseCode implements IResponseCode{
    PARAM_IS_ILLEGAL(13001, "参数不合法"),
    CODE_IS_EXIST(13002, "已存在编号"),
    CREATE_ROLE_ERROR(13003, "创建角色错误"),
    CREATE_ORG_ERROR(13004, "创建组织架构错误"),
    CREATE_TENANT_ERROR(13005, "创建租户错误"),
    UPDATE_TENANT_ERROR(13006, "编辑租户错误"),
    PARAM_IS_NOT_NULL(13007,"参数不能为空"),
    UC_ORG_IS_NOT_EXIST(13008,"组织架构不存在"),
    CURRENT_ORG_HAS_USER(13009,"删除失败该组织架构已被账号管理使用"),
    ROLE_NAME_IS_NULL(13010,"角色名称为空"),
    DATA_SCOPE_IS_NULL(13011,"角色数据范围为空"),
    STATUS_IS_NULL(13012,"状态为空"),
    UC_ROLE_IS_NOT_EXIST(13013,"角色不存在"),
    ROLE_ID_IS_NULL(13014,"角色ID为空"),
    CURRENT_ROLE_HAS_USER(13015,"当前角色下还有账户，请先删除账户或把账户绑定到其他角色再删除当前角色"),
    PERMISSION_ID_LIST_IS_NULL(13016,"权限ID列表为空"),
    AUDIT_CONFIG_NAME_IS_NULL(13017,"审批流名称为空"),
    AUDIT_CONFIG_ROLE_IS_EMPTY(13018,"角色节点列表为空"),
    AUDIT_CONFIG_ID_IS_NULL(13019,"审批流ID为空"),
    USER_NAME_IS_NULL(13020,"用户名为空"),
    NICK_NAME_IS_NULL(13021,"昵称为空"),
    PASSWORD_IS_NULL(13022,"密码为空"),
    EMAIL_IS_NULL(13023,"邮箱为空"),
    MOBILE_IS_NULL(13024,"手机号为空"),
    USER_TYPE_IS_NULL(13025,"账号类型为空"),
    USER_TYPE_IS_ERROR(13026,"账号类型不正确"),
    USER_BIND_TENANT_LIST_HAS_DATA(13027,"账号类型为平台时不应该绑定租户"),
    USER_BIND_TENANT_LIST_NO_DATA(13028,"账号类型为租户时需要绑定租户"),
    PASSWORD_PATTERN_IS_NOT_CORRECT(13029,"密码格式不正确"),
    EMAIL_PATTERN_IS_NOT_CORRECT(13030,"邮箱格式不正确"),
    MOBILE_PATTERN_IS_NOT_CORRECT(13031,"手机号格式不正确"),
    TENANT_NOT_HAVE_ADMIN_ROLE(13032,"对应的租户不存在超级管理员角色"),
    TENANT_NOT_HAVE_ROOT_ORG(13033,"对应的租户没有根组织"),
    USER_ID_IS_NULL(13034,"账户ID为空"),
    ORG_ID_IS_NULL(13035,"组织ID或生产商ID为空"),
    TENANT_IS_REPEAT(13036,"租户绑定列表中有重复的租户"),
    USERNAME_OR_PASSWORD_IS_ERROR_OR_DISABLE(13037,"用户名或密码错误或用户已停用"),
    NAME_IS_EXIST(13038, "已存在该名称"),
    DUPLICATE_OBJECT(13039, "重复对象"),
    OLD_PASSWORD_IS_ERROR(13040, "账户原密码错误"),
    EMAIL_IS_EXIST(13041, "邮箱已存在"),
    EMAIL_IS_NOT_EXIST(13042, "该邮箱未注册，请联系管理员"),
    AUTH_CODE_IS_ERROR(13043, "验证码错误，请重新输入"),
    USER_NAME_IS_EXIST(13044, "账户用户名已存在"),
    ROLE_NAME_IS_EXIST(13045, "角色名称已存在"),
    ORG_NAME_IS_EXIST(13046, "组织名称已存在"),
    ROLE_IS_NOT_EXIST_OR_DISABLED(13047, "角色不存在或者已停用"),
    LOGIN_PLATFORM_IS_ERROR(13048, "账号登录错误，请检查登录地址"),
    ALL_TENANT_IS_DISABLED(13049, "租户已停用，账号不能登录"),
    DONT_HAVE_USER_WITH_MOBILE(13050, "不存在该手机号对应的账号或该账号已停用，不可登录"),
    MOBILE_IS_EXISTS(13051, "手机号已存在"),

    SUCCESS(0, "用户成功请求"),
    FAIL(-1, "用户请求失败");
    private long code;
    private String message;

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

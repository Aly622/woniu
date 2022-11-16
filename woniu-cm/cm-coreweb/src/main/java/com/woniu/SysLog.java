package com.woniu;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author Admin
 * @since 2019-08-07
 */
public class SysLog extends Model<SysLog> {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 服务id
     */
    private String serviceId;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求日期时间
     */
    private LocalDateTime requestDateTime;

    /**
     * 请求结果
     */
    private String responseResult;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdDateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BsLog{" +
        "id=" + id +
        ", serviceId=" + serviceId +
        ", serviceType=" + serviceType +
        ", requestParam=" + requestParam +
        ", requestDateTime=" + requestDateTime +
        ", responseResult=" + responseResult +
        ", createdDateTime=" + createdDateTime +
        "}";
    }
}

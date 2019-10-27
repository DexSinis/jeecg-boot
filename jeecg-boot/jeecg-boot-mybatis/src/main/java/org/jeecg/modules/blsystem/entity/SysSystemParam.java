package org.jeecg.modules.blsystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-25
 */
@TableName("bl_sys_system_param")
public class SysSystemParam extends Model<SysSystemParam> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PARAM_ID", type = IdType.AUTO)
    private Integer paramId;

    private String module;

    private String paramName;

    private String paramValue;

    private String remark;

    private String createuid;

    private Date creattime;

    private String updateuid;

    private Date updatetime;

    private Integer rflag;

    /**
     * 是否允许后台界面编辑 0：否 1：是
     */
    private Integer canEdit;

    /**
     * 参数状态  0：无效  1：有效
     */
    private Integer status;

    /**
     * 参数中文名称
     */
    private String displayName;


    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateuid() {
        return createuid;
    }

    public void setCreateuid(String createuid) {
        this.createuid = createuid;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getUpdateuid() {
        return updateuid;
    }

    public void setUpdateuid(String updateuid) {
        this.updateuid = updateuid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
    }

    public Integer getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Integer canEdit) {
        this.canEdit = canEdit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    protected Serializable pkVal() {
        return this.paramId;
    }

    @Override
    public String toString() {
        return "SysSystemParam{" +
        "paramId=" + paramId +
        ", module=" + module +
        ", paramName=" + paramName +
        ", paramValue=" + paramValue +
        ", remark=" + remark +
        ", createuid=" + createuid +
        ", creattime=" + creattime +
        ", updateuid=" + updateuid +
        ", updatetime=" + updatetime +
        ", rflag=" + rflag +
        ", canEdit=" + canEdit +
        ", status=" + status +
        ", displayName=" + displayName +
        "}";
    }
}

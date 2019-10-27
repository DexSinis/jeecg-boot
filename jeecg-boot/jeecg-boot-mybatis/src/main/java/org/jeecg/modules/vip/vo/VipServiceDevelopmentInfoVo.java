package org.jeecg.modules.vip.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.util.string.StringUtil;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Date: Created in 2019/10/9 10:28
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "服务动态对象", description = "服务动态对象")
public class VipServiceDevelopmentInfoVo {

    //    @JSONField(name = "pricingpkgname")
    @Excel(name = "pricingPkgName", width = 11)
    @ApiModelProperty(value = "定价包名称")
    private String pricingPkgName;

    //    @JSONField(name = "nickname")
    @Excel(name = "nickName", width = 11)
    @ApiModelProperty(value = "用户名称")
    private String nickName;


    public String getPricingPkgName() {
        return pricingPkgName;
    }

    public void setPricingPkgName(String pricingPkgName) {
        this.pricingPkgName = pricingPkgName;
    }

    public String getNickName() {
        if (StringUtil.isEmpty(this.nickName)) {
            this.nickName = "";
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

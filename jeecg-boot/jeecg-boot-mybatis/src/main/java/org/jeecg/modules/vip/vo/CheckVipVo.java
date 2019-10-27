package org.jeecg.modules.vip.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Date: Created in 2019/10/9 14:26
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="是否vip对象", description="是否vip")
public class CheckVipVo {
    @Excel(name = "是否vip ", width = 6)
    @ApiModelProperty(value = "是否vip true 是vip false 不是vip  ")
    private boolean isVip;

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public CheckVipVo(boolean isVip) {
        this.isVip = isVip;
    }
}

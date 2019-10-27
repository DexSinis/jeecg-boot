package org.jeecg.modules.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 记录用户对营销资源的领用、消费使用情况
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUserResourceUsed extends Model<TUserResourceUsed> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer resourceInstId;

    /**
     * 生成规则：
            制作商_资源类别_制作日期（年月日时分秒）_6位数字和英文混合随机值

            制作商：参见字典编码MKT_RESOURCE_PRODUCER
            资源类别：参见字典编码MKT_RESOURCE_TYPE
            
     */
    private String resourceInstQrcode;

    private String resourceSerial;

    /**
     * 参见字典编码MKT_RESOURCE_INST_STATUS：
            0	未使用
            1	已使用

            
     */
    private String status;

    private Date effDate;

    private Date expDate;

    private Date getTime;

    private Date usingTime;

    private Integer usintMerch;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

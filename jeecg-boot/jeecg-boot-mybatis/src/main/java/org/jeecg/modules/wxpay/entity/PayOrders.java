package org.jeecg.modules.wxpay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayOrders extends Model<PayOrders> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 支付平台业务单号（微信对应transcation_id）
     */
    private String payTradeNo;

    /**
     * 内部支付订单编号（微信对应out_trade_no)
     */
    private String busiTradeNo;

    /**
     * 预付单号
     */
    private String prePayNo;

    /**
     * 填表名
表示该订单从业务平台的对应表中查询具体的订单信息（包括金额，产品类型等）
     */
    private String busiModel;

    private String status;

    /**
     * 对应医生customer_id
     */
    private Integer goodId;

    private Date createTime;

    public PayOrders(Map map) {
        this.setBusiTradeNo( map.get("busiTradeNo").toString());
        this.setPrePayNo( map.get("prePayNo").toString());
        this.setBusiModel( map.get("busiModel").toString());
        this.setStatus( map.get("status").toString());
        this.setGoodId( Integer.valueOf(map.get("goodId").toString()));
        this.setCreateTime(new Date());
    }
    public PayOrders() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

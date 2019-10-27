package org.jeecg.modules.notice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Map;

import org.jeecg.util.string.StringUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("service_dynamic_info")
public class ServiceDynamicInfo extends Model<ServiceDynamicInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "INFO_ID", type = IdType.AUTO)
    private Integer infoId;


    private Integer orderId;

    /**
     * 如果是咨询动态则填写工单创建时间，如果是评价动态则填写评价时间。
     */
    private Long createTime;

    /**
     * 0：评价动态  1：咨询动态
     */
    private Integer msgType;

    /**
     * 服务动态内容，通过定时任务生成固定内容。其中医生名字需要拼接URL。
     */
    private String content;

    /**
     * 服务医生ID
     */
    private Integer customerId;

    /**
     * 服务医生姓名
     */
    private String custName;


    /**
     * 用户姓名
     */
    private String contName;



    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }


    public ServiceDynamicInfo(){

    }
    public ServiceDynamicInfo(Map map) {
        this.setContent(StringUtil.isBlank(map.get("CONTENT").toString())?"用户7天未评价，系统默认满意":map.get("CONTENT").toString());
        this.setCreateTime((Long) map.get("CREATE_TIME"));
        this.setMsgType(Integer.valueOf( map.get("MSG_TYPE").toString()));
        this.setOrderId(Integer.valueOf( map.get("ORDER_ID").toString()));
        if(map.get("CONT_NAME")!=null){
            this.setContName(map.get("CONT_NAME").toString().length()>=4?map.get("CONT_NAME").toString().substring(0,4)+"...":map.get("CONT_NAME").toString());
        }else{
            this.setContName("...");
        }
        if(map.get("CUST_NAME")!=null){
            this.setCustName(map.get("CUST_NAME").toString());
        }else{
            this.setCustName("...");
        }
        this.setCustomerId(Integer.valueOf( map.get("CUSTOMER_ID").toString()));
    }

}

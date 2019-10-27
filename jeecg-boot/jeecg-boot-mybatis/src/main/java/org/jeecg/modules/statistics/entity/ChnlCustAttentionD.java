package org.jeecg.modules.statistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 渠道关注日统计表，保存每天各个渠道的医生用户关注数量。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tm_chnl_cust_attention_d")
public class ChnlCustAttentionD implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private Integer statDt;

    /**
     * 渠道标识
     */
    private String sceneId;

    /**
     * 总关注数
     */
    private Integer attenTotalCnt;

    /**
     * 流失关注数
     */
    private Integer attenLostCnt;

    /**
     * 净增关注数
     */
    private Integer attenIncreCnt;

    /**
     * 新增关注数
     */
    private Integer attenAddCnt;


}

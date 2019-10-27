package org.jeecg.modules.statistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 渠道关注时统计表，存储每日偶数时点关注数据。每偶数整点增量统计。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tm_chnl_attention_h")
public class ChnlAttentionH implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道标识
     */
    private String sceneId;

    /**
     * 统计日期，日期格式：yyyymmdd
     */
    private Integer statDt;

    /**
     * 时，24小时格式。
     */
    private String hour;

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

    /**
     * 曲线图打点时刻
     */
    private Long pointTime;


}

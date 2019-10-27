package org.jeecg.modules.qasystem.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用于问答模块，记录常见问题。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_qa_question")
public class QaQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 问题唯一标识
     */
    @TableId(value = "QUESTION_ID", type = IdType.AUTO)
    private Integer questionId;

    /**
     * 问题内容
     */
    private String content;

    /**
     * 创建时间，时间格式：yyyyMMdd HH:mm:ss
     */
    private Date createTime;

    /**
     * 问题阅读数
     */
    private Integer readCnt;

    /**
     * 阅读价格，0元表示免费。
     */
    private BigDecimal price;

    /**
     * 问题主题编码   0：儿科问题 1：妇科问题 2：营养咨询 3：心理咨询
     */
    private Integer questionTopicCode;

    /**
     * 排序号，序号越大越靠前。
     */
    private Integer sortNo;

    /**
     * 问题描述对象  -1：无   0：宝宝  1：孕妇  
     */
    private Integer suitableObject;

    /**
     * 问题描述对象简述，性别/年龄等信息
     */
    private String suitableObjDesc;


}

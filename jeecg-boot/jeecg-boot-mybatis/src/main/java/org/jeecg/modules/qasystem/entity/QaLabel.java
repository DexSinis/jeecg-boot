package org.jeecg.modules.qasystem.entity;

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
 * 使用问答模块，存储所有标签。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_qa_label")
public class QaLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LABEL_ID", type = IdType.AUTO)
    private Integer labelId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 创建时间
     */
    private Date createTime;


}

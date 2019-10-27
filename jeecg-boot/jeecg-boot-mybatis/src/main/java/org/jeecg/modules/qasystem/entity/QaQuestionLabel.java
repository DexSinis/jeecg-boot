package org.jeecg.modules.qasystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 适用问答模块，用于保存问题归属的标签。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_qa_question_label")
public class QaQuestionLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer questionId;

    private Integer labelId;


}

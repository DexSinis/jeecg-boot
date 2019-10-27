package org.jeecg.modules.qasystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-17
 */
@TableName("tb_qa_answer")
public class QaAnswer extends Model<QaAnswer> {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer questionId;

    /**
     * 问题答案
     */
    private String answer;

    /**
     * 解答者ID
     */
    private Integer answererId;

    /**
     * 解答时间
     */
    private Date createTime;

    /**
     * 认为有用数量
     */
    private Integer usefulCnt;

    /**
     * 答案编辑人
     */
    private String editor;


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getAnswererId() {
        return answererId;
    }

    public void setAnswererId(Integer answererId) {
        this.answererId = answererId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUsefulCnt() {
        return usefulCnt;
    }

    public void setUsefulCnt(Integer usefulCnt) {
        this.usefulCnt = usefulCnt;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    protected Serializable pkVal() {
        return this.questionId;
    }

    @Override
    public String toString() {
        return "QaAnswer{" +
        "questionId=" + questionId +
        ", answer=" + answer +
        ", answererId=" + answererId +
        ", createTime=" + createTime +
        ", usefulCnt=" + usefulCnt +
        ", editor=" + editor +
        "}";
    }
}

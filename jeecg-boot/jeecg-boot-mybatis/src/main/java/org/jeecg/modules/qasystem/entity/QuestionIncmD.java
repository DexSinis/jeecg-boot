package org.jeecg.modules.qasystem.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 问答访问量及销售额日统计表，适用问答模块，保存每个问题的访问量以及销售额信息。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-19
 */
@TableName("tm_qa_question_incm_d")
public class QuestionIncmD extends Model<QuestionIncmD> {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期，格式：yyyyMMdd
     */
    private Integer statDt;

    private Integer questionId;

    /**
     * 阅读次数
     */
    private Integer pv;

    /**
     * 阅读用户数
     */
    private Integer uv;

    /**
     * 累计销售金额，单位：元。
     */
    private BigDecimal income;


    public Integer getStatDt() {
        return statDt;
    }

    public void setStatDt(Integer statDt) {
        this.statDt = statDt;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    protected Serializable pkVal() {
        return this.statDt;
    }

    @Override
    public String toString() {
        return "QuestionIncmD{" +
        "statDt=" + statDt +
        ", questionId=" + questionId +
        ", pv=" + pv +
        ", uv=" + uv +
        ", income=" + income +
        "}";
    }
}

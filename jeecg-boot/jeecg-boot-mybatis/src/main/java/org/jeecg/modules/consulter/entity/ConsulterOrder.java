package org.jeecg.modules.consulter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 工单表
 * </p>
 *
 * @author DexSinis
 * @since 2019-05-14
 */
@TableName("consulter_order")
public class ConsulterOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发起人id
     */
    private Integer consulterId;

    /**
     * 当前客服id
     */
    private Integer customerId;

    private Integer evaluationId;

    private Integer iseval;

    /**
     * 0 未接 1 以接 2 转接中
     */
    private Integer status;

    /**
     * 0 未结束 1 结束
     */
    private Integer serverStatus;

    private Long createTime;

    /**
     * 首次响应时间
     */
    private Long ackTime;

    private Long askTime;

    private String bMark;

    /**
     * 最后转接时间
     */
    private Long lastforwardTime;

    /**
     * 标记商户的工单是否超过24小时，0：否，1：是
     */
    private Integer isBmarkOver;

    /**
     * 商户工单的服务次数
     */
    private Integer serviceTimes;

    /**
     * 是否已经发送短信 0 未发 1 已发
     */
    private Integer sendedSms;

    /**
     * 邀请评价时间
     */
    private Long inviteEvaluationTime;

    /**
     * 是否已发送转单提示0，否  1，是
     */
    private Integer isforwardNotice;

    private Integer channelType;

    /**
     * mama回复id
     */
    private String replyId;

    /**
     * 工单所属科室类型，0 宝宝类， 1 妇产科
     */
    private Integer consulteType;

    /**
     * 注册提醒 0 未提醒，1已提醒
     */
    private Integer isregNotice;

    /**
     * 发送给医生的提醒0 未提醒 1已提醒
     */
    private Integer iscusNotice;

    /**
     * 是否发送6分钟未回复提示 0 未发送 1 已发送
     */
    private Integer issixNotice;

    /**
     * 用户3分钟未回复是否已发模板消息0未发，1 已发
     */
    private Integer threeConsulter;

    /**
     * 用户10分钟未回复是否已发模板消息0未发，1 已发
     */
    private Integer tenConsulter;

    /**
     * 推送的用户
     */
    private String pushUsers;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 应用id
     */
    private Integer appid;

    private Integer problem;

    /**
     * 推送次数
     */
    private Integer next;

    /**
     * 用户2分钟未回复是否已发模板消息0未发，1 已发
     */
    private Integer twoConsulter;

    /**
     * 用户5分钟未回复是否已发系统消息0未发，1 已发
     */
    private Integer fiveConsulter;

    /**
     * 是否已经发送短信 0 未发 1 已发
     */
    private Integer scanSms;

    private Long answerTime;

    /**
     * 0 派单 1指定接单
     */
    private Integer isScan;

    private String servVersion;

    private String notifyRecord;

    /**
     * 是否付费工单0，否；1-是
     */
    private Integer isPayed;

    /**
     * 咨询问题主题编码
0：儿科问题 1：妇科问题 2：营养咨询  3：心理咨询
     */
    private Integer questionTopicCode;

    /**
     * 工单类型编码
0：快速咨询工单 1：明星医生工单 2：营养师工单 3：心理咨询工单
     */
    private Integer orderType;

    /**
     * 工单首次分发类型
0：定向分发  1：非定向分发
     */
    private Integer firstDistributeType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsulterId() {
        return consulterId;
    }

    public void setConsulterId(Integer consulterId) {
        this.consulterId = consulterId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getIseval() {
        return iseval;
    }

    public void setIseval(Integer iseval) {
        this.iseval = iseval;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(Integer serverStatus) {
        this.serverStatus = serverStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getAckTime() {
        return ackTime;
    }

    public void setAckTime(Long ackTime) {
        this.ackTime = ackTime;
    }

    public Long getAskTime() {
        return askTime;
    }

    public void setAskTime(Long askTime) {
        this.askTime = askTime;
    }

    public String getbMark() {
        return bMark;
    }

    public void setbMark(String bMark) {
        this.bMark = bMark;
    }

    public Long getLastforwardTime() {
        return lastforwardTime;
    }

    public void setLastforwardTime(Long lastforwardTime) {
        this.lastforwardTime = lastforwardTime;
    }

    public Integer getIsBmarkOver() {
        return isBmarkOver;
    }

    public void setIsBmarkOver(Integer isBmarkOver) {
        this.isBmarkOver = isBmarkOver;
    }

    public Integer getServiceTimes() {
        return serviceTimes;
    }

    public void setServiceTimes(Integer serviceTimes) {
        this.serviceTimes = serviceTimes;
    }

    public Integer getSendedSms() {
        return sendedSms;
    }

    public void setSendedSms(Integer sendedSms) {
        this.sendedSms = sendedSms;
    }

    public Long getInviteEvaluationTime() {
        return inviteEvaluationTime;
    }

    public void setInviteEvaluationTime(Long inviteEvaluationTime) {
        this.inviteEvaluationTime = inviteEvaluationTime;
    }

    public Integer getIsforwardNotice() {
        return isforwardNotice;
    }

    public void setIsforwardNotice(Integer isforwardNotice) {
        this.isforwardNotice = isforwardNotice;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public Integer getConsulteType() {
        return consulteType;
    }

    public void setConsulteType(Integer consulteType) {
        this.consulteType = consulteType;
    }

    public Integer getIsregNotice() {
        return isregNotice;
    }

    public void setIsregNotice(Integer isregNotice) {
        this.isregNotice = isregNotice;
    }

    public Integer getIscusNotice() {
        return iscusNotice;
    }

    public void setIscusNotice(Integer iscusNotice) {
        this.iscusNotice = iscusNotice;
    }

    public Integer getIssixNotice() {
        return issixNotice;
    }

    public void setIssixNotice(Integer issixNotice) {
        this.issixNotice = issixNotice;
    }

    public Integer getThreeConsulter() {
        return threeConsulter;
    }

    public void setThreeConsulter(Integer threeConsulter) {
        this.threeConsulter = threeConsulter;
    }

    public Integer getTenConsulter() {
        return tenConsulter;
    }

    public void setTenConsulter(Integer tenConsulter) {
        this.tenConsulter = tenConsulter;
    }

    public String getPushUsers() {
        return pushUsers;
    }

    public void setPushUsers(String pushUsers) {
        this.pushUsers = pushUsers;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public Integer getProblem() {
        return problem;
    }

    public void setProblem(Integer problem) {
        this.problem = problem;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getTwoConsulter() {
        return twoConsulter;
    }

    public void setTwoConsulter(Integer twoConsulter) {
        this.twoConsulter = twoConsulter;
    }

    public Integer getFiveConsulter() {
        return fiveConsulter;
    }

    public void setFiveConsulter(Integer fiveConsulter) {
        this.fiveConsulter = fiveConsulter;
    }

    public Integer getScanSms() {
        return scanSms;
    }

    public void setScanSms(Integer scanSms) {
        this.scanSms = scanSms;
    }

    public Long getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Long answerTime) {
        this.answerTime = answerTime;
    }

    public Integer getIsScan() {
        return isScan;
    }

    public void setIsScan(Integer isScan) {
        this.isScan = isScan;
    }

    public String getServVersion() {
        return servVersion;
    }

    public void setServVersion(String servVersion) {
        this.servVersion = servVersion;
    }

    public String getNotifyRecord() {
        return notifyRecord;
    }

    public void setNotifyRecord(String notifyRecord) {
        this.notifyRecord = notifyRecord;
    }

    public Integer getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }

    public Integer getQuestionTopicCode() {
        return questionTopicCode;
    }

    public void setQuestionTopicCode(Integer questionTopicCode) {
        this.questionTopicCode = questionTopicCode;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getFirstDistributeType() {
        return firstDistributeType;
    }

    public void setFirstDistributeType(Integer firstDistributeType) {
        this.firstDistributeType = firstDistributeType;
    }

    @Override
    public String toString() {
        return "ConsulterOrder{" +
        "id=" + id +
        ", consulterId=" + consulterId +
        ", customerId=" + customerId +
        ", evaluationId=" + evaluationId +
        ", iseval=" + iseval +
        ", status=" + status +
        ", serverStatus=" + serverStatus +
        ", createTime=" + createTime +
        ", ackTime=" + ackTime +
        ", askTime=" + askTime +
        ", bMark=" + bMark +
        ", lastforwardTime=" + lastforwardTime +
        ", isBmarkOver=" + isBmarkOver +
        ", serviceTimes=" + serviceTimes +
        ", sendedSms=" + sendedSms +
        ", inviteEvaluationTime=" + inviteEvaluationTime +
        ", isforwardNotice=" + isforwardNotice +
        ", channelType=" + channelType +
        ", replyId=" + replyId +
        ", consulteType=" + consulteType +
        ", isregNotice=" + isregNotice +
        ", iscusNotice=" + iscusNotice +
        ", issixNotice=" + issixNotice +
        ", threeConsulter=" + threeConsulter +
        ", tenConsulter=" + tenConsulter +
        ", pushUsers=" + pushUsers +
        ", labelId=" + labelId +
        ", appid=" + appid +
        ", problem=" + problem +
        ", next=" + next +
        ", twoConsulter=" + twoConsulter +
        ", fiveConsulter=" + fiveConsulter +
        ", scanSms=" + scanSms +
        ", answerTime=" + answerTime +
        ", isScan=" + isScan +
        ", servVersion=" + servVersion +
        ", notifyRecord=" + notifyRecord +
        ", isPayed=" + isPayed +
        ", questionTopicCode=" + questionTopicCode +
        ", orderType=" + orderType +
        ", firstDistributeType=" + firstDistributeType +
        "}";
    }
}

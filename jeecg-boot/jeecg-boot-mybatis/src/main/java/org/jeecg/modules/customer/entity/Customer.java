package org.jeecg.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 医生，客服表
 * </p>
 *
 * @author DexSinis
 * @since 2019-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String phone;

    private String password;

    private String chatpd;

    private String nickname;

    private String headurl;

    private String location;

    private String company;

    private String department;

    private String memo;

    private String token;

    private String cardNo;

    private String cardName;

    private String cardAccount;

    /**
     * 0 无认证 1一类 2二类
     */
    private Integer type;

    /**
     * 0：正常 1：冻结
     */
    private Integer lock;

    private String cardurl;

    private String cerNo;

    private String cerurlFront;

    private String cerurlBack;

    private String othurl;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 接单工资
     */
    private Double salary;

    /**
     * 奖励金额
     */
    private Double bonus;

    /**
     * 身份证号
     */
    private String cardNum;

    /**
     * 注册时间
     */
    private Long createTime;

    /**
     * 存放医生设置是否在线，0:离线，1:在线
     */
    private Integer isonline;

    /**
     * 平均首次响应时间
     */
    @TableField("perTime")
    private Double perTime;

    /**
     * 好评率
     */
    @TableField("perGoods")
    private Double perGoods;

    /**
     * 认证时间
     */
    private Long validateTime;

    /**
     * 工作年限 -1：未填写，0：1-3年，1：3-5年，2：5-10年，3：10年以上
     */
    private Integer workYears;

    /**
     * 学历 -1：未填写，0：大专，1：本科，2：硕士，3：博士
     */
    private Integer education;

    /**
     * 银行卡图片
     */
    private String cardImg;

    /**
     * 注册来源0:app,1:html5
     */
    private Integer regFrom;

    /**
     * 标签
     */
    private String tags;

    /**
     * 二维码编码
     */
    private String qrcode;

    /**
     * 商户标识
     */
    private String bMark;

    /**
     * 职称
     */
    private String title;

    /**
     * 身份证背面图
     */
    private String cardurlBack;

    /**
     * 性别 0 女  1 男
     */
    private Integer sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 擅长标签
     */
    private String goodLabel;

    /**
     * 是否星级医生
     */
    private Integer isStar;

    private String isGoodDesc;

    private String selfGoodLabel;

    private Integer totalnum;

    /**
     * 医生角色编码：
1：普通医生 2：明星医生(暂不启用) 3：营养师  4：家庭指导师
     */
    private Integer customerRole;

    /**
     * 排序序号
     */
    private Integer sortSeq;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

package org.jeecg.modules.statistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;
import org.jeecg.util.date.DateUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 用户日统计表，保存最新用户数据。
 * </p>
 *
 * @author DexSinis
 * @since 2019-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tw_pers_user_d")
public class PersUserD implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期，即统计的数据日期
     */
    private Integer statDt;

    /**
     * 用户标识
     */
    private Integer consulterId;

    private Integer provinceId;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 区县
     */
    private Integer areaId;

    /**
     * 性别，0：女  1：男  2：未知 3 :未知
     */
    private String sex;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 怀孕状态，1：备孕中  2：已怀孕  3：有宝宝
     */
    private String pregnantState;

    /**
     * 所属商户
     */
    private String bMark;

    /**
     * 用户二维码的编码
     */
    private String qrcode;

    /**
     * 用户专属医生，多个专属医生用英文逗号隔开。
     */
    private String exclusiveCustomerId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户详细地址
     */
    private String address;

    /**
     * 用户手机号码
     */
    private String phone;

    /**
     * 是否被投诉过，0：否  1：是
     */
    private Integer isComplainted;

    /**
     * 是否冻结 ，0：未冻结  1：已冻结
     */
    private Integer isFrozen;

    public PersUserD(Map map){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.setStatDt((Integer) map.get("statDt"));
        this.setConsulterId((Integer) map.get("CONSULTER_ID"));
        this.setProvinceId(map.get("PROVINCE_ID")==null||"".equals(map.get("PROVINCE_ID"))?null:((Long) map.get("PROVINCE_ID")).intValue());
        this.setCityId(map.get("CITY_ID")==null||"".equals(map.get("CITY_ID"))?null:((Long) map.get("CITY_ID")).intValue());
        this.setAreaId(map.get("AREA_ID")==null||"".equals(map.get("AREA_ID"))?null:Integer.valueOf((String) map.get("AREA_ID")));
        this.setSex(map.get("SEX")==null||"".equals(map.get("SEX"))?"2":map.get("SEX").toString());
        this.setRegTime(StringUtils.isBlank((String) map.get("REG_TIME"))?null: DateUtil.formatStrToDate((String)map.get("REG_TIME"),"yyyy-MM-dd HH:mm:ss"));
        this.setBirthday(map.get("BIRTHDAY")==null?null:new Date(((Timestamp) map.get("BIRTHDAY")).getTime()));
        this.setPregnantState(String.valueOf(map.get("PREGNANT_STATE")));
        this.setBMark((String) map.get("B_MARK"));
        this.setQrcode((String) map.get("QRCODE"));
        this.setPhone((String) map.get("PHONE"));
        this.setQrcode((String) map.get("ADDRESS"));
        this.setNickName((String) map.get("NICK_NAME"));
        this.setIsComplainted((Integer) map.get("IS_COMPLAINTED"));
        this.setIsFrozen((Integer) map.get("IS_FROZEN"));
        this.setExclusiveCustomerId((String) map.get("EXCLUSIVE_CUSTOMER_ID"));
    }

}

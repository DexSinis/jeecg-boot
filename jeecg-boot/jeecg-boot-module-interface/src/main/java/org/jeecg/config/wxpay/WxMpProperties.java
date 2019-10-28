package org.jeecg.config.wxpay;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wxpay pay properties
 *
 * @author Binary Wang
 */
@ConfigurationProperties(prefix = "wx.mp")
@Data
public class WxMpProperties {
  /**
   * 设置微信公众号或者小程序等的appid
   */
  private String appId;

  /**
   * 开发者密码
   */
  private String secret;


  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this,
        ToStringStyle.MULTI_LINE_STYLE);
  }
}

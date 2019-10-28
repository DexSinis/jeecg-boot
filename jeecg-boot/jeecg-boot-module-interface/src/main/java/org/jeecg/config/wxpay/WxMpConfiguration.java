package org.jeecg.config.wxpay;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.config.wxpay.WxPayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxMpService.class)
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
  private WxMpProperties properties;

  @Autowired
  public WxMpConfiguration(WxMpProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxMpService wxMpService() {
    WxMpDefaultConfigImpl wxMpConfig = new WxMpDefaultConfigImpl();
    wxMpConfig.setAppId(this.properties.getAppId());
    wxMpConfig.setSecret(this.properties.getSecret());
    WxMpService wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(wxMpConfig);
    return wxMpService;
  }

}

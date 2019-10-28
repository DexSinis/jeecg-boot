package org.jeecg.modules.wxmp.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
@RequestMapping("/wxmp")
@Slf4j
public class WxMpController {

    @Value("${server.host}")
    private String serverHost;
    @Value("${server.servlet.context-path}")
    private String serverPath;

    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/authorize")
    public String authorize(HttpServletResponse response, @RequestParam("returnUrl") String returnUrl) throws Exception {
        String url = serverHost+serverPath+"/wxmp/userInfo";
        returnUrl = serverHost+serverPath+returnUrl;
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.error("【微信网页授权】获取code,redirectURL={}", redirectURL);
        response.sendRedirect(URLEncoder.encode(returnUrl));
        return "";
    }


    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl,
                           HttpServletResponse response) throws Exception {
        log.info("【微信网页授权】code={}", code);
        log.info("【微信网页授权】state={}", returnUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            log.error("wxMpService-----"+ JSONObject.toJSONString(wxMpService.getWxMpConfigStorage()));
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (Exception e) {
            log.info("【微信网页授权】{}", e);
            throw new Exception(e.getMessage());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.error("【微信网页授权】openId={}", openId);

        response.sendRedirect(returnUrl + "&openid=" + openId);
        return "";
    }
}

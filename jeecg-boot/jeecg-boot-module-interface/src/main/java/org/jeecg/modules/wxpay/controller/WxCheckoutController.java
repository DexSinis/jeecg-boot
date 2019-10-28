package org.jeecg.modules.wxpay.controller;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.aop.PassToken;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.constants.ConstantInterface;
import org.jeecg.modules.cas.util.XMLUtil;
import org.jeecg.modules.consulter.entity.Consulter;
import org.jeecg.modules.consulter.service.ConsulterService;
import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.demo.test.service.IJeecgDemoService;
import org.jeecg.modules.order.entity.ConsulterOrderPkgRel;
import org.jeecg.modules.order.service.ConsulterOrderPkgRelService;
import org.jeecg.modules.wxpay.entity.PayOrders;
import org.jeecg.modules.order.entity.PayTempOrder;
import org.jeecg.modules.order.service.PayOrdersService;
import org.jeecg.modules.order.service.PayTempOrderService;
import org.jeecg.modules.vip.entity.*;
import org.jeecg.modules.vip.service.*;
import org.jeecg.modules.vip.vo.TpPricingPackageSame;
import org.jeecg.modules.wxpay.entity.PayOrderRela;
import org.jeecg.modules.wxpay.service.PayOrderRelaService;
import org.jeecg.util.response.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 测试demo
 * @Author: yewyw
 * @Date:2018-12-29
 * @Version:V1.0
 */
@Slf4j
@Api(tags="微信支付平台")
@RestController
@RequestMapping("/wxcheckout")
public class WxCheckoutController extends JeecgController<JeecgDemo,IJeecgDemoService> {

	@Value("${server.host}")
	private String serverHost;
	@Value("${server.servlet.context-path}")
	private String serverPath;

	@Value("${wx.pay.spbillCreateIp}")
	private String spbillCreateIp;

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WxPayService wxService;

	@Autowired
	private ConsulterService consulterService;

	@Autowired
	private PayTempOrderService payTempOrderService;

	@Resource
	private TpPricingPackageService tpPricingPackageService;

	@Resource
	private ConsulterOrderPkgPayService consulterOrderPkgPayService;

	@Resource
	private ConsulterPricingPackageService consulterPricingPackageService;

	@Resource
	private ConsulterVipService consulterVipService;

	@Resource
	private PayOrderRelaService payOrderRelaService;

	@Resource
	private PayOrdersService payOrdersService;

	@Resource
	private TpPricingPkgResService tpPricingPkgResService;

	@Resource
	private ConsulterOrderPkgRelService consulterOrderPkgRelService;





	@GetMapping("/authorize")
	@PassToken(required=true)
	public String authorize(HttpServletResponse response,@RequestParam("returnUrl") String returnUrl) throws Exception {
		String url = serverHost+serverPath+"/wxcheckout/userInfo";
		returnUrl = serverHost+serverPath+returnUrl;
		String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
		log.error("【微信网页授权】获取code,redirectURL={}", redirectURL);
		response.sendRedirect(redirectURL);
		return "";
	}


	@GetMapping("/userInfo")
	@PassToken(required=true)
	public String userInfo(@RequestParam("code") String code,
						   @RequestParam("state") String returnUrl,
						   HttpServletResponse response) throws Exception {


		log.info("【微信网页授权】code={}", code);
		log.info("【微信网页授权】state={}", returnUrl);
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
		try {
			log.error("wxMpService-----"+JSONObject.toJSONString(wxMpService.getWxMpConfigStorage()));
			wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		} catch (Exception e) {
			log.info("【微信网页授权】{}", e);
			throw new Exception(e.getMessage());
		}
		String openId = wxMpOAuth2AccessToken.getOpenId();
		log.error("【微信网页授权】openId={}", openId);
//		WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,"zh_CN");
//		log.error("response-------"+JSONObject.toJSONString(wxMpUser));


		response.sendRedirect(returnUrl + "&openid=" + openId);
		return "";
	}

	@GetMapping(value = "/checkoutOld")
	@PassToken(required=true)
	@Transactional
	public ModelAndView checkoutOld(HttpServletRequest request,
								 @RequestParam("pricingPkgId") String pricingPkgId,
								 @RequestParam("openid") String openid,
								 @RequestParam("consulterId") String consulterId,
								 @RequestParam("bmark") String bmark,
								 @RequestParam("appid") String appid,
								 @RequestParam("flag") String flag,
								 @RequestParam(value = "payType", required = false, defaultValue = "0") String payType

	) throws Exception {

		if(payType.equals(ConstantInterface.payType.payType0)){
			ModelAndView mv = new ModelAndView();
			TpPricingPackage tpPricingPackage = tpPricingPackageService.getById(pricingPkgId);
			Consulter consulter = consulterService.getById(Integer.valueOf(consulterId));
			QueryWrapper<ConsulterVip> wrapper = new QueryWrapper<ConsulterVip>();
			wrapper.eq("vip_phone",consulter.getPhone());
			ConsulterVip consulterVip = consulterVipService.getOne(wrapper);
			if(consulterVip==null){
				Map mapConsulterVip = new HashMap();
				mapConsulterVip.put("vip_phone",consulter.getPhone());
				mapConsulterVip.put("create_time",new Date());
				mapConsulterVip.put("status", ConstantInterface.Status.OFF);
				mapConsulterVip.put("regChnl",bmark);
				mapConsulterVip.put("eff_time",new Date());
				mapConsulterVip.put("exp_time",new Date());
				consulterVip = new ConsulterVip(mapConsulterVip);
				consulterVipService.save(consulterVip);
			}

			mv.addObject("openid",openid);
			mv.addObject("bmark",bmark);
			mv.addObject("appid",appid);
			mv.addObject("flag",flag);
			mv.addObject("forWordUrl",serverHost+"/index.html?bmark="+bmark+"&appid="+appid+"&flag="+flag+"#/MyEquity");

			mv.addObject("tpPricingPackage",tpPricingPackage);
			mv.addObject("vipPhone",consulter.getPhone());
			mv.addObject("bMark",bmark);
			mv.setViewName("modules/wxpay/view/checkoutPackage");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView();
			TpPricingPackage tpPricingPackage = tpPricingPackageService.getById(pricingPkgId);
			Consulter consulter = consulterService.getById(Integer.valueOf(consulterId));
			QueryWrapper<ConsulterVip> wrapper = new QueryWrapper<ConsulterVip>();
			wrapper.eq("vip_phone",consulter.getPhone());
			ConsulterVip consulterVip = consulterVipService.getOne(wrapper);
			if(consulterVip==null){
				Map mapConsulterVip = new HashMap();
				mapConsulterVip.put("vip_phone",consulter.getPhone());
				mapConsulterVip.put("create_time",new Date());
				mapConsulterVip.put("status", ConstantInterface.Status.OFF);
				mapConsulterVip.put("regChnl",bmark);
				mapConsulterVip.put("eff_time",new Date());
				mapConsulterVip.put("exp_time",new Date());
				consulterVip = new ConsulterVip(mapConsulterVip);
				consulterVipService.save(consulterVip);
			}

			mv.addObject("openid",openid);
			mv.addObject("bmark",bmark);
			mv.addObject("appid",appid);
			mv.addObject("flag",flag);
			mv.addObject("forWordUrl",serverHost+"/index.html?bmark="+bmark+"&appid="+appid+"&flag="+flag+"#/MyEquity");

			mv.addObject("tpPricingPackage",tpPricingPackage);
			mv.addObject("vipPhone",consulter.getPhone());
			mv.addObject("bMark",bmark);
			mv.setViewName("modules/wxpay/view/checkoutConsult");
			return mv;
		}


	}

	@ApiOperation(value = "跳转支付页面")
	@GetMapping(value = "/checkout")
	@PassToken(required=true)
	@Transactional
	public ModelAndView checkout(HttpServletRequest request){
		String consulterId = request.getParameter("consulterId");
		String bMark = request.getParameter("bmark");
		String appId = request.getParameter("appid");
		String flag = request.getParameter("flag");
		String openid = request.getParameter("openid")==null?"oDCcft3x4mGXKTRTbw6vuNuVrmsA":request.getParameter("openid");
		String orderType = request.getParameter("orderType");
		String payTempOrderId = request.getParameter("payTempOrderId");
		String payType = request.getParameter("payType");
		String pricingPkgId = request.getParameter("pricingPkgId");

		log.error("consulterId--------"+consulterId);
		log.error("bMark--------"+bMark);
		log.error("appId--------"+appId);
		log.error("flag--------"+flag);
		log.error("openid--------"+openid);
		log.error("orderType--------"+orderType);
		log.error("payTempOrderId--------"+payTempOrderId);
		log.error("payType--------"+payType);
		log.error("pricingPkgId--------"+pricingPkgId);

		String leftUrl = ""; //向后
		String rightUrl = serverHost+"/index.html?bmark="+bMark+"&appid="+appId+"&flag="+flag;

		Consulter consulter = consulterService.getById(Integer.valueOf(consulterId));
		QueryWrapper<ConsulterVip> wrapper = new QueryWrapper<ConsulterVip>();
		wrapper.eq("vip_phone",consulter.getPhone());
		ConsulterVip consulterVip = consulterVipService.getOne(wrapper);
		if(consulterVip==null){
			Map mapConsulterVip = new HashMap();
			mapConsulterVip.put("vip_phone",consulter.getPhone());
			mapConsulterVip.put("create_time",new Date());
			mapConsulterVip.put("status", ConstantInterface.Status.OFF);
			mapConsulterVip.put("regChnl",bMark);
			mapConsulterVip.put("eff_time",new Date());
			mapConsulterVip.put("exp_time",new Date());
			mapConsulterVip.put("consulter_id",consulterId);
			consulterVip = new ConsulterVip(mapConsulterVip);
			consulterVipService.save(consulterVip);
		}

		if(payType.equals(ConstantInterface.payType.payType0)){
			ModelAndView mv = new ModelAndView();
			TpPricingPackage tpPricingPackage = tpPricingPackageService.getById(pricingPkgId);
			Map payParams = new HashMap();
			payParams.put("consulterId",consulterId);
			payParams.put("openid",openid);
			payParams.put("bMark",bMark);
			payParams.put("appId",appId);
			payParams.put("flag",flag);
			payParams.put("payType",payType);
			payParams.put("rightUrl",rightUrl+"#/MyEquity");
			payParams.put("tpPricingPackage",tpPricingPackage);
			payParams.put("vipPhone",consulter.getPhone());
			mv.addObject("payParams",JSONObject.toJSON(payParams));
			mv.setViewName("modules/wxpay/view/checkoutPackage");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView();
			String nickName = "---";
			String headUrl = "";
			Map payParams = new HashMap();
			PayTempOrder payTempOrder  = payTempOrderService.getById(Integer.valueOf(payTempOrderId));
			if (payTempOrder != null && StringUtils.isNotBlank(payTempOrder.getBusiTradeNo())) {
				payParams.put("payTempOrder", payTempOrder);
				payParams.put("payTempOrderId", payTempOrder.getId());
				QueryWrapper<PayOrderRela> wrapperPayOrderRela = new QueryWrapper<PayOrderRela>();
				wrapperPayOrderRela.eq("busi_trade_no",payTempOrder.getBusiTradeNo());
				PayOrderRela payOrderRela =  payOrderRelaService.getOne(wrapperPayOrderRela);
				if (payOrderRela != null) {
					payParams.put("payOrderRela", payOrderRela);
				}
			}
			if (payTempOrder.getStatus().toString().equals(ConstantInterface.PayTempOrderStatus.PayTempOrderStatusCenter)) {
				rightUrl = rightUrl + "#/ChatData?&backUrl=index&customerId="+payTempOrder.getCustomerId()+"&paytemporderid="+payTempOrder.getId()+"&consulttype="+payTempOrder.getConsulteType();
			} else {
				rightUrl = rightUrl + "#/Chat?&backUrl=index&customerId="+payTempOrder.getCustomerId()+"&paytemporderid="+payTempOrder.getId()+"&consulttype="+payTempOrder.getConsulteType();
			}

			List<TpPricingPackageSame> tpPricingPackages = tpPricingPackageService.queryScaleCnt();
			payParams.put("nickName",nickName);
			payParams.put("headUrl",headUrl);
			payParams.put("consulterId",consulterId);
			payParams.put("bMark",bMark);
			payParams.put("appId",appId);
			payParams.put("flag",flag);
			payParams.put("openid",openid);
			payParams.put("orderType",orderType);
			payParams.put("payType",payType);
			payParams.put("rightUrl",rightUrl);

			for (TpPricingPackageSame tpPricingPackageSame:tpPricingPackages) {
				String composePrice = tpPricingPackageSame.getSalePrice();
				if(orderType.equals(ConstantInterface.orderTypeRoServiceId.NUTRITION_GUIDANCE_ORDER)
						||orderType.equals(ConstantInterface.orderTypeRoServiceId.PSYCHOLOGICAL_COUNSELING_ORDER)
						||orderType.equals(ConstantInterface.orderTypeRoServiceId.NUTRITION_QUICK_ORDER)
						||orderType.equals(ConstantInterface.orderTypeRoServiceId.PSYCHOLOGICAL_QUCIK_ORDER)) {
					if(payTempOrder!=null){
						QueryWrapper<TpPricingPkgRes> wrapperTpPricingPkgRes = new QueryWrapper<TpPricingPkgRes>();
						wrapperTpPricingPkgRes.eq("pricing_pkg_id",tpPricingPackageSame.getPricingPkgId());
						wrapperTpPricingPkgRes.eq("service_id",orderType);
						TpPricingPkgRes tpPricingPkgRes=  tpPricingPkgResService.getOne(wrapperTpPricingPkgRes);
						if(tpPricingPkgRes!=null){
							composePrice = new BigDecimal(composePrice).add(payTempOrder.getFee().multiply(tpPricingPkgRes.getDiscountRate()).multiply(BigDecimal.valueOf(0.01)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).toString();
						}
					}
				}
				tpPricingPackageSame.setComposePrice(composePrice);
			}

			payParams.put("tpPricingPackages",tpPricingPackages);
			log.error("tpPricingPackages----------------"+JSONObject.toJSONString(tpPricingPackages));
			mv.addObject("payParams",JSONObject.toJSON(payParams));
			log.error("payParams----------------"+JSONObject.toJSONString(payParams));
			mv.setViewName("modules/wxpay/view/checkoutnew");
			return mv;
		}



	}

	@ApiOperation(value = "选择支付类型")
	@PostMapping(value = "/channelChange")
	@PassToken(required=true)
	@Transactional
	public ServiceResult channelChange(HttpServletRequest request){

		String payParamJson = request.getParameter("payParam");
		Map payParam = (Map) JSONObject.parse(payParamJson);
		String consulterId = payParam.get("consulterId").toString();
		String bMark = payParam.get("bMark").toString();
		String appId = payParam.get("appId").toString();
		String flag = payParam.get("flag").toString();
		String openid =payParam.get("openid")==null?null:payParam.get("openid").toString();
		String orderType = payParam.get("orderType").toString();
		String payTempOrderId = payParam.get("payTempOrderId")==null?"":payParam.get("payTempOrderId").toString();
		String payType = payParam.get("payType").toString();
		String pricingPkgId = payParam.get("pricingPkgId")==null?null:payParam.get("pricingPkgId").toString();
		log.error("consulterId--------"+consulterId);
		log.error("bMark--------"+bMark);
		log.error("appId--------"+appId);
		log.error("flag--------"+flag);
		log.error("openid--------"+openid);
		log.error("orderType--------"+orderType);
		log.error("payTempOrderId--------"+payTempOrderId);
		log.error("payType--------"+payType);
		log.error("pricingPkgId--------"+pricingPkgId);

		String leftUrl = ""; //向后
		String rightUrl = serverHost+"/index.html?bmark="+bMark+"&appid="+appId+"&flag="+flag;
		String nickName = "---";
		String headUrl = "";
		Map payParams = new HashMap();

		PayTempOrder payTempOrder = payTempOrderService.getById(Integer.valueOf(payTempOrderId));
		if (payTempOrder.getStatus().toString().equals(ConstantInterface.PayTempOrderStatus.PayTempOrderStatusCenter)) {
			rightUrl = rightUrl + "#/ChatData?&backUrl=index&customerId="+payTempOrder.getCustomerId()+"&paytemporderid="+payTempOrder.getId()+"&consulttype="+payTempOrder.getConsulteType();
		} else {
			rightUrl = rightUrl + "#/Chat?&backUrl=index&customerId="+payTempOrder.getCustomerId()+"&paytemporderid="+payTempOrder.getId()+"&consulttype="+payTempOrder.getConsulteType();
		}


		if(payType.equals(ConstantInterface.payType.payType0)){
//			TpPricingPackage tpPricingPackage = tpPricingPackageService.getById(pricingPkgId);
//			payParams.put("tpPricingPackage",tpPricingPackage);
			if (payTempOrder != null && StringUtils.isNotBlank(payTempOrder.getBusiTradeNo())) {
				payParams.put("payTempOrder", payTempOrder);
				QueryWrapper<PayOrderRela> wrapperPayOrderRela = new QueryWrapper<PayOrderRela>();
				wrapperPayOrderRela.eq("busi_trade_no", payTempOrder.getBusiTradeNo());
				PayOrderRela payOrderRela = payOrderRelaService.getOne(wrapperPayOrderRela);
				if (payOrderRela != null) {
					payParams.put("payOrderRela", payOrderRela);
				}
			}

			List<TpPricingPackageSame> tpPricingPackages = tpPricingPackageService.queryScaleCnt();
			TpPricingPackageSame tpPricingPackage = tpPricingPackages.stream().filter(x -> x.getPricingPkgId().equals(pricingPkgId)).distinct().collect(Collectors.toList()).get(0);

			String composePrice = tpPricingPackage.getSalePrice();
			if(payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.NUTRITION_GUIDANCE_ORDER)
					||payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.PSYCHOLOGICAL_COUNSELING_ORDER)
					||payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.NUTRITION_QUICK_ORDER)
					||payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.PSYCHOLOGICAL_QUCIK_ORDER)) {
				if(payTempOrder!=null){
					QueryWrapper<TpPricingPkgRes> wrapperTpPricingPkgRes = new QueryWrapper<TpPricingPkgRes>();
					wrapperTpPricingPkgRes.eq("pricing_pkg_id",pricingPkgId);
					wrapperTpPricingPkgRes.eq("service_id",payTempOrder.getOrderType());
					TpPricingPkgRes tpPricingPkgRes=  tpPricingPkgResService.getOne(wrapperTpPricingPkgRes);
					if(tpPricingPkgRes!=null){
						composePrice = new BigDecimal(composePrice).add(payTempOrder.getFee().multiply(tpPricingPkgRes.getDiscountRate()).multiply(BigDecimal.valueOf(0.01)).setScale(2, BigDecimal.ROUND_HALF_DOWN)).toString();
					}
				}
			}
			tpPricingPackage.setComposePrice(composePrice);
			payParams.put("tpPricingPackage",tpPricingPackage);
			log.error("tpPricingPackages----------------"+JSONObject.toJSONString(tpPricingPackage));


		}else{
//			if(orderType.equals(ConstantInterface.orderTypeRoServiceId.QUICK_ORDER)||orderType.equals(ConstantInterface.orderTypeRoServiceId.NUTRITION_QUICK_ORDER)) {
				if (payTempOrder != null && StringUtils.isNotBlank(payTempOrder.getBusiTradeNo())) {
					payParams.put("payTempOrder", payTempOrder);
					QueryWrapper<PayOrderRela> wrapperPayOrderRela = new QueryWrapper<PayOrderRela>();
					wrapperPayOrderRela.eq("busi_trade_no", payTempOrder.getBusiTradeNo());
					PayOrderRela payOrderRela = payOrderRelaService.getOne(wrapperPayOrderRela);
					if (payOrderRela != null) {
						payParams.put("payOrderRela", payOrderRela);
					}
				}
//			}
		}




		payParams.put("nickName",nickName);
		payParams.put("headUrl",headUrl);
		payParams.put("consulterId",consulterId);
		payParams.put("bMark",bMark);
		payParams.put("appId",appId);
		payParams.put("flag",flag);
		payParams.put("openid",openid);
		payParams.put("orderType",orderType);
		payParams.put("rightUrl",rightUrl);
		payParams.put("payTempOrderId",payTempOrderId);
		payParams.put("payType",payType);
		log.error("payParams----------------"+JSONObject.toJSONString(payParams));
		return new ServiceResult().successInterface("选择支付方式成功",payParams);

	}


	@ApiOperation(value = "微信支付回调")
	@PostMapping(value = "/payNotify")
	@PassToken(required=true)
	public String payNotify(HttpServletRequest request) throws Exception {
		log.info("接收到通知");
		JSONObject notifyDataString =null;
		try {
			notifyDataString  = XMLUtil.xmlToJSONObject(request);
			log.error(notifyDataString.toJSONString());
			if(notifyDataString.get("out_trade_no").toString().indexOf(ConstantInterface.payType.payType0)!=-1){
				consulterVipService.payNotify(notifyDataString.get("out_trade_no").toString(),notifyDataString.get("transaction_id").toString()); //购买或者续费之后调用逻辑
			}else {
				consulterVipService.payNotifyConsult(notifyDataString.get("out_trade_no").toString(),notifyDataString.get("transaction_id").toString()); //购买或者续费之后调用逻辑
			}
			return ("<xml>" +
					"  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
					"  <return_msg><![CDATA[OK]]></return_msg>\n" +
					"</xml>");
		}catch (Exception e){
			e.printStackTrace();
			log.info("支付回到处理异常");
			log.error(notifyDataString.toString());
			log.error(e.getMessage());
			return "<xml><return_code><![CDATA[FAIL]]></return_code> <return_msg><![CDATA[报文为空]]></return_msg></xml> ";
		}

	}

	@ApiOperation(value = "获取预支付单号")
	@GetMapping("/getWeChatPreOrder")
	@Transactional
	public String getWeChatPreOrder(HttpServletRequest req) throws WxPayException {

        String outTradeNo =  System.currentTimeMillis() + "";
        String totalFee = "0";
        String openid ="";
		WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest =null ;
		WxPayMpOrderResult result =null ;
		String payParamJson = req.getParameter("payParam");
		Map payParam = (Map) JSONObject.parse(payParamJson);
		log.error("payParam---------",JSONObject.toJSONString(payParam));
		if(payParam.get("payType").equals(ConstantInterface.payType.payType0)){
			Map tpPricingPackageMap = (Map) payParam.get("tpPricingPackage");
			openid = payParam.get("openid").toString();
			String orderType = payParam.get("orderType").toString();
			totalFee = tpPricingPackageMap.get("salePrice").toString();
			String pricingPkgId = tpPricingPackageMap.get("pricingPkgId").toString();
			String bMark = payParam.get("bMark").toString();
			TpPricingPackage tpPricingPackage = tpPricingPackageService.getById(pricingPkgId);
			QueryWrapper<ConsulterVip> wrapper = new QueryWrapper<ConsulterVip>();
			wrapper.eq("consulter_id",payParam.get("consulterId").toString());
			ConsulterVip consulterVip = consulterVipService.getOne(wrapper);

			Map mapConsulterOrderPkgPay = new HashMap();
			mapConsulterOrderPkgPay.put("moible_phone",consulterVip.getVipPhone());
			mapConsulterOrderPkgPay.put("pricing_pkg_id", pricingPkgId);
			mapConsulterOrderPkgPay.put("pay_fee",tpPricingPackage.getSalePrice());
			mapConsulterOrderPkgPay.put("create_time",new Date());
			mapConsulterOrderPkgPay.put("pay_stauts", ConstantInterface.payStauts.UnPay);
			mapConsulterOrderPkgPay.put("b_mark",bMark);
			mapConsulterOrderPkgPay.put("busi_trade_no",0);
			mapConsulterOrderPkgPay.put("pay_fail_desc",0);
			mapConsulterOrderPkgPay.put("pay_chnl", ConstantInterface.payChnl.payChnl0);
			ConsulterOrderPkgPay consulterOrderPkgPay = new ConsulterOrderPkgPay(mapConsulterOrderPkgPay);


			QueryWrapper<ConsulterOrderPkgRel> consulterOrderPkgRelQueryWrapper = new QueryWrapper<ConsulterOrderPkgRel>();
			ConsulterOrderPkgRel consulterOrderPkgRel = null;
			Map payTempOrderMap = (Map) payParam.get("payTempOrder");
			String payTempOrderId = payTempOrderMap.get("id").toString();
			QueryWrapper<PayTempOrder> payTempOrderQueryWrapper = new QueryWrapper<PayTempOrder>();
			payTempOrderQueryWrapper.eq("id",payTempOrderId);
			PayTempOrder payTempOrder = payTempOrderService.getOne(payTempOrderQueryWrapper);
			//计算组合价格
			if(payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.NUTRITION_GUIDANCE_ORDER)
					||payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.PSYCHOLOGICAL_COUNSELING_ORDER)
					||payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.NUTRITION_QUICK_ORDER)
					||payTempOrder.getOrderType().toString().equals(ConstantInterface.orderTypeRoServiceId.PSYCHOLOGICAL_QUCIK_ORDER)) {
				if(payTempOrder!=null){
					QueryWrapper<TpPricingPkgRes> wrapperTpPricingPkgRes = new QueryWrapper<TpPricingPkgRes>();
					wrapperTpPricingPkgRes.eq("pricing_pkg_id",tpPricingPackage.getPricingPkgId());
					wrapperTpPricingPkgRes.eq("service_id",payTempOrder.getOrderType());
					TpPricingPkgRes tpPricingPkgRes=  tpPricingPkgResService.getOne(wrapperTpPricingPkgRes);
					BigDecimal payTempOrderFee = payTempOrder.getFee();
					if(tpPricingPkgRes!=null){
						payTempOrderFee = payTempOrder.getFee().multiply(tpPricingPkgRes.getDiscountRate()).multiply(BigDecimal.valueOf(0.01)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
						totalFee = new BigDecimal(totalFee).add(payTempOrderFee).toString();
					}

					//建立关联关系
					payTempOrder.setPreorder(payTempOrder.getBusiTradeNo().replace("CONSULT","PREORDER"));
					payTempOrderService.updateById(payTempOrder);
					getPayOrdersAndPayOrderRela(payTempOrder,payTempOrder.getBusiTradeNo().replace("CONSULT","PREORDER"));
					consulterOrderPkgRelQueryWrapper.eq("ord_busi_trade_no",payTempOrder.getBusiTradeNo());
					consulterOrderPkgRel = consulterOrderPkgRelService.getOne(consulterOrderPkgRelQueryWrapper);
					if(consulterOrderPkgRel!=null){
						consulterOrderPkgRel.setPkgBusiTradeNo("VIP"+outTradeNo);
						consulterOrderPkgRelService.update(consulterOrderPkgRel,consulterOrderPkgRelQueryWrapper);
					}else{
						consulterOrderPkgRel = new ConsulterOrderPkgRel();
						consulterOrderPkgRel.setCreateTime(new Date());
						consulterOrderPkgRel.setPkgBusiTradeNo("VIP"+outTradeNo);
						consulterOrderPkgRel.setOrdBusiTradeNo(payTempOrder.getBusiTradeNo());
						consulterOrderPkgRelService.save(consulterOrderPkgRel);
					}


				}
			}

			log.error("openid"+openid);
			log.error("totalFee"+totalFee);
			Map requestMap = new HashMap();
			requestMap.put("openid",openid);
			requestMap.put("body","YEWYW-VIP卡");
			requestMap.put("totalFee",totalFee);
			requestMap.put("outTradeNo","VIP"+outTradeNo);
			wxPayUnifiedOrderRequest =  getWxPayUnifiedOrderRequest(requestMap);
			consulterOrderPkgPay.setBusiTradeNo(String.valueOf(wxPayUnifiedOrderRequest.getOutTradeNo()));
			result = this.wxService.createOrder(wxPayUnifiedOrderRequest);
			consulterOrderPkgPayService.save(consulterOrderPkgPay);






		}else{
			openid = payParam.get("openid").toString();
			Map payTempOrderMap = (Map) payParam.get("payTempOrder");
			String payTempOrderId = payTempOrderMap.get("id").toString();
			QueryWrapper<PayTempOrder> wrapper = new QueryWrapper<PayTempOrder>();
			wrapper.eq("id",payTempOrderId);
			PayTempOrder payTempOrder = payTempOrderService.getOne(wrapper);
			if(payTempOrder!=null){
				totalFee = payTempOrder.getFee().toString();
			}
			Map requestMap = new HashMap();
			requestMap.put("openid",openid);
			requestMap.put("body","YEWYW-CONSULT");
			requestMap.put("totalFee",totalFee);
			requestMap.put("outTradeNo",payTempOrder.getBusiTradeNo());
			payTempOrder.setPreorder(payTempOrder.getBusiTradeNo().replace("CONSULT","PREORDER"));
			payTempOrderService.updateById(payTempOrder);
			wxPayUnifiedOrderRequest =  getWxPayUnifiedOrderRequest(requestMap);
			result = this.wxService.createOrder(wxPayUnifiedOrderRequest);
			getPayOrdersAndPayOrderRela(payTempOrder,result.getPackageValue().replace("prepay_id=",""));
		}



		log.error("wxPayUnifiedOrderRequest---------",JSONObject.toJSONString(wxPayUnifiedOrderRequest));
		log.error("result---------",JSONObject.toJSONString(result));
		return JSONObject.toJSONString(this.wxService.createOrder(wxPayUnifiedOrderRequest));
	}


	/**
	 *  utils
	 */
	public WxPayUnifiedOrderRequest getWxPayUnifiedOrderRequest(Map map){
		String notifyUrl = serverHost+serverPath+"/wxcheckout/payNotify";
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("openid", map.get("openid").toString());
		packageParams.put("body", map.get("body").toString());
		packageParams.put("out_trade_no", map.get("outTradeNo").toString());
		packageParams.put("total_fee", (int) (Float.valueOf(map.get("totalFee").toString()) * 100) + "");
		packageParams.put("spbill_create_ip", spbillCreateIp);
		packageParams.put("notify_url", notifyUrl);
		packageParams.put("trade_type", "JSAPI");
		packageParams.put("device_info", "WEB");
		packageParams.put("nonce_str", System.currentTimeMillis() + "");
		Object object =  SignUtils.createSign(packageParams,null,"LICHENG2017121514283lichenglcnet",null);


		WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
		wxPayUnifiedOrderRequest.setOpenid(packageParams.get("openid"));
		wxPayUnifiedOrderRequest.setBody(packageParams.get("body"));
		wxPayUnifiedOrderRequest.setOutTradeNo(packageParams.get("out_trade_no"));
		wxPayUnifiedOrderRequest.setTotalFee(Integer.valueOf(packageParams.get("total_fee")));
		wxPayUnifiedOrderRequest.setSpbillCreateIp(packageParams.get("spbill_create_ip"));
		wxPayUnifiedOrderRequest.setNotifyUrl(packageParams.get("notify_url"));
		wxPayUnifiedOrderRequest.setTradeType(packageParams.get("trade_type"));
		wxPayUnifiedOrderRequest.setDeviceInfo(packageParams.get("device_info"));
		wxPayUnifiedOrderRequest.setNonceStr(packageParams.get("nonce_str"));
		return  wxPayUnifiedOrderRequest;
	}


	public void getPayOrdersAndPayOrderRela(PayTempOrder payTempOrder,String prePayNo){
		QueryWrapper<PayOrders> payOrdersWrapper = new QueryWrapper<PayOrders>();
		payOrdersWrapper.eq("busi_trade_no",payTempOrder.getBusiTradeNo());
		PayOrders payOrders = payOrdersService.getOne(payOrdersWrapper);
		if(payOrders==null){
			Map payOrdersMap = new HashMap();
			payOrdersMap.put("busiTradeNo",payTempOrder.getBusiTradeNo());
			payOrdersMap.put("prePayNo",prePayNo);
			payOrdersMap.put("busiModel", payTempOrder.getBusiTradeNo().replace("CONSULT","BUSIMODEL"));
			payOrdersMap.put("status",ConstantInterface.payStauts.UnPay);
			payOrdersMap.put("goodId",payTempOrder.getCustomerId());
			payOrders = new PayOrders(payOrdersMap);
			payOrdersService.save(payOrders);
		}


		QueryWrapper<PayOrderRela> payOrderRelaQueryWrapper = new QueryWrapper<PayOrderRela>();
		payOrderRelaQueryWrapper.eq("busi_trade_no",payTempOrder.getBusiTradeNo());
		PayOrderRela payOrderRela = payOrderRelaService.getOne(payOrderRelaQueryWrapper);
		if(payOrderRela==null){
			Map payOrderRelaMap = new HashMap();
			payOrderRelaMap.put("busiTradeNo",payTempOrder.getBusiTradeNo());
			payOrderRelaMap.put("consulterId",payTempOrder.getConsulterId());
			payOrderRelaMap.put("customerId", payTempOrder.getCustomerId());
			payOrderRelaMap.put("fee",payTempOrder.getFee());
			payOrderRelaMap.put("isClose","1");
			payOrderRelaMap.put("isAsked","1");
			payOrderRela = new PayOrderRela(payOrderRelaMap);
			payOrderRelaService.save(payOrderRela);
		}
	}

}

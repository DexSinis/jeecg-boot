package org.jeecg.constants;

public class ConstantInterface {



    public interface UsefulFlag {
        String Useful = "1" ; //1 有用
        String UnUseful = "0" ; //0 无用
    }

    public interface Status {
        String ON = "1" ; //1 有用
        String OFF = "0" ; //0 禁用
    }

    public interface payStauts {  //        支付状态  -1:未支付  0:支付失败  1:支付成功
        String UnPay = "-1" ;
        String canclePay = "0" ;
        String Pay = "1" ;
    }
    public interface payChnl {  //        支付渠道编码，填写终端类型  0：公众号  1：小程序  2：IOS-APP  3：ANDROID-APP
        String payChnl0 = "0" ;
        String payChnl1 = "1" ;
        String payChnl2 = "2" ;
        String payChnl3 = "3" ;
    }
    public interface payType {   //        支付渠道编码，填写终端类型  0：购买vip包  1：提问支付  2：送心意支付
        String payType0 = "VIP" ;
        String payType1 = "CONSULT" ;
        String payType2 = "HEART" ;
        String payType3 = "3" ;
    }


    public interface orderTypeRoServiceId {
         String orderTypeALL="-1";//全部工单
         String FREE_ORDER = "0" ; //0 快速咨询工单
         String STAR_ORDER = "1" ; //1 明星医生工单
         String NUTRITION_GUIDANCE_ORDER = "2" ; //2 营养师工单
         String PSYCHOLOGICAL_COUNSELING_ORDER = "3" ; //3 家庭指导咨询工单
         String QUICK_ORDER = "4" ; //4 快速咨询工单
         String NUTRITION_QUICK_ORDER = "5"; //5 营养师快速工单
         String PSYCHOLOGICAL_QUCIK_ORDER = "6" ; //6 家庭指导咨询快速工单
    }


    public static interface PayTempOrderStatus {
         String PayTempOrderStatusNone = "0" ;//临时工单未生成工单
         String PayTempOrderStatusDone = "1" ;//临时工单生成成功
         String PayTempOrderStatusCancle= "2" ;//临时工单主动关闭
         String PayTempOrderStatusCenter = "3" ;//需要经过中间的临时页面
//       Integer PayTempOrderStatusRebuildCancle= 3 ;//由于二次问，替换了临时单号而取消
    }

}

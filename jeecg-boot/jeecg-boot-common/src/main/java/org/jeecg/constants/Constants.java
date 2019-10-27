package org.jeecg.constants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 常量工具
 * Created by agfly on 2017/6/21.
 */
public class Constants {

    public static final String SPECIAL_BMARK_LIST = "mama"; //妈妈网 需要做特殊处理的B_MARKs  txjk

    public static String REDIS_DISTRIBUTE_POOL = "customer_distribute_pool";

    public static final String REDIS_WEIXIN_APPLICATION_CACHE_NAME = "redis_weixin_application_cache_name";

    public static final String REDIS_WEIXIN_APPLICATION_FIELD = "redis_weixin_application_field";
    public static final String REDIS_SYSTEM_PARAM_NAME = "redis_system_param_name";
    public static final String REDIS_SYSTEM_PARAM_FIELD = "redis_system_param_field";
    public static final String REDIS_SYSTEM_PARAM_CONFIG_KEY = "redis_system_param_config_key";
    public static final String SYSTEM_PARAM_HEAD_URL = "HEAD_URL";
    public static final String REDIS_SYSTEM_WEBSOCKET_ONLINE = "redis_websocket_onlie_";
    public static final String MAX_SMS_IN_MIN = "max_sms_in_min";
    public static final String OPENID_KEY = "_openid_";

    public static final String ERKE_STR = "'儿科','儿保科','全科','内科','外科','公共卫生科','预防医学科','中医科','其他','病理科','儿内科'";
    public static final String FUKE_STR = "'妇科','妇保科','妇产科'";

    public static final String HIGH_LV_JOB_TITLE ="'主任医师','副主任医师'";
    public static final String MIDDLE_LV_JOB_TITLE ="'主治医师'";
    public static final String LOW_LV_JOB_TITLE ="'住院医师'";


    public static final ArrayList<String> erke = new ArrayList<String>(Arrays.asList("儿科","儿保科","全科","内科","外科","公共卫生科","预防医学科","中医科","其他","病理科","儿内科")) ;
    public static final ArrayList<String> fuke = new ArrayList<String>(Arrays.asList("妇科","妇保科","妇产科")) ;

    public static final ArrayList<String> dietitian = new ArrayList<String>(Arrays.asList("营养师")) ;

    public static final ArrayList<String> psychologist = new ArrayList<String>(Arrays.asList("心理咨询科")) ;
    //心理咨询师职称
    public static final ArrayList<String> title_of_psychologist = new ArrayList<String>(Arrays.asList("国家一级心理咨询师","国家二级心理咨询师","国家三级心理咨询师")) ;

    public static final int TAKESIZE = 5;

    public static final int DUOBAOID= 13;//多宝体验

    public static final int JIATINGID = 14;//体验家庭医生

    public static final int JIATINGID2 = 15;//推送家庭医生

    public static final int RENEW = 16 ;// 重新激活旧的用户的模板

    public static final int QUICK_CONSULTE_TIME = 15;//推送家庭医生
//    public static final int QUICK_CONSULTE_TIME = 2;//推送家庭医生
    public static final int FEE_CONSULTE_TIME = 24;//推送家庭医生
    public static final int QUICK_CONSULTE_TIME_ADDED = 5;//推送家庭医生
    public static final int MINITUE = 60;//推送家庭医生

    public static final int FEE_CONSULTE_REPLIED = 50;//推送家庭医生
    public static final String REFUND_TYPE = "refund";//推送家庭医生
    public static final String ONLINE_USER = "_ONLINE_USER_";//推送家庭医生
    public static final Integer ONLINE_EX_TIME = 60 * 60;//推送家庭医生
    public static final String MODULE_H5 = "H5";
    public static final String NOTIFY_MSG = "NOTIFY_MSG";
    public static final String PAY_TIPS = "PAY_TIPS_";
    public static final String APP_VERSION = "VERSION_";
    public static final String WECHAT = "WECHAT";

    public static final String INDEX_URL = "INDEX_URL";
    public static final String GLOBLE_DEBUG = "GLOBLE_DEBUG";
    public static final String CODE = "CODE";

    public static final String TEST_DOCTOR = "TEST_DOCTOR";
    public static final String VUE_SWITCH = "VUE_SWITCH";
    public static final String HUIDU_NEW_BLACK_LIST = "HUIDU_NEW_BLACK_LIST";

    public static final String MONITOR_INFORM_USER = "monitor_inform_user";
    public static final String MONITOR_CHAIN = "monitor_chain";
    public static final String MONITOR_CUSTOMER_SERVICES_INFORM_USER_BY_LC = "monitor_customer_services_inform_user_by_lc";
    public static final String MONITOR_CUSTOMER_SERVICES_INFORM_USER_BY_OWNER = "monitor_customer_services_inform_user_by_owner";
    public final static String FIFTH_POOL_CUSTOMER_mama = "FIFTH_POOL_CUSTOMER_mama";
    public final static String FIFTH_POOL_CUSTOMER_baby = "FIFTH_POOL_CUSTOMER_baby";

    public final static long DAY_SECONDS = 60 * 60 * 24l;
    public final static int EX_DAY_SECONDS = 60 * 60 * 24;




    public final static String YES = "0";
    public final static String NO = "1";

    public final static String UNREADMSG = "unread_msg";



    public static interface Department {
        public static final Integer erke = 1 ;//儿科
        public static final Integer fuke = 2 ;// 妇科
        public static final Integer dietitian =3 ;//营养师
        public static final Integer psychologist =4;//家庭指导师
    }

    public static interface SuitServiceType {
        public static final Integer ALL = -1 ;//所有业务
        public static final Integer QUICK = 0 ;// 快速咨询
        public static final Integer START =1 ;//咨询明星医生
        public static final Integer DIETITIAN =2;//咨询营养师
        public static final Integer PSYCHOLOGIST =3;//咨询家庭指导师
    }

//        1001	0	0	2019-04-12 12:00:00
//        1002	0	1	2019-04-12 12:00:00
//        1003	0	2	2019-04-12 12:00:00
//        1004	0	3	2019-04-12 12:00:00
//        1005	0	4	2019-04-19 12:00:00

    public static interface TRServiceType {
        public static final Integer ALL = -1 ;//所有业务
        public static final Integer FREE = 0 ;// 免费咨询
        public static final Integer START =1 ;//咨询明星医生
        public static final Integer DIETITIAN =2;//咨询营养师
        public static final Integer PSYCHOLOGIST =3;//咨询家庭指导师
        public static final Integer QUICK =4;//快速咨询
        public static final Integer  DIETITIAN_QUICK=5; //营养快速咨询
        public static final Integer  PSYCHOLOGIST_QUICK=6; //心理快速咨询

    }



    public static interface CustomerServiceType {
        int type0 = 0 ;//儿科
         int type1 = 1 ;// 妇科
         int type2 =2 ;//营养指导
         int type3 =3;//过敏咨询
         int type4 =4;//亲子教育
         int type5 =5;//婚姻家庭
         int type6 =6;//个人成长
         int type7 =7;//两性心理
    }

    public static interface ChargeType {
        int ChargeType_1 = 1 ;//按次收费
        int ChargeType_2 = 2 ;// 按周收费
        int ChargeType_3 = 3 ;//按月收费
        int ChargeType_4 = 4;//按年收费
    }

    public static interface ConditionPramType{
        int ConditionPramTypeFree = 100101 ;	//免费咨询条件参数	0		2019-04-12 12:00:00	1001
        int ConditionPramTypeStar  = 100102 ;	//咨询明星医生条件参数	1		2019-04-12 12:00:00	1002
        int ConditionPramTypeDietitian = 100103	; //咨询营养师条件参数	2		2019-04-12 12:00:00	1003
        int ConditionPramTypePsychologist = 100104 ;	//咨询心理咨询师条件参数	3		2019-04-12 12:00:00	1004
        int ConditionPramTypeQuick = 100105 ;	//快速咨询条件参数			2019-04-12 12:00:00	1001

    }





    public static interface Sex {
        public static final Integer UNKNOW = 0 ;//未知
        public static final Integer BOY = 1 ;// 男
        public static final Integer GIRL =2 ;//女

    }

    public static interface Change {
        public static final String canChage = "1" ;//能切换
        public static final String canNotChange = "0" ;//不能切换

    }

    public static interface OrderType {
        public static final Integer QUICK_ORDER = 0 ; //0 快速咨询工单
        public static final Integer STAR_ORDER = 1 ; //1 明星医生工单
        public static final Integer NUTRITION_GUIDANCE_ORDER = 2 ; //2 营养师工单
        public static final Integer PSYCHOLOGICAL_COUNSELING_ORDER = 3 ; //3 家庭指导咨询工单
    }

    public static interface ServiceIdType {
        public static final Integer FEE_ORDER = 0 ; //0 免费咨询
        public static final Integer STAR_ORDER = 1 ; //1 明星医生工单
        public static final Integer NUTRITION_GUIDANCE_ORDER = 2 ; //2 营养师工单
        public static final Integer PSYCHOLOGICAL_COUNSELING_ORDER = 3 ; //3 家庭指导咨询工单
        public static final Integer QUICK_ORDER = 4 ; //快速咨询
    }


    public static interface OrderTypeLimitMin {
        public static final Integer QUICK_ORDER_LIMITMIN = 15*60 ; //0 快速咨询工单限制时间(秒)
        public static final Integer STAR_ORDER_LIMITMIN = 24*60*60 ; //1 明星医生限制时间(秒)
        public static final Integer NUTRITION_GUIDANCE_ORDER_LIMITMIN = 24*60*60 ; //2 营养师工单限制时间(秒)
        public static final Integer PSYCHOLOGICAL_COUNSELING_ORDER_LIMITMIN = 20*60 ; //3 家庭指导咨询工单限制时间(秒)

    }

    public static interface CustomerRole {
        public static final Integer none = 0 ;
        public static final Integer comonCustomer = 1 ;//普通医生
        public static final Integer StarCustomer = 2 ;// 明星医生
        public static final Integer dietitianCustomer =3 ;//营养师
        public static final Integer psychologistCustomer =4;//家庭指导师
    }


    public interface CustomerRoleName {
        String allCustomer = "医生" ;//医生
        String dietitianCustomer = "营养师" ;//营养师
        String psychologistCustomer = "家庭指导师";//家庭指导师
    }


    public static interface OrderConsultType {
        public static final Integer currentTypeBabyRroblem = 0 ;//儿科问题
        public static final Integer currentTypeMotherRroblem = 1 ;//妇科问题
    }


    public static interface QuestionTopicCode { //问题主题分类
        public static final Integer BABY = 0 ; //0 儿科问题
        public static final Integer MAMA = 1 ; //1 妇科问题
        public static final Integer NUTRITION_GUIDANCE= 2 ; // 营养指导 过敏咨询(营养咨询)
        public static final Integer PSYCHOLOGICAL_COUNSELING= 3 ; // 家庭指导咨询

    }

    public static interface CurrentType {
        public static final Integer currentTypeOther = 0 ;//其他
        public static final Integer currentTypeBaby = 1 ;//儿科
        public static final Integer currentTypeMother = 2 ;//妇科

    }

    public static interface Birthstatus {
        public static final Integer birthstatusPrepare = 1 ;//备孕中
        public static final Integer birthstatusPregnant = 2 ;//怀孕中
        public static final Integer birthstatusBaby= 3 ;//出生了
        public static final Integer birthstatusFemale=4;//无怀孕和备孕

    }


    public static interface PayTempOrderStatus {
        public static final Integer PayTempOrderStatusNone = 0 ;//临时工单未生成工单
        public static final Integer PayTempOrderStatusDone = 1 ;//临时工单生成成功
        public static final Integer PayTempOrderStatusCancle= 2 ;//临时工单主动关闭
        public static final Integer PayTempOrderStatusRebuildCancle= 3 ;//由于二次问，替换了临时单号而取消

    }

    public static interface OrderScanStatus {

        /**
         * 普通快速咨询派单给非扫码医生(派单时状态）
         */
        public static final Integer ScanStatus0 = 0 ;
        /**
         * 两种含义：
         * 1.快速咨询的付费工单派单给指定医生
         * 2.派单给扫码医生
         */
        public static final Integer ScanStatus1 = 1 ;
        public static final Integer ScanStatus2= 2 ;// 普通派单（创建时状态）
        public static final Integer ScanStatus3= 3 ;//
        public static final Integer ScanStatus4= 4 ;// 明星医生单（创建时状态）
        public static final Integer ScanStatus5= 5 ;// 第三方工单 例如妈妈网


    }



    public final static String RESSERIAL_ERR = "886";  //代金券不可用错误
    public final static String PAY_ERR = "887"; // 支付订单错误
    public final static String PAY_VALUE_ERR = "888"; //支付金额不匹配

    public static interface wechatConfig{


        public static final String ISDEBUG  =  "WECHATISDEBUG" ;

        public static final String PAYNOTIFY  =  "WECHATPAYNOTIFY" ;

        public static final String APPID = "WECHATCONFIGAPPID" ;

        public static final String SECRET = "WECHATCONFIGSECRET" ;

        public static final String PARTNERID = "WECHATCONFIGPARTNERID" ;

        public static final String PARTNERKEY = "WECHATCONFIGPARTNERKEY" ;

    }
    public static interface SenderType {


        int reward_1  =  16 ;//医生已收到您送出的心意，感谢您的支持
        int reward_2  =  18 ;//用户刚刚给您送出了心意
        int iseval  =  22 ;//您的支持与反馈是我不断前进的动力
        int cus  =  7 ;//催他一下
        int forward  =  98 ;//转接
        int forward_2  =  107 ;//转接
        int recommend  =  8 ;//推荐
        int reward_3  =  17 ;//心动不如行动
        int reward_4  =  117 ;//心动不如行动
        int recommend_2  =  12 ;//推荐
        int thank  =  3 ;//感谢


    }



    public static interface EventLogType {
        public static final Integer hotLabel =1 ; //热门标签
        public static final Integer symptom =2 ; //常见症状
        public static final Integer advertManage=3 ; //广告管理
        public static final Integer articleRead = 4 ; //文章阅读
        public static final Integer articleComment = 6 ; //文章评论
        public static final Integer articleTransmit =7 ; //文章转发
        public static final Integer clnAccess =8 ; //栏目统计


    }

    public static interface PushRecordType {
        public static final Integer news = 0 ; //0 图文
        public static final Integer template = 1 ; //1 模板
        public static final Integer text = 2 ; //2 文本
        public static final Integer image = 3 ; //3 图片

    }




    public static interface FirstDistributeType {
        public static final Integer DIRECTIONAL = 0 ; //0 定向分发
        public static final Integer NOT_DIRECTIONAL = 1 ; //1 非定向分发
    }

    public interface CustomerServicePriceIsvalid {
        Integer valid = 1 ; //1 有效
        Integer invalid = 0 ; //0 无效

    }

    public interface isPayed {
        int ALREADY_PAY = 1;
        int NOT_PAY = 0;
    }
    public interface MsgTemplate {
        String msg_1= "感谢您的评价！把平台分享给需要的朋友就是对我们的最大的支持哦~";
        String msg_2= "育儿问一问正在为您连线医生，您可能需要耐心等待1~3分钟哦~";
        String msg_3= "感谢您的评价！我们将持续提升我们的服务，敬请关注，祝您身体健康，生活愉快！";
        String msg_4= "让您久等了，当前医生比较繁忙。还需要等待1-3分钟";
        String msg_5= "您指定的医生为医院的一线医务人员，现在可能无法及时回复您，如果医生超过15分钟仍未接单，系统默认本单咨询失败，将在2个工作日内为您全额退款！您现在可选择继续等待或点击右上角菜单栏进行退款，并重新选择其他医生咨询。";
        String msg_6="您的医生可能仍在接待其他用户，平台客服正在帮您联系医生，请耐心等待！您也可以点击右上角菜单栏进行退款，重新选择其他医生进行咨询哦~";
        String msg_7="很抱歉，医生超过15分钟仍未能回复您的问题，本次咨询失败，我们将在2个工作日内为您全额退款！";
        String msg_8="医生已接单，正查看问题，请您不要离开！因无法面诊，医生回复仅为建议；涉及诊疗部分，应与面诊医生咨询确认。";
        String msg_9="为您解答的医生均在医院临床一线工作，可能无法完全做到随问随答，请不要离开，耐心等待一下哦~";
        String msg_10="您指定的医生为医院的一线医务人员，现在可能无法及时回复您，客服已通过其他方式通知医生，请您耐心等待一下哦~";
        String msg_11="您咨询的医生仍无法回复您的问题，请耐心等待，您可以点击右上角菜单栏进行退款，并重新选择其他医生咨询。";
        String msg_12="医生已收到您送出的心意，感谢您的支持";
        String msg_13="欢迎您选择其他医生进行咨询。";

    }

    public interface MsgDietitianTemplate {
        String msg_dietitian_1= "感谢您的评价！把平台分享给需要的朋友就是对我们的最大的支持哦~";
        String msg_dietitian_2= "育儿问一问正在为您连线营养师，您可能需要耐心等待1~3分钟哦~";
        String msg_dietitian_3= "感谢您的评价！我们将持续提升我们的服务，敬请关注，祝您身体健康，生活愉快！";
        String msg_dietitian_4= "让您久等了，当前营养师比较繁忙。还需要等待1-3分钟";
        String msg_dietitian_5= "您的营养师可能正在忙，平台客服正在帮您联系营养师，请耐心等待！您也可以点击右上角菜单栏申请退款，重新选择其他营养师进行咨询哦~";
        String msg_dietitian_6="您的营养师现在可能无法及时回复您，如果超过15分钟仍未回复，系统默认本单咨询失败，将在2个工作日内为您全额退款！";
        String msg_dietitian_7="很抱歉，营养师超过15分钟仍未能回复您的问题，本次咨询失败，我们将在2个工作日内为您全额退款！";
        String msg_dietitian_8="营养师已接单，正查看问题，请您不要离开！本次服务最长时长24小时。因无法面诊，营养师回复仅为建议。";
        String msg_dietitian_9="为您解答的营养师均在一线工作，可能无法完全做到随问随答，请不要离开，耐心等待一下哦~";
        String msg_dietitian_10="您指定的营养师为一线工作人员，现在可能无法及时回复您，客服已通过其他方式通知营养师，请您耐心等待一下哦~";
        String msg_dietitian_11="您咨询的营养师仍无法回复您的问题，请耐心等待，您也可以点击右上角菜单栏进行退款，并重新选择其他营养师咨询。";
        String msg_dietitian_12="营养师已收到您送出的心意，感谢您的支持";
        String msg_dietitian_13="欢迎您选择其他营养师进行咨询。";
    }



    public interface MsgPsychologistTemplate {
        String msg_psychologist_1= "感谢您的评价！把平台分享给需要的朋友就是对我们的最大的支持哦~";
        String msg_psychologist_2= "育儿问一问正在为您连线家庭指导师，您可能需要耐心等待1~3分钟哦~";
        String msg_psychologist_3= "感谢您的评价！我们将持续提升我们的服务，敬请关注，祝您身体健康，生活愉快！";
        String msg_psychologist_4= "让您久等了，当前家庭指导师比较繁忙。还需要等待1-3分钟";
        String msg_psychologist_5= "您的家庭指导师可能正在忙，平台客服正在帮您联系家庭指导师，请耐心等待！您也可以点击右上角菜单栏申请退款，重新选择其他家庭指导师进行咨询哦~";
        String msg_psychologist_6="您的家庭指导师现在可能无法及时回复您，如果超过15分钟仍未回复，系统默认本单咨询失败，将在2个工作日内为您全额退款！";
        String msg_psychologist_7="很抱歉，家庭指导师超过15分钟仍未能回复您的问题，本次咨询失败，我们将在2个工作日内为您全额退款！";
        String msg_psychologist_8="家庭指导师已接单，正查看问题，请您不要离开！本次服务最长时长20分钟。因无法面诊，家庭指导师回复仅为建议。";
        String msg_psychologist_9="为您解答的家庭指导师均在一线工作，可能无法完全做到随问随答，请不要离开，耐心等待一下哦~";
        String msg_psychologist_10="您指定的家庭指导师为一线工作人员，现在可能无法及时回复您，客服已通过其他方式通知家庭指导师，请您耐心等待一下哦~";
        String msg_psychologist_11="您咨询的家庭指导师仍无法回复您的问题，请耐心等待，您也可以点击右上角菜单栏进行退款，并重新选择其他家庭指导师咨询。";
        String msg_psychologist_12="家庭指导师已收到您送出的心意，感谢您的支持";
        String msg_psychologist_13="欢迎您选择其他家庭指导师进行咨询。";

    }

    public interface TR_SYSTEM {
        String S000_YEWYW = "S000-YEWYW"; //	育儿问一问	2019-04-02 12:00:00
        String S001_HUANXIN = "S001-HUANXIN"; //	环信	2019-04-02 12:00:00
        String S002_MAMA = "S002-MAMA"; //	妈妈网	2019-04-02 12:00:00
    }

//    短信服务商   国都：GUODU  亿美：EMAY     kewail：KEWAIL
    public interface SMS_PLATFORM {
        String SMS_PLATFORM_EMAY = "EMAY"; //	益美	2019-04-02 12:00:00
        String SMS_PLATFORM_GUODU = "GUODU"; //	国都	2019-04-02 12:00:00
        String SMS_PLATFORM_KEWAIL = "KEWAIL"; // KEWAIL 		2019-04-02 12:00:00
    }

    public interface CustomerPoolType {
        Integer NEW_CUSTOMER = 0 ; // 新进医生
        Integer STAR_CUSTOMER = 1 ; // 星级医生
        Integer BUS_CUSTOMER = 3;  // 商户医生
    }

    public interface GolbleStatus {
        Integer valid = 1 ; //1 有效
        Integer invalid = 0 ; //0 无效
    }
    //  育多多后台管理角色权限等级
    public interface YDD_MAN_USER_ROLE {
        Integer BUSINESS = 4;  //商户管理者权限等级
    }



    public interface TcServiceTermType {
//        ALL	4	V1000	S1001	VPG	当您成为新手妈妈，却不知道如何养育孩子。请您诉说此刻最想解决的一个困惑。	1
//        ALL	4	V1000	S1002	VPG	您总是莫名的情绪困扰，却找不到排解的方法，请您诉说此刻最希望帮您排解的一个困惑。	1
//        ALL	4	V1000	S1003	VPG	对生活失去了兴趣，找不到人生的意义。请您诉说最困扰的一点。	1
//        ALL	4	V1000	S1004	VPG	渴望性福，却不是那么和谐，请您诉说您的困惑。	1
        String S1001 = "S1001" ; //1 有效
        String S1002 = "S1002" ; //1 有效
        String S1003 = "S1003" ; //1 有效
        String S1004 = "S1004" ; //1 有效
        String S1005 = "S1005" ; //1 有效
    }

    public interface  BABY_OR_MAMA_TYPE{
        String BABY_TYPE="0"; //宝宝
        String MAMA_TYPE="1"; //妈妈
    }


//    public static final String ADD_BABY_OR_MAMA="ADD_BABY_OR_MAMA"; //添加宝宝或妈妈信息缓存前缀


    public interface REQUIRE{
        Integer NOT_REQUIRE=0; //不必须
        Integer MUST_REQUIRE=1; //必须
    }

    public  interface RESOURCE_TYPE{
        String RESOURCE_TYPE1="1";//有效优惠券
        String RESOURCE_TYPE2="2";//无效优惠券
    }


}

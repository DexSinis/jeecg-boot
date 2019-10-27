//package junit;
//
//import org.jeecg.AdminApplication;
//import org.jeecg.module.chat.service.ChatService;
//import org.jeecg.module.consulterservice.ConsultService;
//import org.jeecg.module.customer.service.DoctorService;
//import org.jeecg.util.response.ServiceResult;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AdminApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class Demo {
//
//    @Autowired
//    private ConsultService consultService;
//    @Autowired
//    private ChatService chatService;
//    @Autowired
//    private DoctorService doctorService;
//
//    @Test
//    public void test(){
//        System.out.println("成功");
//    }
//
//    //获取工单状态
//    @Test
//    public void getOrderStatusTest(){
//        Integer orderId = 1716938;
//        ServiceResult serviceResult = consultService.getOrderStatus(orderId);
//        Assert.assertTrue(serviceResult.isSuccess());
//    }
//
//    //第三方平台退款通知
//    @Test
//    public void refund(){
//        Integer orderId = 1716976;
//        ServiceResult serviceResult = chatService.refundThirdPlatform(orderId);
//        Assert.assertTrue(serviceResult.isSuccess());
//    }
//
//    //医生详情
//    @Test
//    public void doctorInfo(){
//        Integer customerId = 1070;
//        ServiceResult serviceResult = doctorService.doctorInfo(customerId);
//        Assert.assertTrue(serviceResult.isSuccess());
//    }
//
//}

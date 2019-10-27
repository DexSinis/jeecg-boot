package org.jeecg.exception;//package org.jeecg.exception;
//
//
//import org.jeecg.util.response.ServiceResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import java.util.Set;
//
//
//@ControllerAdvice
//@Component
//@Slf4j
//public class GlobalExceptionHandler {
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }
//
//    @ExceptionHandler
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ServiceResult handle(ConstraintViolationException e) {
//        log.error(e.getMessage(), e);
//        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//        String errorMsg = null;
//        String errorMsgResult = "";
//        for (ConstraintViolation constraintViolation : constraintViolations) {
//            errorMsg = constraintViolation.getMessageTemplate();
////            System.out.println("errorMsg: " + errorMsg);
//            errorMsgResult += errorMsg + "/";
//        }
//        errorMsgResult = errorMsgResult.substring(0, errorMsgResult.length() - 1);
//        log.error("errorMsgResult: "+ errorMsgResult);
//        return ServiceResult.buildError(errorMsgResult);
//    }
//
//
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ServiceResult allException(Exception e) {
//        log.error(e.getMessage(), e);
//
//
//        return ServiceResult.buildError("系统繁忙");
//    }
//}

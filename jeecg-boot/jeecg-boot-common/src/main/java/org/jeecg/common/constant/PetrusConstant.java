package org.jeecg.common.constant;

public class PetrusConstant {

    public static final String PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
            "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgeXnDfxRFcNhpdVC1\n" +
            "79Hc/QjeQ79ZLRnsaTvfckDUWgShRANCAARgICDoVkpn9NZxoK4Bm6kAy04qBpcQ\n" +
            "OU9A1XTpGeICIunUQXwc98f2TCFpk+w3nvy8H5tqqjm8U1XG4eHv+zl2\n" +
            "-----END PRIVATE KEY-----";



    public static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEYCAg6FZKZ/TWcaCuAZupAMtOKgaX\n" +
            "EDlPQNV06RniAiLp1EF8HPfH9kwhaZPsN578vB+baqo5vFNVxuHh7/s5dg==\n" +
            "-----END PUBLIC KEY-----\n";

    public interface CODE_TYPE {
        Integer SUCCESS = 0 ; //0 成功
        Integer ERROR = 1 ; //1 失败
        Integer OVER = -1 ; // -1 token超时失败
    }



    public interface ONLINE {
        Integer ON = 1 ; //0 成功
        Integer OFF = 0 ; //1 失败
    }


    public interface RESERVE_TYPE {
        Integer NONE = 0 ; //0 成功
        Integer DONE = 1 ; //1 失败
    }


    public interface LESSON_STATUS {
        Integer NONE = 0 ; //0 未开始
        Integer STARTTEACHER = 1 ; //1 老师进入课堂
        Integer STARTSTUDENT = 2 ; //2 学生进入课堂
        Integer DONE = 3 ; //3 完成
        Integer UNSTART = 4 ; //4 缺课
        Integer OVER = 5 ; //5 没人定课超时
    }

}

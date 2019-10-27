package org.jeecg.util.password;


import org.jeecg.util.md5.MD5Util;
import org.jeecg.util.UUIDUtil;

import java.util.Random;

/**
 * 密码加密工具类
 */
public class PasswordUtil {


    // 创建盐
    private static String buildSalt() {

        char[] cs = new char[]{'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '_', '=', ';', ':',
                ',', '.', '<', '>'};
        char tmp;
        Random random = new Random();
        int randomInt;
        for (int i = 0; i < cs.length; i++) {
            randomInt = random.nextInt(cs.length);
            tmp = cs[randomInt];
            cs[randomInt] = cs[i];
            cs[i] = tmp;
        }
        String salt = UUIDUtil.createUUId() + String.valueOf(cs);
        return salt;

    }

    /**
     * @param password
     * @param salt
     * @return 创建加密密码
     */
    private static String buildMd5Password(String password, String salt) {
        String md5SaltPassword = MD5Util.getMD5(password + salt);
//		System.out.println("buildMd5Password md5SaltPassword: " + md5SaltPassword);
//		System.out.println("buildMd5Password password: " + password);
//		System.out.println("buildMd5Password salt: " + salt);
        System.out.println("");
        return md5SaltPassword;

    }


    //获取加密密码
    public static Md5PasswordResult getMd5PasswordResult(String password) {
        String salt = buildSalt();
        return new Md5PasswordResult(buildMd5Password(password, salt), salt);
    }

    //验证密码与加密密码
    public static boolean validMd5Password(String password, String salt, String md5Password) {
        return md5Password.equals(buildMd5Password(password, salt));
    }


    public static class Md5PasswordResult {
        private String md5Password;
        private String salt;

        public Md5PasswordResult(String md5Password, String salt) {
            this.md5Password = md5Password;
            this.salt = salt;
        }

        public String getMd5Password() {
            return md5Password;
        }

        public void setMd5Password(String md5Password) {
            this.md5Password = md5Password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }
    }
}

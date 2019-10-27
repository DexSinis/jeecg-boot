package org.jeecg.util;

import java.util.UUID;

/**
 * uuid创建工具类
 */
public class UUIDUtil {

    public static String createUUId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

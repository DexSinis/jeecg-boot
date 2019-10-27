package org.jeecg.util;

import org.jeecg.util.string.StringUtil;
import org.apache.commons.lang3.StringUtils;

public class PathUtil {

    //获取网络路径
    public static final String getWebPath(String fileName) {
        if (StringUtils.isNotBlank(fileName)) {
            return fileName;
        } else {
            return "/res/html5/images/yuer.png";
        }
    }

    /**
     * 默认头像地址
     */
    public static final String headUrl="https://test.yewyw.com/";

    /**
     * 宝宝默认头像
     * @param sex
     * @param url
     * @return
     */
    public static final String getDefaultBabySexHeadUrl(Integer sex,String url) {
        if (sex == 1) {
            return getPath("/res/images/nanBaby.png",url);
        } else {
            return getPath("/res/images/nvBaby.png",url);
        }
    }

    /**
     * 拼接地址
     * @param path
     * @param url
     * @return
     */
    public static final String getPath(String path,String url) {
        if(StringUtil.isEmpty(url)){
            url=headUrl;
        }
        return url + path;
    }

    /**
     * 妈妈默认头像
     * @param url
     * @return
     */
    public static final String getDefaultMotherHeadUrl(String url) {
        return getPath("/res/images/mother.png",url);
    }

}

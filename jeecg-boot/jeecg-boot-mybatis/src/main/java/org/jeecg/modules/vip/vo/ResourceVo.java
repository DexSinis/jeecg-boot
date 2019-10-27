package org.jeecg.modules.vip.vo;

import java.util.List;

/**
 * @Date: Created in 2019/10/9 17:33
 **/
public class ResourceVo {

    private String  pageNo;

    private List<List<TUserResourceVo>> list;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public List<List<TUserResourceVo>> getList() {
        return list;
    }

    public void setList(List<List<TUserResourceVo>> list) {
        this.list = list;
    }
}

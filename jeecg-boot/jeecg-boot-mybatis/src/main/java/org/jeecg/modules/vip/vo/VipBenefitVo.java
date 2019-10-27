package org.jeecg.modules.vip.vo;

import java.util.List;

/**
 * @Date: Created in 2019/10/10 15:25
 **/
public class VipBenefitVo {

    private List<UserVipInfo> info;

    private List<List<ConsulterVIPBenefitVo>> list;


    public List<UserVipInfo> getInfo() {
        return info;
    }

    public void setInfo(List<UserVipInfo> info) {
        this.info = info;
    }

    public List<List<ConsulterVIPBenefitVo>> getList() {
        return list;
    }

    public void setList(List<List<ConsulterVIPBenefitVo>> list) {
        this.list = list;
    }
}

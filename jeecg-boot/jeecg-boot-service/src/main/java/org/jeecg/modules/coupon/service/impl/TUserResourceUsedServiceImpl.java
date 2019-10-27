package org.jeecg.modules.coupon.service.impl;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.jeecg.constants.Constants;
import org.jeecg.modules.coupon.entity.TUserResourceUsed;
import org.jeecg.modules.coupon.mapper.TUserResourceUsedMapper;
import org.jeecg.modules.coupon.service.TUserResourceUsedService;
import org.jeecg.modules.vip.vo.TUserResourceVo;
import org.jeecg.util.string.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 记录用户对营销资源的领用、消费使用情况 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Service
public class TUserResourceUsedServiceImpl extends ServiceImpl<TUserResourceUsedMapper, TUserResourceUsed> implements TUserResourceUsedService {

    @Resource
    private TUserResourceUsedMapper tUserResourceUsedMapper;


    @Override
    public List<List<TUserResourceVo>> myCoupons(String consulterId,
                                                 Page<TUserResourceVo> page,
                                                 String type,String serviceId,String consultFee) {
        /**
         * type 为 2 即为无效的优惠券 ，不做叠加处理，list 中的第一个list 即为所有失效的优惠券类型
         * type 为1 即为有效的优惠券，做叠加处理，list 中的每一个list 即为叠加相同类型的优惠券，
         * 如list[0] 的size 即为叠加了多少张相同的优惠券，list[0][0] 中的就为展开后的优惠券
         */
        IPage<TUserResourceVo> iPage = tUserResourceUsedMapper.myCoupons(page, consulterId, type, serviceId);
        List<List<TUserResourceVo>> list = new ArrayList<>();
        List<TUserResourceVo> voList = iPage.getRecords();
        if (voList == null || voList.size() <= 0) {
            return list;
        }

        if (Constants.RESOURCE_TYPE.RESOURCE_TYPE2.equals(type)) {
            list.add(voList);
            return list;
        }
        int i = 0;
        List<TUserResourceVo> voListSame;
        for (TUserResourceVo vo : voList) {
            if(!StringUtil.isEmpty(serviceId)){
//                if (record.get("RES_VALUE_TYPE").equals("3") && record.getBigDecimal("FULL_VALUE").compareTo(consultFee) > 0) {
//                    record.set("USTATUS", "2");
//                }
                if(vo.getResValueType().equals("3") && new BigDecimal(vo.getFullValue()).compareTo(new BigDecimal(consultFee))>0){
                    vo.setuStatus("2");
                }
            }
            boolean flag = false;
            if (i == 0) {
                voListSame = new ArrayList<>();
                voListSame.add(vo);
                list.add(voListSame);
                i++;


            } else {
                for (List<TUserResourceVo> userResourceVos : list) {
                    boolean rFlag = userResourceVos.get(0).equals(vo);
                    if (rFlag) {
                        userResourceVos.add(vo);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    voListSame = new ArrayList<>();
                    voListSame.add(vo);
                    list.add(voListSame);

                }
            }
        }

        return list;
    }
}

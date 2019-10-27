package org.jeecg.modules.vip.service.impl;

import org.jeecg.modules.vip.entity.TpPricingPackage;
import org.jeecg.modules.vip.mapper.TpPricingPackageMapper;
import org.jeecg.modules.vip.service.TpPricingPackageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.vip.vo.TpPackageResSameVo;
import org.jeecg.modules.vip.vo.TpPricingPackageSame;
import org.jeecg.util.ObjectCheckUtil;
import org.jeecg.util.response.ServiceResult;
import org.jeecg.util.string.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定价信息表 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-09-29
 */
@Service
public class TpPricingPackageServiceImpl extends ServiceImpl<TpPricingPackageMapper, TpPricingPackage> implements TpPricingPackageService {

    @Resource
    private TpPricingPackageMapper tpPricingPackageMapper;

    @Override
    public List<TpPricingPackageSame> queryScaleCnt() {
        List<Map<String, Object>> list = tpPricingPackageMapper.queryScaleCnt();
        List<TpPricingPackageSame> packageSameList = new ArrayList<>();
        String packageId = "";
        TpPricingPackageSame same;
        TpPackageResSameVo vo;
        List<TpPackageResSameVo> voList = null;
        for (Map<String, Object> map : list) {
            String packageIdSame = map.get("PRICING_PKG_ID").toString();
            if (packageId.equals(packageIdSame)) {
                vo = createVo(map);
                if (vo != null) {
                    voList.add(vo);
                }

            } else {
                packageId=packageIdSame;
                same = new TpPricingPackageSame();
                same.setPricingPkgId(packageIdSame);
                same.setPricingPkgName(map.get("PRICING_PKG_NAME") == null ? "" : map.get("PRICING_PKG_NAME").toString());
                same.setChargeType(map.get("CHARGE_TYPE") == null ? "" : map.get("CHARGE_TYPE").toString());
                same.setCreateTime(map.get("CREATE_TIME") == null ? "" : map.get("CREATE_TIME").toString());
                same.setStatus(map.get("STATUS") == null ? "" : map.get("STATUS").toString());
                same.setChangeTime(map.get("CHANGE_TIME") == null ? "" : map.get("CHANGE_TIME").toString());
                same.setEffTime(map.get("EFF_TIME") == null ? "" : map.get("EFF_TIME").toString());
                same.setExpTime(map.get("EXP_TIME") == null ? "" : map.get("EXP_TIME").toString());
                same.setPricingDesc(map.get("PRICING_DESC") == null ? "" : map.get("PRICING_DESC").toString());
                same.setMinPrice(map.get("MIN_PRICE") == null ? "" : map.get("MIN_PRICE").toString());
                same.setSaleCntLimit(map.get("sale_cnt_limit") == null ? "" : map.get("sale_cnt_limit").toString());
                same.setSalePrice(map.get("sale_price") == null ? "" : map.get("sale_price").toString());
                same.setMarkedPrice(map.get("marked_price") == null ? "" : map.get("marked_price").toString());
                same.setPkgType(map.get("pkg_type") == null ? "" : map.get("pkg_type").toString());
                same.setEffType(map.get("eff_type") == null ? "" : map.get("eff_type").toString());
                same.setBuyNum(map.get("buy_num") == null ? "" : map.get("buy_num").toString());
                same.setManMadeBuyNum(map.get("man_made_buy_num")==null?"":map.get("man_made_buy_num").toString());

                String pricingDesc  = (map.get("PRICING_DESC") == null ? "" : map.get("PRICING_DESC").toString());
                String[] strings = pricingDesc.split("#");
                if (strings.length==2){
                    same.setPricingDescS(strings[0]);
                    same.setPricingDescE(strings[1]);
                }

                packageSameList.add(same);
                voList = new ArrayList<>();
                same.setList(voList);
                vo = createVo(map);
                if (vo != null) {
                    voList.add(vo);
                }


            }

        }
        return packageSameList;
    }

    private  TpPackageResSameVo createVo(Map<String,Object> map){
        String serviceId=map.get("service_id")==null?"":map.get("service_id").toString();
        if (!StringUtil.isEmpty(serviceId)) {
            TpPackageResSameVo   vo = new TpPackageResSameVo();
            vo.setServiceId(serviceId);
            vo.setFreeTimes(map.get("free_times")==null?"":map.get("free_times").toString());
            vo.setDiscountRate(map.get("discount_rate")==null?"":map.get("discount_rate").toString());
            vo.setDiscountLimitTimes(map.get("discount_limit_times")==null?"":map.get("discount_limit_times").toString());
            vo.setDiscountdesc(map.get("discount_desc")==null?"":map.get("discount_desc").toString());
           return vo;
        }
        return null;
    }


    @Override
    public ServiceResult queryConsulterVipInformation(String consulterId, String bMark) {
        //todo  2019/09/29/16:53
        ServiceResult serviceResult=new ServiceResult();
        List<Map<String,Object>> list=tpPricingPackageMapper.queryConsulterVipInformation(consulterId,bMark);
        if(list==null || list.size()<=0){
            serviceResult.setCode(1);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("暂无权益");
            return serviceResult;
        }
        return null;
    }
}

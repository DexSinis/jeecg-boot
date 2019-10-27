package org.jeecg.modules.baby.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.baby.entity.Baby;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-06
 */
public interface BabyService extends IService<Baby> {

    /**
     * 获取咨询者信息接口
     * @param consulterId 咨询者id
     * @param type 咨询者查询类型 1 宝宝信息 2 妈妈信息
     * @return
     */
    Map<String,Object> queryConsulterBabyMessage(String consulterId, String type);

    /**
     * 查询咨询者缺省信息
     * @param type
     * @param consulterId
     * @param tempOrderId
     * @param babyId
     * @return
     */
    Map<String,Object> queryDefaultMessage(String type,String consulterId,
                                           String tempOrderId,String babyId);

    /**
     * 保存妈妈或者宝宝信息
     * @param type
     * @param consulterId
     * @param tempOrderId
     * @param itemId
     * @param itemValue
     * @param babyId
     * @return
     */
    Map<String,Object> saveBabyInfo(String type,String consulterId,
                                    String tempOrderId, String itemId,
                                    String itemValue,String babyId);

    /**
     * 创建宝宝
     * @param consulterId
     * @return
     */
    Map<String,Object> createBaby(String consulterId,String tempOrderId);


    Map<String, Object> chooseBaby(String consulterId, String tempOrderId, String babyId);
}

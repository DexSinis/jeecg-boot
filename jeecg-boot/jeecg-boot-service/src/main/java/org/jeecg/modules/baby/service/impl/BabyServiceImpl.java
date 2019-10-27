package org.jeecg.modules.baby.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.jeecg.constants.Constants;
import org.jeecg.modules.baby.service.BabyService;
import org.jeecg.modules.consulter.entity.ConsulterOrder;
import org.jeecg.modules.consulter.mapper.ConsulterOrderMapper;
import org.jeecg.modules.customer.entity.Customer;
import org.jeecg.modules.customer.mapper.CustomerMapper;
import org.jeecg.modules.order.entity.PayTempOrder;
import org.jeecg.modules.order.entity.PreConsulterOrder;
import org.jeecg.modules.baby.entity.TargetInfoItemInst;
import org.jeecg.modules.baby.entity.TrTargetInfoItem;
import org.jeecg.modules.baby.mapper.BabyMapper;
import org.jeecg.modules.baby.entity.Baby;
import org.jeecg.modules.order.mapper.PayTempOrderMapper;
import org.jeecg.modules.order.mapper.PreConsulterOrderMapper;
import org.jeecg.modules.baby.mapper.TargetInfoItemInstMapper;
import org.jeecg.modules.baby.mapper.TrTargetInfoItemMapper;

import org.jeecg.modules.blsystem.entity.SysSystemParam;
import org.jeecg.modules.blsystem.mapper.SysSystemParamMapper;
import org.jeecg.modules.consulter.entity.Consulter;
import org.jeecg.modules.consulter.mapper.ConsulterMapper;
import org.jeecg.util.DateUtil;
import org.jeecg.util.PathUtil;
import org.jeecg.util.date.LocalDateTimeUtil;
import org.jeecg.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author DexSinis
 * @since 2019-07-06
 */
@Service
@Slf4j
public class BabyServiceImpl extends ServiceImpl<BabyMapper, Baby> implements BabyService {


    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private BabyMapper babyMapper;

    @Resource
    private ConsulterMapper consulterMapper;

    @Resource
    private TargetInfoItemInstMapper targetInfoItemInstMapper;

    @Resource
    private TrTargetInfoItemMapper trTargetInfoItemMapper;

    @Resource
    private SysSystemParamMapper sysSystemParamMapper;

    @Resource
    private PreConsulterOrderMapper preConsulterOrderMapper;

    @Resource
    private PayTempOrderMapper payTempOrderMapper;

    @Resource
    private ConsulterOrderMapper consulterOrderMapper;

    @Resource
    private CustomerMapper customerMapper;



    @Override
    public Map<String, Object> queryConsulterBabyMessage(String consulterId, String type) {
//        Baby baby=new Baby();
//        QueryWrapper<Baby> queryWrapper=new QueryWrapper<>();
//        queryWrapper.setEntity(baby);
//        queryWrapper.eq("status",1);
//        if("1".equals(type)){
//            queryWrapper.eq("birthstatus",3);
//        } else if("2".equals(type)){
//            queryWrapper.ne("birthstatus",3);
//        }
//        queryWrapper.eq("consulterid",consulterId);
//        queryWrapper.orderByDesc("create_time");
//        Page<Baby> page = new Page<>(1, 5);
//        IPage<Baby> babyIPage=babyMapper.selectPage(page,queryWrapper);
        //校验用户是否存在
        Map<String, Object> map = checkConsulterExsit(consulterId);
        if (map.size() != 0) {
            return map;
        }
        List<Map<String, Object>> list = babyMapper.queryConsulterBabyMessage(consulterId, type);
        map.put("flag", true);
        map.put("list", list);
        return map;
    }


    @Override
    public Map<String, Object> queryDefaultMessage(String type, String consulterId,
                                                   String tempOrderId, String babyId) {
        //校验用户是否存在
        Map<String, Object> map = checkConsulterExsit(consulterId);
        if (map.size() != 0) {
            return map;
        }
        if (StringUtil.isEmpty(babyId)) {
            babyId = "0";
        }
        //查询默认缺省信息和已填信息
        List<Map<String, Object>> list = babyMapper.queryDefaultMessage(type, consulterId, tempOrderId, babyId);
        //遍历数据分类
        List<Map<String, Object>> defultList = new ArrayList<>();
        Map<String, Object> objectMap = null;
        String id = "";
        List<Map<String, Object>> refList = null;
        Map<String, Object> refMap = null;
        for (Map<String, Object> defultMap : list) {
            String itemId = defultMap.get("ITEM_ID").toString();
            if (!id.equals(itemId)) {
                id = itemId;
                objectMap = new HashMap<>();
                objectMap.put("ITEM_ID", getParmeter(defultMap,"ITEM_ID"));
                objectMap.put("ITEM_NAME",getParmeter(defultMap,"ITEM_NAME") );
                objectMap.put("TARGET_TYPE",getParmeter(defultMap,"TARGET_TYPE"));
                objectMap.put("VALUE_TYPE",getParmeter(defultMap,"VALUE_TYPE") );
                objectMap.put("DEFAULT_VALUE",getParmeter(defultMap,"DEFAULT_VALUE"));
                objectMap.put("REF_OPTIONAL_ID", getParmeter(defultMap,"REF_OPTIONAL_ID"));
                objectMap.put("IS_REQUIRED",getParmeter(defultMap,"IS_REQUIRED"));
                objectMap.put("USER_ANSWER_ID",getParmeter(defultMap,"USER_ANSWER_ID") );
                objectMap.put("USER_ANSWER_VALUE", getParmeter(defultMap,"USER_ANSWER_VALUE"));
                objectMap.put("BABYID", getParmeter(defultMap,"BABYID"));
                defultList.add(objectMap);
                refList = new ArrayList<>();
                objectMap.put("list", refList);
                refMap = new HashMap<>();
                refList.add(refMap);
                refMap.put("ROID", getParmeter(defultMap,"ROID"));
                refMap.put("REF_ID", getParmeter(defultMap,"REF_ID"));
                refMap.put("REF_VALUE", getParmeter(defultMap,"REF_VALUE"));
                refMap.put("SORT_NO",getParmeter(defultMap,"SORT_NO"));
            } else {
                refMap = new HashMap<>();
                refList.add(refMap);
                refMap.put("ROID", getParmeter(defultMap,"ROID"));
                refMap.put("REF_ID", getParmeter(defultMap,"REF_ID"));
                refMap.put("REF_VALUE", getParmeter(defultMap,"REF_VALUE"));
                refMap.put("SORT_NO",getParmeter(defultMap,"SORT_NO"));


            }


        }

        Integer process = 0; //进行到第几步骤
        for(Map data :defultList){
            if(StringUtil.isBlank(data.get("USER_ANSWER_VALUE").toString())){
                process = Integer.valueOf(data.get("ITEM_ID").toString());
                break;
            }
        }
        map.put("process", process);
        map.put("defultlist", defultList);
        map.put("flag", true);
        return map;
    }

    /**
     * 获取map集合中的参数
     * @param map
     * @param parmeter
     * @return
     */
    private String getParmeter(Map<String, Object> map, String parmeter) {
        return map.get(parmeter) == null ? "" : map.get(parmeter).toString();

    }


    @Override
    @Transactional
    public Map<String, Object> saveBabyInfo(String type, String consulterId,
                                            String tempOrderId, String itemId,
                                            String itemValue, String babyId) {
        log.info("--------------------------------保存baby或者妈妈信息开始---------");

        /**
         * `id` varchar(36) NOT NULL,
         `name` varchar(100) DEFAULT NULL,
         `childbirth` datetime DEFAULT '2019-02-21 00:00:00' COMMENT '1-备孕，2-怀孕，3-宝宝 4-女性',
         `sex` int(1) DEFAULT NULL COMMENT '宝宝性别 0未知 1男 2女',
         `consulterid` int(10) DEFAULT NULL,
         `create_time` datetime DEFAULT NULL,
         `isdefault` int(1) DEFAULT NULL,
         `birthstatus` int(1) DEFAULT NULL,
         `gestational_week` int(11) DEFAULT NULL,
         `baby_weight` double(16,2) DEFAULT NULL,
         `baby_height` double(16,2) DEFAULT NULL,
         `baby_url` varchar(256) DEFAULT NULL,
         `status` varchar(2) DEFAULT '1' COMMENT '状态  0-删除，  1-使用',
         */
        /**
         * 宝宝必填 childbirth，sex birthstatus
         */

        //校验用户信息
        Map<String, Object> map = checkConsulterExsit(consulterId);
        if (map.size() != 0) {
            return map;
        }
        Baby baby = null;
        if (Constants.BABY_OR_MAMA_TYPE.BABY_TYPE.equals(type)) {
            baby = babyMapper.selectById(babyId);
            if (baby == null) {
                map.put("flag", false);
                map.put("message", "宝宝信息不存在");
                return map;
            }
            /**
             * 宝宝状态不需要做判断
             */
            if (baby.getBirthstatus()==null) {
                //设置birthdate
                baby.setBirthstatus(Constants.Birthstatus.birthstatusBaby);

            }


            //判断问题存在合理性
            TrTargetInfoItem item = trTargetInfoItemMapper.selectById(itemId);
            map = checkQuestion(item, itemValue, map);
            if (map.size() != 0) {
                return map;
            }

            //判断当前itemid 是否已经存在于用户答案表中
            boolean isTrue = selectTargetinfoItemIst(consulterId, tempOrderId, itemId);


            //插入字段信息到baby 表中
            String refColumn = item.getRefColumn();
            if ("name".equals(refColumn)) {
                baby.setName(itemValue);

            } else if ("childbirth".equals(refColumn)) {
                try {
                    Date date = simpleDateFormat1.parse(itemValue);
                    LocalDateTime localDate = DateUtil.datePastLocalDateTime(date);
                    baby.setChildbirth(LocalDateTimeUtil.convertLDTToDate(localDate));
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("flag", false);
                    map.put("message", "日期格式不对");
                    return map;
                }


            } else if ("sex".equals(refColumn)) {
                if (StringUtil.isEmpty(itemValue)) {
                    baby.setSex(Constants.Sex.UNKNOW);
                } else if (itemValue.contains("男")) {
                    baby.setSex(Constants.Sex.BOY);
                } else if (itemValue.equals("女")) {
                    baby.setSex(Constants.Sex.GIRL);
                } else {
                    baby.setSex(Constants.Sex.UNKNOW);
                }

            } else if ("gestational_week".equals(refColumn)) {
                if (!StringUtil.isEmpty(itemValue)) {
                    baby.setGestationalWeek(Integer.valueOf(itemValue));
                }
            } else if ("baby_weight".equals(refColumn)) {
                if (!StringUtil.isEmpty(itemValue)) {
                    baby.setBabyWeight(new Double(itemValue));
                }

            } else if ("baby_height".equals(refColumn)) {
                if (!StringUtil.isEmpty(itemValue)) {
                    baby.setBabyHeight(new Double(itemValue));
                }

            } else if ("baby_url".equals(refColumn)) {
                if (StringUtil.isEmpty(itemValue)) {
                    //查询头像前缀
                    String url = getSysSystemParamValue("CODE", "HEADURL");
                    String headurl = PathUtil.getDefaultBabySexHeadUrl(baby.getSex(), url);
                    baby.setBabyUrl(headurl);

                } else {
                    baby.setBabyUrl(itemValue);
                }

            }

            if(isTrue){
                //修改
                updateIst(tempOrderId, consulterId, itemId, itemValue);
            }else {
                //插入数据库中
                inertTargetinfoItemIst(tempOrderId, consulterId, itemId, itemValue);
            }
            //更新baby
            babyMapper.updateById(baby);

            //判断baby表中信息是否填完
            String name = baby.getName();
            String babyUrl = baby.getBabyUrl();
            Date childbirth = baby.getChildbirth();
            Integer sex = baby.getSex();
            Integer birthStatus = baby.getBirthstatus();


            if (childbirth != null && birthStatus != null) {
                //信息录入完成
                if (StringUtil.isEmpty(babyUrl)) {
                    //设置默认url
                    String headurl = PathUtil.getDefaultBabySexHeadUrl(Constants.Sex.BOY, "");
                    baby.setBabyUrl(headurl);
                }
                if (StringUtil.isEmpty(name)) {
                    //设置默认name
                    baby.setName("宝宝");
                }
                if (sex == null) {
                    baby.setSex(Constants.Sex.UNKNOW);
                }
                //设置baby 状态为1
                baby.setStatus(Constants.NO);
                // 设置宝宝默认
                baby.setIsdefault(Integer.valueOf(Constants.NO));
                UpdateWrapper<Baby> updateMapper=new UpdateWrapper<>();
                Baby updateBaby=new Baby();
                updateBaby.setIsdefault(Integer.valueOf(Constants.YES));
                //uopdate 语句时不需要设置entiy
               // updateMapper.setEntity(updateBaby);
                updateMapper.eq("consulterid",consulterId);
                updateMapper.eq("birthstatus",Constants.Birthstatus.birthstatusBaby);
                babyMapper.update(updateBaby,updateMapper);
                babyMapper.updateById(baby);
                //信息回填pre_consulter_order 设置已经录入完成
                PreConsulterOrder preConsulterOrder = preConsulterOrderMapper.selectById(tempOrderId);
                preConsulterOrder.setIsComplete(Integer.valueOf(Constants.NO));
//
                preConsulterOrderMapper.updateById(preConsulterOrder);


                map.put("message", "信息全部录入完成");
                map.put("flag", true);
                return map;

            }
            map.put("message", "该问题录入完成");
            map.put("flag", true);
            return map;


        } else if (Constants.BABY_OR_MAMA_TYPE.MAMA_TYPE.equals(type)) {

            baby = babyMapper.selectById(babyId);
            if (baby == null) {
                map.put("flag", false);
                map.put("message", "妈妈信息不存在");
                return map;
            }
            //妈妈性别不做判断
            if (baby.getSex()==null) {
                baby.setSex(Constants.Sex.GIRL);
                babyMapper.updateById(baby);
            }


            //判断问题存在合理性
            TrTargetInfoItem item = trTargetInfoItemMapper.selectById(itemId);
            map = checkQuestion(item, itemValue, map);
            if (map.size() != 0) {
                return map;
            }

            //判断当前itemid 是否已经存在于用户答案表中
            boolean isTrue = selectTargetinfoItemIst(consulterId, tempOrderId, itemId);

            String refColumn = item.getRefColumn();
            /**
             * 是否怀孕 否  是否备孕 是 birthstatus 等于1
             * 是否怀孕 否  是否备孕 否  birthstatus 等于4
             * 是否怀孕 是 预产期  birthstatus 等于2 childbrith 等于 预产期
             */
            Consulter consulter = consulterMapper.selectById(consulterId);
            if ("birthstatus".equals(refColumn)) {
                if ((Constants.Birthstatus.birthstatusPregnant+"").equals(itemValue)) {
                    baby.setBirthstatus(Constants.Birthstatus.birthstatusPregnant);
                } else if ((Constants.Birthstatus.birthstatusPrepare+"").equals(itemValue)) {
                    baby.setBirthstatus(Constants.Birthstatus.birthstatusPrepare);

                } else if((Constants.Birthstatus.birthstatusFemale+"").equals(itemValue)){
                    log.error("进入等于4 的if ------");
                    baby.setBirthstatus(Constants.Birthstatus.birthstatusFemale);
                } else {
                    log.info("----------------------------111111111111111");
                }
            } else if ("childbirth".equals(refColumn)) {
                try {
                    Date date = simpleDateFormat1.parse(itemValue);
                    LocalDateTime localDate = DateUtil.datePastLocalDateTime(date);
                    baby.setChildbirth(LocalDateTimeUtil.convertLDTToDate(localDate));
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("flag", false);
                    map.put("message", "日期格式不对");
                    return map;
                }

            } else if ("gestational_week".equals(refColumn)) {
                if (!StringUtil.isEmpty(itemValue)) {
                    baby.setGestationalWeek(Integer.valueOf(itemValue));
                }
            } else if ("baby_weight".equals(refColumn)) {
                if (!StringUtil.isEmpty(itemValue)) {
                    baby.setBabyWeight(new Double(itemValue));
                }

            } else if ("baby_height".equals(refColumn)) {
                if (!StringUtil.isEmpty(itemValue)) {
                    baby.setBabyHeight(new Double(itemValue));
                }

            } else if ("baby_url".equals(refColumn)) {
                if (StringUtil.isEmpty(itemValue)) {
                    //查询头像前缀
                    String url = getSysSystemParamValue("CODE", "HEADURL");
                    String headurl = PathUtil.getDefaultMotherHeadUrl(url);
                    baby.setBabyUrl(headurl);

                } else {
                    baby.setBabyUrl(itemValue);
                }

            } else if ("name".equals(refColumn)) {
                baby.setName(itemValue);
            } else if("year".equals(refColumn)){
                if (!StringUtil.isEmpty(itemValue)) {
                    itemValue = itemValue.substring(0,itemValue.length()-1);
                    LocalDateTime localDate = LocalDateTime.now();
                    LocalDateTime consulterYear = localDate.minus(Integer.valueOf(itemValue), ChronoUnit.YEARS);
                    consulter.setBirthdate(LocalDateTimeUtil.convertLDTToDate(consulterYear));
                    consulterMapper.updateById(consulter);

                }



            }


            if(isTrue) {
                //修改数据
                updateIst(tempOrderId, consulterId, itemId, itemValue);

            } else {
                //插入数据库中
                inertTargetinfoItemIst(tempOrderId, consulterId, itemId, itemValue);
            }
            //更新baby
            babyMapper.updateById(baby);

            String name = baby.getName();
            String babyUrl = baby.getBabyUrl();
            Date childbirth = baby.getChildbirth();
            Integer sex = baby.getSex();
            Integer birthStatus = baby.getBirthstatus();
            boolean flag = false;
            if (birthStatus!=null) {
                if(birthStatus==Constants.Birthstatus.birthstatusFemale||birthStatus==Constants.Birthstatus.birthstatusPrepare){
                    flag=true;
                }else {
                    if (childbirth != null) {
                        flag = true;
                    }
                }

            }
            if (flag) {

                //信息录入完成
                if (StringUtil.isEmpty(babyUrl)) {
                    baby.setBabyUrl(consulter.getHeadurl());
                }
                if (StringUtil.isEmpty(name)) {
                    //设置默认name
                    //取consulter 表中的nickname
                    baby.setName(consulter.getNickname());
                }
                //设置baby 状态为1
                baby.setStatus(Constants.NO);
                baby.setIsdefault(Integer.valueOf(Constants.NO));
                Baby updateBaby=new Baby();
                updateBaby.setIsdefault(Integer.valueOf(Constants.YES));
                UpdateWrapper<Baby> updateMapper=new UpdateWrapper<>();
                //updateMapper.setEntity(updateBaby);
                updateMapper.eq("consulterid",consulterId);
                updateMapper.ne("birthstatus",Constants.Birthstatus.birthstatusBaby);
                babyMapper.update(updateBaby,updateMapper);
                babyMapper.updateById(baby);
                //信息回填pre_consulter_order 设置已经录入完成
                PreConsulterOrder preConsulterOrder = preConsulterOrderMapper.selectById(tempOrderId);
                preConsulterOrder.setIsComplete(Integer.valueOf(Constants.NO));
                preConsulterOrderMapper.updateById(preConsulterOrder);


                map.put("message", "信息全部录入完成");
                map.put("flag", true);
                return map;

            } else {
                map.put("message", "该问题录入完成");
                map.put("flag", true);
                return map;
            }


        }

        return map;
    }

    /**
     * 用户校验
     *
     * @param consulterId
     * @return
     */
    private Map<String, Object> checkConsulterExsit(String consulterId) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtil.isEmpty(consulterId)) {
            map.put("flag", false);
            map.put("message", "该用户不存在");
            return map;
        }
        Consulter consulter = consulterMapper.selectById(consulterId);
        if (consulter == null) {
            map.put("flag", false);
            map.put("message", "该用户不存在");
            return map;
        }
        return map;

    }

    /**
     * 查询信息是否存在
     *
     * @param consulterId
     * @param tempOrderId
     * @param itemId
     * @return
     */
    private boolean selectTargetinfoItemIst(String consulterId, String tempOrderId,
                                            String itemId) {
        QueryWrapper<TargetInfoItemInst> queryWrapper = new QueryWrapper<>();
        TargetInfoItemInst infoItemInst = new TargetInfoItemInst();
        queryWrapper.setEntity(infoItemInst);
        queryWrapper.eq("CONSULTER_ID", consulterId);
        queryWrapper.eq("TMP_ORDER_ID", tempOrderId);
        queryWrapper.eq("ITEM_ID", itemId);
        List<TargetInfoItemInst> list = targetInfoItemInstMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
           return true;

        }

        return false;


    }


    /**
     * 插入用户填写资料记录
     *
     * @param tempOrderId
     * @param consulterId
     * @param itemId
     * @param itemValue
     */
    private void inertTargetinfoItemIst(String tempOrderId, String consulterId, String itemId, String itemValue) {
        TargetInfoItemInst infoItemInst = new TargetInfoItemInst();
        infoItemInst.setConsulterId(Integer.valueOf(consulterId));
        infoItemInst.setTmpOrderId(Integer.valueOf(tempOrderId));
        infoItemInst.setItemId(Integer.valueOf(itemId));
        infoItemInst.setItemValue(itemValue);
        infoItemInst.setCreateTime(new Date());
        if (StringUtil.isEmpty(itemValue)) {
            infoItemInst.setIsCompleted(0); //未填写完成
        } else {
            infoItemInst.setIsCompleted(1); //填写完成
        }
        targetInfoItemInstMapper.insert(infoItemInst);

    }

    private void updateIst(String tempOrderId, String consulterId, String itemId, String itemValue){
        TargetInfoItemInst infoItemInst = new TargetInfoItemInst();
        infoItemInst.setItemValue(itemValue);
        if (StringUtil.isEmpty(itemValue)) {
            infoItemInst.setIsCompleted(0); //未填写完成
        } else {
            infoItemInst.setIsCompleted(1); //填写完成
        }
        UpdateWrapper<TargetInfoItemInst> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("CONSULTER_ID",consulterId);
        updateWrapper.eq("TMP_ORDER_ID",tempOrderId);
        updateWrapper.eq("ITEM_ID",itemId);
        targetInfoItemInstMapper.update(infoItemInst,updateWrapper);


    }



    /**
     * 获取参数表中的数值
     *
     * @param module
     * @param paramName
     * @return
     */
    private String getSysSystemParamValue(String module, String paramName) {
        SysSystemParam sysSystemParam = new SysSystemParam();
        QueryWrapper<SysSystemParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(sysSystemParam);
        queryWrapper.eq("MODULE", module);
        queryWrapper.eq("PARAM_NAME", paramName);
        queryWrapper.eq("RFLAG", 1);
        queryWrapper.eq("STATUS", 1);
        sysSystemParam = sysSystemParamMapper.selectOne(queryWrapper);
        if (sysSystemParam == null) {
            return "";
        } else {
            return sysSystemParam.getParamValue();
        }

    }

    /**
     * 判断问题选项
     *
     * @param item
     * @param itemValue
     * @param map
     * @return
     */
    private Map<String, Object> checkQuestion(TrTargetInfoItem item, String itemValue, Map<String, Object> map) {
        //判断当前问题是否存在
        if (item == null) {
            map.put("flag", false);
            map.put("message", "该问题选项不存在");
            return map;

        }
        //判断当前选项是否必填
        Integer require = item.getIsRequired();
        if (Constants.REQUIRE.MUST_REQUIRE.equals(require)) {
            if (StringUtil.isEmpty(itemValue)) {
                map.put("flag", false);
                map.put("message", "该问题选项为必填选项");
                return map;
            }

        }
        return map;
    }


    private ConsulterOrder createConsulterOrder(PayTempOrder payTempOrder) {
        ConsulterOrder consulterOrder = new ConsulterOrder();
        /** 临时表字段
         * `id` int(11) NOT NULL AUTO_INCREMENT,
         `CONSULTER_ID` int(11) DEFAULT NULL,
         `LABEL` text CHARACTER SET utf8,
         `PROBLEM` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
         `CONSULTE_TYPE` int(11) DEFAULT NULL,
         `ISDEFAULT` int(11) DEFAULT NULL,
         `PREORDER` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
         `LABELID` int(11) DEFAULT NULL,
         `BABYID` varchar(256) DEFAULT NULL,
         `FLAG` varchar(256) DEFAULT NULL,
         `STATUS` int(11) DEFAULT NULL,
         `CREATE_TIME` datetime DEFAULT NULL,
         `PAY_TYPE` varchar(64) DEFAULT NULL,
         `CUSTOMER_ID` varchar(64) DEFAULT NULL,
         `BUSI_TRADE_NO` varchar(64) DEFAULT NULL,
         `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
         `RESOURCE_INST_ID` int(11) DEFAULT NULL,
         `DISCOUNT_CHARGE` decimal(12,2) DEFAULT NULL,
         `CLICK_PRAMA` varchar(500) DEFAULT NULL,
         `ORDER_ID` int(11) DEFAULT NULL,
         `ALL_SRC` text,
         `IS_PAYED` int(11) DEFAULT '0',
         `FEE` decimal(14,2) DEFAULT '0.00',
         `GOOD_ID` varchar(64) DEFAULT NULL,
         `CUS_ATTR_FEE` decimal(14,2) DEFAULT '0.00',
         `common_customer_id` int(11) DEFAULT NULL COMMENT '非明星医生id，指定非明星医生咨询时才记录本字段',
         `MOBILE_INFO` text,
         `B_MARK` varchar(64) DEFAULT NULL,
         `order_type` int(5) DEFAULT '-1' COMMENT '工单类型编码，0：快速咨询工单 1：明星医生工单 2：营养师工单 3：心理咨询工单',
         `question_topic_code` int(11) DEFAULT '-1' COMMENT '咨询问题主题编码，0：儿科问题 1：妇科问题 2：营养师咨询 3：心理咨询\r\n',
         `first_distribute_type` int(11) DEFAULT '-1' COMMENT '首次分发类型：\n0：定向分发  1：非定向分发',
         */
        consulterOrder.setConsulterId(Integer.valueOf(payTempOrder.getConsulterId()));
        consulterOrder.setStatus(Integer.valueOf(Constants.YES));
        consulterOrder.setServerStatus(Integer.valueOf(Constants.YES));
        long time = System.currentTimeMillis();
        consulterOrder.setCreateTime(time);
        consulterOrder.setAskTime(time);
        consulterOrder.setbMark(payTempOrder.getBMark());
        consulterOrder.setConsulteType(payTempOrder.getConsulteType());
        if (!StringUtil.isEmpty(payTempOrder.getCustomerId())) {
            Customer customer = customerMapper.selectById(payTempOrder.getCustomerId());
            if (customer != null) {
                consulterOrder.setPushUsers(customer.getUsername() + ",");
            }
        }
        consulterOrder.setIsPayed(payTempOrder.getIsPayed());
        consulterOrder.setLabelId(payTempOrder.getLabelid());
        if (!StringUtil.isEmpty(payTempOrder.getProblem())) {
            consulterOrder.setProblem(Integer.valueOf(payTempOrder.getProblem()));
        }
        consulterOrder.setOrderType(payTempOrder.getOrderType());
        if (0 != payTempOrder.getOrderType()) {
            consulterOrder.setIsScan(Integer.valueOf(Constants.NO));
        } else {
            consulterOrder.setIsScan(Integer.valueOf(Constants.YES));
        }
        if (!StringUtil.isEmpty(payTempOrder.getQuestionTopicCode() + "")) {
            consulterOrder.setQuestionTopicCode(payTempOrder.getQuestionTopicCode());
        }
        if (!StringUtil.isEmpty(payTempOrder.getFirstDistributeType() + "")) {
            consulterOrder.setFirstDistributeType(payTempOrder.getFirstDistributeType());
        }
        return consulterOrder;


        /**正式表字段
         * `id` int(11) NOT NULL AUTO_INCREMENT,
         `consulter_id` int(11) NOT NULL COMMENT '发起人id',
         `customer_id` int(11) DEFAULT NULL COMMENT '当前客服id',
         `evaluation_id` int(11) DEFAULT NULL,
         `iseval` int(11) NOT NULL DEFAULT '0',
         `status` int(11) NOT NULL COMMENT '0 未接 1 以接 2 转接中',
         `server_status` int(11) NOT NULL COMMENT '0 未结束 1 结束',
         `create_time` bigint(20) NOT NULL,
         `ack_time` bigint(20) DEFAULT NULL COMMENT '首次响应时间',
         `ask_time` bigint(20) DEFAULT NULL,
         `b_mark` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
         `lastforward_time` bigint(20) DEFAULT NULL COMMENT '最后转接时间',
         `is_bmark_over` int(3) DEFAULT '0' COMMENT '标记商户的工单是否超过24小时，0：否，1：是',
         `service_times` int(11) DEFAULT '0' COMMENT '商户工单的服务次数',
         `sended_sms` int(3) DEFAULT '0' COMMENT '是否已经发送短信 0 未发 1 已发',
         `invite_evaluation_time` bigint(20) DEFAULT NULL COMMENT '邀请评价时间',
         `isforward_notice` int(3) DEFAULT '0' COMMENT '是否已发送转单提示0，否  1，是',
         `channel_type` int(11) DEFAULT NULL,
         `reply_id` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'mama回复id',
         `consulte_type` int(1) DEFAULT NULL COMMENT '工单所属科室类型，0 宝宝类， 1 妇产科',
         `isreg_notice` int(1) DEFAULT '0' COMMENT '注册提醒 0 未提醒，1已提醒',
         `iscus_notice` int(1) DEFAULT '0' COMMENT '发送给医生的提醒0 未提醒 1已提醒',
         `issix_notice` int(1) DEFAULT '0' COMMENT '是否发送6分钟未回复提示 0 未发送 1 已发送',
         `three_consulter` int(1) DEFAULT '0' COMMENT '用户3分钟未回复是否已发模板消息0未发，1 已发',
         `ten_consulter` int(1) DEFAULT '0' COMMENT '用户10分钟未回复是否已发模板消息0未发，1 已发',
         `push_users` longtext CHARACTER SET utf8mb4 COMMENT '推送的用户',
         `label_id` int(11) DEFAULT NULL COMMENT '标签id',
         `appid` int(11) DEFAULT '-1' COMMENT '应用id',
         `problem` int(2) DEFAULT NULL,
         `next` int(2) DEFAULT '0' COMMENT '推送次数',
         `two_consulter` int(1) DEFAULT '0' COMMENT '用户2分钟未回复是否已发模板消息0未发，1 已发',
         `five_consulter` int(1) DEFAULT '0' COMMENT '用户5分钟未回复是否已发系统消息0未发，1 已发',
         `scan_sms` int(3) DEFAULT '0' COMMENT '是否已经发送短信 0 未发 1 已发',
         `answer_time` bigint(20) DEFAULT NULL,
         `is_scan` int(2) DEFAULT NULL COMMENT '0 派单 1指定接单',
         `serv_version` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
         `notify_record` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL,
         `is_payed` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否付费工单0，否；1-是',
         `question_topic_code` int(11) DEFAULT '-1' COMMENT '咨询问题主题编码\n0：儿科问题 1：妇科问题 2：营养咨询  3：心理咨询',
         `order_type` int(11) DEFAULT '-1' COMMENT '工单类型编码\n0：快速咨询工单 1：明星医生工单 2：营养师工单 3：心理咨询工单',
         `first_distribute_type` int(11) DEFAULT '-1' COMMENT '工单首次分发类型\n0：定向分发  1：非定向分发',
         */


    }


    @Override
    public Map<String, Object> createBaby(String consulterId,String tempOrderId) {
        Map<String, Object> map = new HashMap<>();
        Baby baby = new Baby();
        try {
            PreConsulterOrder preConsulterOrder = preConsulterOrderMapper.selectById(tempOrderId);
            baby.setCreateTime(new Date());
            baby.setStatus("2"); //未完善资料
            baby.setConsulterid(Integer.valueOf(consulterId));
            String babyId = UUID.randomUUID().toString().replace("-", "");
            baby.setId(babyId);
            this.saveOrUpdate(baby);
            babyMapper.updateChildBirth(baby.getId());
            preConsulterOrder.setBabyId(baby.getId());
            preConsulterOrder.setIsNew(1);
            preConsulterOrderMapper.updateById(preConsulterOrder);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message","系统错误");
            return map;
        }
        map.put("flag", true);
        map.put("baby", baby);
        return map;
    }


    @Override
    public Map<String, Object> chooseBaby(String consulterId, String tempOrderId, String babyId){
        PreConsulterOrder preConsulterOrder = preConsulterOrderMapper.selectById(tempOrderId);
        preConsulterOrder.setBabyId(babyId);
        preConsulterOrder.setIsNew(0);
        preConsulterOrderMapper.updateById(preConsulterOrder);
        Map<String,Object> map=new HashMap<>();
        map.put("preConsulterOrder",preConsulterOrder);
        return map;

    }

}

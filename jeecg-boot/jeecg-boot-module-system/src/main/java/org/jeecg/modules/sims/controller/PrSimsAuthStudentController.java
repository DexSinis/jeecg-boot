package org.jeecg.modules.sims.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tls.tls_sigature.tls_sigature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.jwt.SimsJWTUtil;
import org.jeecg.common.util.upload.UUIDUtils;
import org.jeecg.modules.sims.entity.SimsStudent;
import org.jeecg.modules.sims.entity.SimsTokenVersion;
import org.jeecg.modules.sims.service.ISimsStudentService;
import org.jeecg.modules.sims.service.ISimsTokenVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 授权  前端控制器
 * </p>
 *
 * @author DexSinis
 * @since 2019-08-03
 */
@Slf4j
@RestController
@Api(tags="APP-授权接口")
@RequestMapping("/simsAuthStudent")
public class PrSimsAuthStudentController {


    @Value(value = "${tencent.SDKAppid}")
    private long SDKAppid;

    @Value(value = "${tencent.SDKAppName}")
    private String SDKAppName;

    @Autowired
    private ISimsStudentService simsStudentService;

    @Autowired
    private ISimsTokenVersionService simsTokenVersionService;

    /**
     * 登录接口
     *
     * @param mobilePhone
     * @param password
     * @param req
     * @return
     */
    @ApiOperation(value = "学生登录接口", notes = "通过账号密码登录账号，返回用户信息")
    @PostMapping(value = "/login")
    @PermissionData(pageComponent="jeecg/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobilePhone",value="手机号",required=true),
            @ApiImplicitParam(name="password",value="密码",required=true),
    })
    @Transactional
    public Result<SimsStudent> login(String mobilePhone , String password,
                                     HttpServletRequest req) {
        boolean queryFlag = false;
        Map map = new HashMap<>();
        map.put("mobilePhone",mobilePhone);
        map.put("password",password);
        SimsStudent simsStudent =  new SimsStudent(map);
        Result<SimsStudent> result = new Result<SimsStudent>();
        QueryWrapper<SimsStudent> queryWrapper = QueryGenerator.initQueryWrapper(simsStudent, req.getParameterMap());
        SimsStudent simsStudentResult  = simsStudentService.getOne(queryWrapper);
        if(simsStudentResult!=null){
            queryFlag = true;
            if(StringUtils.isBlank(simsStudentResult.getSimsPassword())){
                String simsid = UUIDUtils.getUUID();
                tls_sigature.GenTLSSignatureResult signatureResult = tls_sigature.genSig(SDKAppid, simsid, PetrusConstant.PRIVATE_KEY);
                log.error(signatureResult.urlSig);
                simsStudentResult.setSimsid(simsid);
                simsStudentResult.setSimsPassword(signatureResult.urlSig);
            }

            int version = 0;
            String id = simsStudentResult.getId();
            SimsTokenVersion simsTokenVersionQuery = new SimsTokenVersion();
            simsTokenVersionQuery.setId(id);
            SimsTokenVersion simsTokenVersionRecord = simsTokenVersionService.getOne(new QueryWrapper<SimsTokenVersion>(simsTokenVersionQuery));
            if(simsTokenVersionRecord!=null){
                version = simsTokenVersionRecord.getVersion()+1;
                simsTokenVersionRecord.setVersion(version);
            }else{
                simsTokenVersionRecord = new SimsTokenVersion();
                simsTokenVersionRecord.setId(simsStudentResult.getId());
                simsTokenVersionRecord.setVersion(version);
            }

            simsTokenVersionService.saveOrUpdate(simsTokenVersionRecord);



            String token = SimsJWTUtil.createToken(simsStudentResult.getId(), simsStudentResult.getMobilePhone(), version+"", simsStudentResult.getSimsPassword(), Integer.MAX_VALUE).getToken();
            simsStudentResult.setToken(token);
            simsStudentResult.setOnline(PetrusConstant.ONLINE.ON);
            queryFlag = simsStudentService.updateById(simsStudentResult);

        }






        if(queryFlag){

            result.successInterface("登录成功",simsStudentResult);
        }else{
            result.errorInterface("登录失败",null);
        }
        return result;
    }


    /**
     * 登录接口
     *
     * @param mobilePhone
     * @param req
     * @return
     */
    @ApiOperation(value = "学生登出接口", notes = "通过账号退出登录")
    @PostMapping(value = "/loginOut")
    @PermissionData(pageComponent="jeecg/loginOut")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobilePhone",value="手机号",required=true)
    })
    @Transactional
    public Result<SimsStudent> loginOut(String mobilePhone ,
                                     HttpServletRequest req) {
        boolean queryFlag = false;
        Map map = new HashMap<>();
        map.put("mobilePhone",mobilePhone);
        SimsStudent simsStudent =  new SimsStudent(map);
        Result<SimsStudent> result = new Result<SimsStudent>();
        QueryWrapper<SimsStudent> queryWrapper = QueryGenerator.initQueryWrapper(simsStudent, req.getParameterMap());
        SimsStudent simsStudentResult  = simsStudentService.getOne(queryWrapper);
        if(simsStudentResult!=null){
            queryFlag = true;
            simsStudentResult.setOnline(PetrusConstant.ONLINE.OFF);
            simsStudentResult.setToken(null);
            queryFlag = simsStudentService.updateById(simsStudentResult);
        }
        if(queryFlag){
            simsStudentResult.setToken(null);
            result.successInterface("登出成功",simsStudentResult);
        }else{
            result.errorInterface("登出失败",null);
        }
        return result;
    }
}

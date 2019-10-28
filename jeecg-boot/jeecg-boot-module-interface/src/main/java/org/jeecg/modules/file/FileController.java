package org.jeecg.modules.file;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.modules.sims.entity.SimsFile;
import org.jeecg.modules.upload.service.UploadService;
import org.jeecg.util.UUIDUtil;
import org.jeecg.util.file.FileUtil;
import org.jeecg.util.response.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by tnt on 2017/12/7.
 * ${TODO}
 */
@Slf4j
@RestController
@Api(tags="上传接口")
@RequestMapping("/file")
public class FileController {
    @Autowired
    @Qualifier("ossService")
    private UploadService ossService;



    @ApiOperation(value = "上传接口", notes = "上传图片接口")
    @PostMapping(value = "/upload")
    @Transactional
    @ApiImplicitParams({
    })
    public Result uploadFile(@RequestParam("test") MultipartFile file, HttpServletRequest httpServletRequest) {
        String ossUrl = null;
        Map m = Maps.newHashMap();
        SimsFile simsFile = null;
        Result result = new Result();
        try {

            String realName = UUIDUtil.createUUId()+FileUtil.getExtensionName(file.getOriginalFilename());
            ossUrl = ossService.upload(file,realName);
            m.put("ossUrl", ossUrl);
//            m.put("name", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return result.errorInterface(e.getMessage(),"");
        }finally {
            if(simsFile!=null){
                result.setResult(simsFile);
                result.setSuccess(true);
                result.setMessage("上传资料成功");
                result.setCode(PetrusConstant.CODE_TYPE.SUCCESS);
            }else{
                result.setResult(simsFile);
                result.setSuccess(false);
                result.setMessage("上传资料失败");
                result.setCode(PetrusConstant.CODE_TYPE.ERROR);
            }
        }
        return result;
    }

}

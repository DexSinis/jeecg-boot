package org.jeecg.modules.sims.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.common.util.FileTypeUtils;
import org.jeecg.common.util.upload.FileUtils;
import org.jeecg.modules.sims.entity.*;
import org.jeecg.modules.sims.service.ISimsRescourceService;
import org.jeecg.modules.sims.service.ISimsRescourceOpernStudentService;
import org.jeecg.modules.sims.service.ISimsStudentService;
import org.jeecg.modules.upload.service.UploadService;
import org.jeecg.util.UUIDUtil;
import org.jeecg.util.file.FileUtil;
import org.jeecg.util.random.QETag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Api(tags="APP-上传下载")
@RequestMapping("/simsScore")
public class PrSimsScoreController {

    @Value(value = "${jeecg.path.upload}")
    private String uploadpath;

    @Autowired
    @Qualifier("ossService")
    private UploadService ossService;

    @Autowired
    private ISimsRescourceService simsRescourceService;

    @Autowired
    private ISimsRescourceOpernStudentService simsRescourceOpernStudentService;



    @ApiOperation(value = "OSS乐章上传接口", notes = "OSS乐章上传接口")
    @PostMapping(value = "/ossupload")
    @PermissionData(pageComponent="jeecg/ossupload")
    @Transactional
    @ApiImplicitParams({
    })
    public Result ossUploadFile(String opernId,@RequestParam("fileName") MultipartFile file,HttpServletResponse response, HttpServletRequest request) {
        String ossUrl = null;
        Map m = Maps.newHashMap();
        SimsRescource simsRescource = null;
        SimsRescourceOpernStudent simsRescourceOpernStudent = null;
        boolean uploadFlag = false;

        Result result = new Result();
        try {
            QETag tag = new QETag();
            String hash = tag.calcETag(file);
            QueryWrapper<SimsRescource> wrapper = new QueryWrapper<SimsRescource>();
            wrapper.eq("hash",hash);
            wrapper.eq("source","oss");
//            simsRescource = simsRescourceService.getOne(wrapper);
            if( simsRescource!= null&&1==0){
                result.setResult(simsRescource);
                result.setSuccess(true);
                result.setMessage("上传文件成功");
                result.setCode(PetrusConstant.CODE_TYPE.SUCCESS);
            }else{
                String realName = UUIDUtil.createUUId()+ FileUtil.getExtensionName(file.getOriginalFilename());
                ossUrl = ossService.upload(file,realName);                //解析结果
                String id=request.getAttribute("id").toString();
                String mobilePhone=request.getAttribute("mobilePhone").toString();
                String version=request.getAttribute("version").toString();
                String simsPassword=request.getAttribute("simsPassword").toString();
                Map params = new HashMap();
                params.put("mobilePhone",mobilePhone);
                params.put("version",version);
                params.put("simsPassword",simsPassword);
                params.put("webUrl",ossUrl);
                params.put("fileName",file.getName());
                params.put("fileSize",new java.text.DecimalFormat("#.##").format(file.getSize()/1024)+"kb");
                params.put("fileType", FileUtil.getExtensionName(file.getOriginalFilename()));
                params.put("source",id);
                params.put("hash",hash);
                params.put("opernId",opernId);
                simsRescource = new SimsRescource(params);
                simsRescourceService.save(simsRescource);

                params.put("opernId",opernId);
                params.put("resourceId",simsRescource.getId());
                simsRescourceOpernStudent = new SimsRescourceOpernStudent(params);
                uploadFlag = simsRescourceOpernStudentService.save(simsRescourceOpernStudent);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return result.errorInterface(e.getMessage(),"");
        }finally {
            if(uploadFlag){
                result.setResult(simsRescource);
                result.setSuccess(true);
                result.setMessage("上传文件成功");
                result.setCode(PetrusConstant.CODE_TYPE.SUCCESS);
            }else{
                result.setResult(simsRescource);
                result.setSuccess(false);
                result.setMessage("上传文件失败");
                result.setCode(PetrusConstant.CODE_TYPE.ERROR);
            }
        }
        return result;
    }



    @ApiOperation(value = "OSS乐章上传接口", notes = "OSS乐章上传接口")
    @PostMapping(value = "/ossUploadOpern")
    @PermissionData(pageComponent="jeecg/ossUploadOpern")
    @Transactional
    @ApiImplicitParams({
    })
    public Result ossUploadOpern(@RequestBody SimsOpern simsOpern , @RequestParam("fileName") MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
        String ossUrl = null;
        Map m = Maps.newHashMap();
        SimsRescource simsRescource = null;
        SimsRescourceOpernStudent simsRescourceOpernStudent = null;
        boolean uploadFlag = false;

        Result result = new Result();
        try {
            QETag tag = new QETag();
            String hash = tag.calcETag(file);
            QueryWrapper<SimsRescource> wrapper = new QueryWrapper<SimsRescource>();
            wrapper.eq("hash",hash);
            wrapper.eq("source","oss");
//            simsRescource = simsRescourceService.getOne(wrapper);
            if( simsRescource!= null&&1==0){
                result.setResult(simsRescource);
                result.setSuccess(true);
                result.setMessage("上传文件成功");
                result.setCode(PetrusConstant.CODE_TYPE.SUCCESS);
            }else{
                String realName = UUIDUtil.createUUId()+ FileUtil.getExtensionName(file.getOriginalFilename());
                ossUrl = ossService.upload(file,realName);                //解析结果
                String id=request.getAttribute("id").toString();
                String mobilePhone=request.getAttribute("mobilePhone").toString();
                String version=request.getAttribute("version").toString();
                String simsPassword=request.getAttribute("simsPassword").toString();
                Map params = new HashMap();
                params.put("mobilePhone",mobilePhone);
                params.put("version",version);
                params.put("simsPassword",simsPassword);
                params.put("webUrl",ossUrl);
                params.put("fileName",file.getName());
                params.put("fileSize",new java.text.DecimalFormat("#.##").format(file.getSize()/1024)+"kb");
                params.put("fileType", FileUtil.getExtensionName(file.getOriginalFilename()));
                params.put("source",id);
                params.put("hash",hash);
                params.put("opernId",simsOpern.getId());
                simsRescource = new SimsRescource(params);
                simsRescourceService.save(simsRescource);

                params.put("opernId",simsOpern.getId());
                params.put("resourceId",simsRescource.getId());
                simsRescourceOpernStudent = new SimsRescourceOpernStudent(params);
                uploadFlag = simsRescourceOpernStudentService.save(simsRescourceOpernStudent);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return result.errorInterface(e.getMessage(),"");
        }finally {
            if(uploadFlag){
                result.setResult(simsRescource);
                result.setSuccess(true);
                result.setMessage("上传文件成功");
                result.setCode(PetrusConstant.CODE_TYPE.SUCCESS);
            }else{
                result.setResult(simsRescource);
                result.setSuccess(false);
                result.setMessage("上传文件失败");
                result.setCode(PetrusConstant.CODE_TYPE.ERROR);
            }
        }
        return result;
    }



    @ApiOperation(value = "乐章上传接口", notes = "上传图片接口")
    @PostMapping(value = "/upload")
    @PermissionData(pageComponent="jeecg/upload")
    @Transactional
    @ApiImplicitParams({
            @ApiImplicitParam(name="opernId",value="曲谱号",required=true),
    })
    public Result upload( String opernId,@RequestParam("fileName") MultipartFile mf, HttpServletResponse response, HttpServletRequest request) {
        Result result = new Result();
        boolean uploadFlag = false;
        SimsRescource simsRescource = null;
        SimsRescourceOpernStudent simsRescourceOpernStudent = null;
        try {
            String ctxPath = uploadpath;
            String fileName = null;
            String bizPath = "files";
            String nowday = new SimpleDateFormat("yyyyMMdd").format(new Date());
            File file = new File(ctxPath + File.separator + bizPath + File.separator + nowday);
            if (!file.exists()) {
                file.mkdirs();// 创建文件根目录
            }
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            MultipartFile mf = multipartRequest.getFile("file");// 获取上传文件对象
            String orgName = mf.getOriginalFilename();// 获取文件名
            fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
            String savePath = file.getPath() + File.separator + fileName;
            File savefile = new File(savePath);
            FileCopyUtils.copy(mf.getBytes(), savefile);
            String dbpath = bizPath + File.separator + nowday + File.separator + fileName;
            if (dbpath.contains("\\")) {
                dbpath = dbpath.replace("\\", "/");
            }

            String id=request.getAttribute("id").toString();
            String mobilePhone=request.getAttribute("mobilePhone").toString();
            String version=request.getAttribute("version").toString();
            String simsPassword=request.getAttribute("simsPassword").toString();
            Map params = new HashMap();


            params.put("mobilePhone",mobilePhone);
            params.put("version",version);
            params.put("simsPassword",simsPassword);
            params.put("webUrl",dbpath);
            params.put("fileName",file.getName());
            params.put("fileSize",file.length());
            params.put("fileType", fileName.substring(fileName.lastIndexOf(".")));
            params.put("source",id);
            params.put("opernId",opernId);



            simsRescource = new SimsRescource(params);
            uploadFlag = simsRescourceService.save(simsRescource);


            params.put("opernId",opernId);
            params.put("resourceId",simsRescource.getId());
            simsRescourceOpernStudent = new SimsRescourceOpernStudent(params);
            uploadFlag = simsRescourceOpernStudentService.save(simsRescourceOpernStudent);



        } catch (IOException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            result.setCode(PetrusConstant.CODE_TYPE.ERROR);
            log.error(e.getMessage(), e);
        }finally {
            if(uploadFlag){
                result.setResult(simsRescource);
                result.setSuccess(true);
                result.setMessage("上传乐章成功");
                result.setCode(PetrusConstant.CODE_TYPE.SUCCESS);
            }else{
                result.setResult(simsRescource);
                result.setSuccess(false);
                result.setMessage("上传乐章失败");
                result.setCode(PetrusConstant.CODE_TYPE.ERROR);
            }
        }
        return result;
    }


    /**
     * 预览图片
     * 请求地址：http://localhost:8080/common/view/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
     *
     * @param request
     * @param response
     */
    @ApiOperation(value = "乐章浏览接口", notes = "http://192.168.1.6:8080/jeecg-boot/simsScore/view/files/20190806/Free-Converter.com-wechatimg1807-63742344_1565094164540.com-wechatimg1807-63742344.jpg")
    @PermissionData(pageComponent="jeecg/view")
    @GetMapping(value = "/view/**")
    public void view(HttpServletRequest request, HttpServletResponse response) {
        // ISO-8859-1 ==> UTF-8 进行编码转换
        String imgPath = extractPathFromPattern(request);
        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            imgPath = imgPath.replace("..", "");
            if (imgPath.endsWith(",")) {
                imgPath = imgPath.substring(0, imgPath.length() - 1);
            }
            response.setContentType("image/jpeg;charset=utf-8");
            String localPath = uploadpath;
            String imgurl = localPath + File.separator + imgPath;
            inputStream = new BufferedInputStream(new FileInputStream(imgurl));
            outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            response.flushBuffer();
        } catch (IOException e) {
            log.error("预览图片失败" + e.getMessage());
            // e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }

    /**
     * 下载文件
     * 请求地址：http://localhost:8080/common/download/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/download/**")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ISO-8859-1 ==> UTF-8 进行编码转换
        String filePath = extractPathFromPattern(request);
        // 其余处理略
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            filePath = filePath.replace("..", "");
            if (filePath.endsWith(",")) {
                filePath = filePath.substring(0, filePath.length() - 1);
            }
            String localPath = uploadpath;
            String downloadFilePath = localPath + File.separator + filePath;
            File file = new File(downloadFilePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开            
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
                inputStream = new BufferedInputStream(new FileInputStream(file));
                outputStream = response.getOutputStream();
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
                response.flushBuffer();
            }

        } catch (Exception e) {
            log.info("文件下载失败" + e.getMessage());
            // e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "删除乐章页")
    @PostMapping(value = "/delete")
    @ApiOperation(value = "通过ID删除曲谱页", notes = "通过ID删除乐章页")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        Result result = new Result();
        try {
            simsRescourceService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败",e.getMessage());
            return result.errorInterface("删除失败",id);
        }
        return result.successInterface("删除成功",id);
    }



    /**
     * @功能：pdf预览Iframe
     * @param modelAndView
     * @return
     */
    @RequestMapping("/pdf/pdfPreviewIframe")
    public ModelAndView pdfPreviewIframe(ModelAndView modelAndView) {
        modelAndView.setViewName("pdfPreviewIframe");
        return modelAndView;
    }

    /**
     *  把指定URL后的字符串全部截断当成参数
     *  这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
     * @param request
     * @return
     */
    private static String extractPathFromPattern(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }

}


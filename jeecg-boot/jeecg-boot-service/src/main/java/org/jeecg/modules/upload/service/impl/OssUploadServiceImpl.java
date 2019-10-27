package org.jeecg.modules.upload.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.sims.entity.SimsFile;
import org.jeecg.modules.sims.service.ISimsFileService;
import org.jeecg.modules.statistics.entity.OrderDtlD;
import org.jeecg.modules.upload.entity.SysUploadInfo;
import org.jeecg.modules.upload.service.SysUploadInfoService;
import org.jeecg.modules.upload.service.UploadService;
import org.jeecg.util.date.DateUtil;
import org.jeecg.util.file.FileUtil;
import org.jeecg.util.random.QETag;
//import org.jeecg.util.random.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@Service("ossService")
public class OssUploadServiceImpl implements UploadService {

    @Value("${jeecg.path.upload}")
    private String upLoadPath;

    @Autowired
    private ISimsFileService simsFileService;

    @Autowired
    private SysUploadInfoService sysUploadInfoService;

    private SysUploadInfo getUploadInfo(){
        return sysUploadInfoService.getOneInfo();
    }

    private OSSClient getOSSClient(){
        return new OSSClient(getUploadInfo().getOssEndpoint(),getUploadInfo().getOssKeyId(), getUploadInfo().getOssKeySecret());
    }




    public MultipartFile getThumbnailsFile(MultipartFile multipartfile,
                                           String prefixName,
                                           String suffixNameOrigin,
                                           File tempFile,
                                           File f,
                                           FileInputStream inFile
                                           ) throws Exception {
        //图片存储文件夹
        String filePath = upLoadPath;
        //图片在项目中的地址(项目位置+图片名,带后缀名)
        String contextPath = filePath + prefixName + "." + suffixNameOrigin;

        try {
            //图片在项目中的地址(项目位置+图片名,带后缀名)
            tempFile = new File(contextPath);
            if (!tempFile.exists()) {
                //生成图片文件
                FileUtils.copyInputStreamToFile(multipartfile.getInputStream(), tempFile);
            }
            /*
             * 图片压缩
             */
            BufferedImage image = ImageIO.read(multipartfile.getInputStream());
            Thumbnails.of(contextPath).outputQuality(0.2f).scale(1f).toFile(contextPath);
            f = new File(contextPath);

            inFile = new FileInputStream(f);
            MultipartFile multi = new MockMultipartFile(prefixName + "." + suffixNameOrigin, inFile);

            return multi;
        } catch (Exception e) {
            throw new Exception("文件上传失败");
        } finally {

        }
    }



    @Override
    public String upload(MultipartFile file,String realName) throws IOException, NoSuchAlgorithmException {
        String realNames = "";
        StringBuffer returnUrl = new StringBuffer(getUploadInfo().getOssBasePath());
        String ossDir = getUploadInfo().getOssDir();
        File tempFile = null ;
        File f = null ;
        FileInputStream inFile = null;
        URL ossUrl = null;
        //判断文件是否存在
        try {
            //获取图片文件名(不带扩展名的文件名)
            String prefixName = FileUtil.getFileNameWithoutEx(file.getOriginalFilename());
            //获取图片后缀名,判断如果是png的话就不进行格式转换,因为Thumbnails存在转png->jpg图片变红bug
            String suffixNameOrigin = FileUtil.getExtensionName(file.getOriginalFilename());
            //压缩文件
            file = getThumbnailsFile(file,prefixName,suffixNameOrigin,tempFile,f,inFile);


            InputStream is = file.getInputStream();

            Long fileSize = file.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(is.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
//            metadata.setContentType(ToolUtil.getContentType(fileName));
            StringBuffer description = new StringBuffer("filename/filesize=");
            description.append(realNames).append("/").append(fileSize).append("Byte.");
            metadata.setContentDisposition(description.toString());

            StringBuffer key = new StringBuffer();
            if(ossDir != null && !"".equals(ossDir)){
                key.append(ossDir).append("/");
                returnUrl.append(ossDir).append("/");
            }
            key.append(realName);
            returnUrl.append(realName);
            PutObjectResult putResult = getOSSClient().putObject(getUploadInfo().getOssBucketName(), key.toString(), is, metadata);
            Date expiration = DateUtil.formatStrToDate("2200-01-01","yyyy-MM-dd");
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            ossUrl = getOSSClient().generatePresignedUrl(getUploadInfo().getOssBucketName(), key.toString(), expiration);



//            simsFile.insert();
            getOSSClient().shutdown();
            is.close();
        } catch (Exception e) {
            throw new JeecgBootException("上传阿里云OSS服务器异常." + e.getMessage());
        }finally {
            if(tempFile!=null || f!=null|| inFile!=null){
                //将临时文件删除
                tempFile.delete();
                f.delete();
                inFile.close();
            }

        }
        return ossUrl.toString().replaceAll("http://","https://");
    }

    @Override
    public Boolean delete(String path) {
        path = path.replace(getUploadInfo().getOssBasePath(), "");
        String ossDir = getUploadInfo().getOssDir();
        StringBuffer sb = new StringBuffer();
        if(ossDir != null && !"".equals(ossDir)){
            sb.append(ossDir).append("/");
        }
        String key = path.replace(sb.toString(),"");
        try {
            getOSSClient().deleteObject(getUploadInfo().getOssBucketName(), path);
            QueryWrapper<SimsFile> wrapper = new QueryWrapper<SimsFile>();
            wrapper.eq("file_name",key);
            wrapper.eq("source","oss");
            simsFileService.remove(wrapper);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String uploadNetFile(String url) throws IOException, NoSuchAlgorithmException {
        QueryWrapper<SimsFile> wrapper = new QueryWrapper<SimsFile>();
        wrapper.eq("source","oss");
        wrapper.eq("original_net_url",url);
        SimsFile simsFile = simsFileService.getOne(wrapper);
        if(simsFile != null){
            return simsFile.getWebUrl();
        }
        String ossDir = getUploadInfo().getOssDir(),
                fileName = UUID.randomUUID().toString();
        StringBuffer returnUrl = new StringBuffer(getUploadInfo().getOssBasePath());
        StringBuffer key = new StringBuffer();
        if(ossDir != null && !"".equals(ossDir)){
            key.append(ossDir).append("/");
        }
        key.append(fileName).append(".jpg");
        StringBuffer sb = new StringBuffer(fileName);
        InputStream inputStream = new URL(url).openStream();
        PutObjectResult putObjectResult = getOSSClient().putObject(getUploadInfo().getOssBucketName(), key.toString(), inputStream);
        ResponseMessage responseMessage = putObjectResult.getResponse();
        returnUrl.append(key);
        simsFile = new SimsFile();
        simsFile.setFileName(sb.append(".jpg").toString());
        simsFile.setHash(putObjectResult.getETag());
        simsFile.setWebUrl(returnUrl.toString());
//        simsFile.setOriginalNetUrl(url);
        simsFile.setSource("oss");
        simsFileService.save(simsFile);
        inputStream.close();

        getOSSClient().shutdown();
        return returnUrl.toString();
    }

    @Override
    public String uploadLocalImg(String localPath) {
        File file = new File(localPath);
        if(!file.exists()){
            throw new JeecgBootException("本地文件不存在");
        }
        QETag tag = new QETag();
        String hash = null;
        try {
            hash = tag.calcETag(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SimsFile simsFile = new SimsFile();
        QueryWrapper<SimsFile> wrapper = new QueryWrapper<SimsFile>();
        wrapper.eq("hash",hash);
        wrapper.eq("source","oss");
        simsFile = simsFileService.getOne(wrapper);
        if( simsFile!= null){
            return simsFile.getWebUrl();
        }
        String filePath="",
                extName = "",
                ossDir = getUploadInfo().getOssDir(),
                name = UUID.randomUUID().toString();
        extName = file.getName().substring(
                file.getName().lastIndexOf("."));
        StringBuffer returnUrl = new StringBuffer(getUploadInfo().getOssBasePath());
        StringBuffer key = new StringBuffer();
        if(ossDir != null && !"".equals(ossDir)){
            key.append(ossDir).append("/");
        }
        key.append(name).append(".").append(extName);
        StringBuffer realName = new StringBuffer(name);
        getOSSClient().putObject(getUploadInfo().getOssBucketName(), key.toString(), file);
        returnUrl.append(realName);
        simsFile = new SimsFile();
        simsFile.setFileName(realName.append(".").append(extName).toString());
        simsFile.setFileSize(new java.text.DecimalFormat("#.##").format(file.length()/1024)+"kb");
        simsFile.setHash(hash);
        simsFile.setFileType(extName);
        simsFile.setWebUrl(returnUrl.toString());
        simsFile.setSource("oss");
        simsFileService.save(simsFile);
        getOSSClient().shutdown();
        return null;
    }

    @Override
    public String uploadBase64(String base64) {
        //base64数据转换为byte[]类型
        byte[] asBytes = Base64.getDecoder().decode(base64);
        InputStream sbs = new ByteArrayInputStream(asBytes);
        StringBuffer returnUrl = new StringBuffer(getUploadInfo().getOssBasePath());
        StringBuffer key = new StringBuffer();
        StringBuffer fileName = new StringBuffer(UUID.randomUUID().toString());
        String ossDir = getUploadInfo().getOssDir();
        int fileSize = asBytes.length;
        //创建上传Object的Metadata
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(sbs.available());
            metadata.setContentEncoding("utf-8");
            metadata.setContentType("image/png");
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件
            if(ossDir != null && !"".equals(ossDir)){
                key.append(ossDir).append("/");
                returnUrl.append(ossDir).append("/");
            }
            key.append(fileName);
            PutObjectResult putResult = getOSSClient().putObject(getUploadInfo().getOssBucketName(), key.toString(), sbs, metadata);
            returnUrl.append(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sbs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getOSSClient().shutdown();
        }
        return returnUrl.toString();
    }

    @Override
    public Boolean testAccess(SysUploadInfo sysUploadInfo) {
        ClassPathResource classPathResource = new ClassPathResource("static/images/userface1.jpg");
        try {
            OSSClient ossClient = new OSSClient(sysUploadInfo.getOssEndpoint(),sysUploadInfo.getOssKeyId(), sysUploadInfo.getOssKeySecret());
            InputStream inputStream = classPathResource .getInputStream();
            ossClient.putObject(sysUploadInfo.getOssBucketName(), "test.jpg", inputStream, null);
            ossClient.shutdown();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

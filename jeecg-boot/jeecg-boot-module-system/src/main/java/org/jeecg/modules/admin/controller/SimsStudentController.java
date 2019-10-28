package org.jeecg.modules.admin.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.sims.entity.SimsStudent;
import org.jeecg.modules.sims.service.ISimsStudentService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @Description: 学生后台管理
* @Author: jeecg-boot
* @Date:   2019-08-21
* @Version: V1.0
*/
@Slf4j
@Api(tags="Server-学生管理")
@RestController
@RequestMapping("/sims/simsStudent")
public class SimsStudentController {
   @Autowired
   private ISimsStudentService simsStudentService;

   /**
     * 分页列表查询
    * @param simsStudent
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "学生后台管理-分页列表查询")
   @ApiOperation(value="学生后台管理-分页列表查询", notes="学生后台管理-分页列表查询")
   @GetMapping(value = "/list")
   public Result<IPage<SimsStudent>> queryPageList(SimsStudent simsStudent,
                                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
       Result<IPage<SimsStudent>> result = new Result<IPage<SimsStudent>>();
       QueryWrapper<SimsStudent> queryWrapper = QueryGenerator.initQueryWrapper(simsStudent, req.getParameterMap());
       Page<SimsStudent> page = new Page<SimsStudent>(pageNo, pageSize);
       IPage<SimsStudent> pageList = simsStudentService.page(page, queryWrapper);
       result.setSuccess(true);
       result.setResult(pageList);
       return result;
   }

   /**
     *   添加
    * @param simsStudent
    * @return
    */
   @AutoLog(value = "学生后台管理-添加")
   @ApiOperation(value="学生后台管理-添加", notes="学生后台管理-添加")
   @PostMapping(value = "/add")
   public Result<SimsStudent> add(@RequestBody SimsStudent simsStudent) {
       Result<SimsStudent> result = new Result<SimsStudent>();
       try {
           simsStudent.setCreateTime(new Date());
           simsStudentService.save(simsStudent);
           result.success("添加成功！");
       } catch (Exception e) {
           log.error(e.getMessage(),e);
           result.error500("操作失败");
       }
       return result;
   }

   /**
     *  编辑
    * @param simsStudent
    * @return
    */
   @AutoLog(value = "学生后台管理-编辑")
   @ApiOperation(value="学生后台管理-编辑", notes="学生后台管理-编辑")
   @PutMapping(value = "/edit")
   public Result<SimsStudent> edit(@RequestBody SimsStudent simsStudent) {
       Result<SimsStudent> result = new Result<SimsStudent>();
       SimsStudent simsStudentEntity = simsStudentService.getById(simsStudent.getId());
       if(simsStudentEntity==null) {
           result.error500("未找到对应实体");
       }else {
           boolean ok = simsStudentService.updateById(simsStudent);
           //TODO 返回false说明什么？
           if(ok) {
               result.success("修改成功!");
           }
       }

       return result;
   }

   /**
     *   通过id删除
    * @param id
    * @return
    */
   @AutoLog(value = "学生后台管理-通过id删除")
   @ApiOperation(value="学生后台管理-通过id删除", notes="学生后台管理-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       try {
           simsStudentService.removeById(id);
       } catch (Exception e) {
           log.error("删除失败",e.getMessage());
           return Result.error("删除失败!");
       }
       return Result.ok("删除成功!");
   }

   /**
     *  批量删除
    * @param ids
    * @return
    */
   @AutoLog(value = "学生后台管理-批量删除")
   @ApiOperation(value="学生后台管理-批量删除", notes="学生后台管理-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<SimsStudent> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       Result<SimsStudent> result = new Result<SimsStudent>();
       if(ids==null || "".equals(ids.trim())) {
           result.error500("参数不识别！");
       }else {
           this.simsStudentService.removeByIds(Arrays.asList(ids.split(",")));
           result.success("删除成功!");
       }
       return result;
   }

   /**
     * 通过id查询
    * @param id
    * @return
    */
   @AutoLog(value = "学生后台管理-通过id查询")
   @ApiOperation(value="学生后台管理-通过id查询", notes="学生后台管理-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<SimsStudent> queryById(@RequestParam(name="id",required=true) String id) {
       Result<SimsStudent> result = new Result<SimsStudent>();
       SimsStudent simsStudent = simsStudentService.getById(id);
       if(simsStudent==null) {
           result.error500("未找到对应实体");
       }else {
           result.setResult(simsStudent);
           result.setSuccess(true);
       }
       return result;
   }

 /**
     * 导出excel
  *
  * @param request
  * @param response
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
     // Step.1 组装查询条件
     QueryWrapper<SimsStudent> queryWrapper = null;
     try {
         String paramsStr = request.getParameter("paramsStr");
         if (oConvertUtils.isNotEmpty(paramsStr)) {
             String deString = URLDecoder.decode(paramsStr, "UTF-8");
             SimsStudent simsStudent = JSON.parseObject(deString, SimsStudent.class);
             queryWrapper = QueryGenerator.initQueryWrapper(simsStudent, request.getParameterMap());
         }
     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     }

     //Step.2 AutoPoi 导出Excel
     ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
     List<SimsStudent> pageList = simsStudentService.list(queryWrapper);
     //导出文件名称
     mv.addObject(NormalExcelConstants.FILE_NAME, "学生后台管理列表");
     mv.addObject(NormalExcelConstants.CLASS, SimsStudent.class);
     mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("学生后台管理列表数据", "导出人:Jeecg", "导出信息"));
     mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
     return mv;
 }

 /**
     * 通过excel导入数据
  *
  * @param request
  * @param response
  * @return
  */
 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
     MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
     Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
     for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
         MultipartFile file = entity.getValue();// 获取上传文件对象
         ImportParams params = new ImportParams();
         params.setTitleRows(2);
         params.setHeadRows(1);
         params.setNeedSave(true);
         try {
             List<SimsStudent> listSimsStudents = ExcelImportUtil.importExcel(file.getInputStream(), SimsStudent.class, params);
             simsStudentService.saveBatch(listSimsStudents);
             return Result.ok("文件导入成功！数据行数:" + listSimsStudents.size());
         } catch (Exception e) {
             log.error(e.getMessage(),e);
             return Result.error("文件导入失败:"+e.getMessage());
         } finally {
             try {
                 file.getInputStream().close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     return Result.ok("文件导入失败！");
 }

}

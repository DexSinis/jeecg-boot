package org.jeecg.modules.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.constant.PetrusConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.sims.entity.SimsLesson;
import org.jeecg.modules.sims.entity.SimsStudent;
import org.jeecg.modules.sims.entity.SimsTeacher;
import org.jeecg.modules.sims.service.ISimsLessonService;
import org.jeecg.modules.sims.service.ISimsStudentService;
import org.jeecg.modules.sims.service.ISimsTeacherService;
import org.jeecg.modules.sims.vo.SimsLessonVo;
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
import java.util.*;

//import org.jeecg.modules.demo.sims.entity.SimsLesson;
//import org.jeecg.modules.demo.sims.service.ISimsLessonService;

/**
* @Description: 课程列表
* @Author: jeecg-boot
* @Date:   2019-08-21
* @Version: V1.0
*/
@Slf4j
@Api(tags="Server-课程列表")
@RestController
@RequestMapping("/sims/simsLesson")
public class SimsLessonController {
   @Autowired
   private ISimsLessonService simsLessonService;

    @Autowired
    private ISimsTeacherService simsTeacherService;


    @Autowired
    private ISimsStudentService simsStudentService;

   /**
     * 分页列表查询
    * @param simsLesson
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "课程列表-分页列表查询")
   @ApiOperation(value="课程列表-分页列表查询", notes="课程列表-分页列表查询")
   @GetMapping(value = "/list")
   public Result<IPage<SimsLessonVo>> queryPageList(SimsLesson simsLesson,
                                                    @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                    HttpServletRequest req) {
//       Result<IPage<SimsLesson>> result = new Result<IPage<SimsLesson>>();
//       String teacherName = req.getParameter("teacherName");
//
//       QueryWrapper<SimsTeacher> queryWrapperSimsTeacher = new QueryWrapper<SimsTeacher>();
//       queryWrapperSimsTeacher.eq("Name",teacherName);
//       SimsTeacher simsTeacher = simsTeacherService.getOne(queryWrapperSimsTeacher);
//       if(simsTeacher!=null){
//           simsLesson.setTeacherId(simsTeacher.getId());
//       }
//       QueryWrapper<SimsLesson> queryWrapper = QueryGenerator.initQueryWrapper(simsLesson, req.getParameterMap());
//       Page<SimsLesson> page = new Page<SimsLesson>(pageNo, pageSize);
//       IPage<SimsLesson> pageList = simsLessonService.page(page, queryWrapper);
//       result.setSuccess(true);
//       result.setResult(pageList);
//       return result;


       String teacherName = req.getParameter("teacherName");
       QueryWrapper<SimsTeacher> queryWrapperSimsTeacher = new QueryWrapper<SimsTeacher>();
       queryWrapperSimsTeacher.eq("Name",teacherName);
       SimsTeacher simsTeacher = simsTeacherService.getOne(queryWrapperSimsTeacher);


       String studentName = req.getParameter("studentName");
       QueryWrapper<SimsStudent> queryWrapperSimsStudent = new QueryWrapper<SimsStudent>();
       queryWrapperSimsStudent.eq("Name",studentName);
       SimsStudent simsStudent = simsStudentService.getOne(queryWrapperSimsStudent);

       log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
       log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
       log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
       log.error("simsLesson------"+ JSONObject.toJSONString(simsLesson));
       Result<IPage<SimsLessonVo>> result = new Result<IPage<SimsLessonVo>>();
       Map params = new HashMap();
       SimsLessonVo simsLessonVo = new SimsLessonVo();
       if(simsTeacher!=null){
           simsLesson.setTeacherId(simsTeacher.getId());
       }

       if(simsStudent!=null){
           simsLesson.setStudentId(simsStudent.getId());
       }
       Page<SimsLessonVo> page = new Page<SimsLessonVo>(pageNo, pageSize);
//       QueryWrapper<SimsLessonVo> queryWrapper = QueryGenerator.initQueryWrapper(simsLessonVo, req.getParameterMap());
       IPage<SimsLessonVo> pageList = simsLessonService.querySimsLessonByParamsAdmin(page, simsLesson);
       result.setSuccess(true);
       result.setResult(pageList);
       return result;

   }

   /**
     *   添加
    * @param simsLesson
    * @return
    */
   @AutoLog(value = "课程列表-添加")
   @ApiOperation(value="课程列表-添加", notes="课程列表-添加")
   @PostMapping(value = "/add")
   public Result<SimsLesson> add(@RequestBody SimsLesson simsLesson) {
       Result<SimsLesson> result = new Result<SimsLesson>();
       try {
           simsLesson.setCreateTime(new Date());
           long roomId = new Date().getTime()/1000;
           simsLesson.setRoomId(roomId+"");
           simsLesson.setLessonStatus(PetrusConstant.LESSON_STATUS.NONE);
           simsLessonService.save(simsLesson);
           result.success("添加成功！");
       } catch (Exception e) {
           log.error(e.getMessage(),e);
           result.error500("操作失败");
       }
       return result;
   }

   /**
     *  编辑
    * @param simsLesson
    * @return
    */
   @AutoLog(value = "课程列表-编辑")
   @ApiOperation(value="课程列表-编辑", notes="课程列表-编辑")
   @PutMapping(value = "/edit")
   public Result<SimsLesson> edit(@RequestBody SimsLesson simsLesson) {
       Result<SimsLesson> result = new Result<SimsLesson>();
       SimsLesson simsLessonEntity = simsLessonService.getById(simsLesson.getId());
       if(simsLessonEntity==null) {
           result.error500("未找到对应实体");
       }else {
           boolean ok = simsLessonService.updateById(simsLesson);
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
   @AutoLog(value = "课程列表-通过id删除")
   @ApiOperation(value="课程列表-通过id删除", notes="课程列表-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       try {
           simsLessonService.removeById(id);
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
   @AutoLog(value = "课程列表-批量删除")
   @ApiOperation(value="课程列表-批量删除", notes="课程列表-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<SimsLesson> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       Result<SimsLesson> result = new Result<SimsLesson>();
       if(ids==null || "".equals(ids.trim())) {
           result.error500("参数不识别！");
       }else {
           this.simsLessonService.removeByIds(Arrays.asList(ids.split(",")));
           result.success("删除成功!");
       }
       return result;
   }

   /**
     * 通过id查询
    * @param id
    * @return
    */
   @AutoLog(value = "课程列表-通过id查询")
   @ApiOperation(value="课程列表-通过id查询", notes="课程列表-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<SimsLesson> queryById(@RequestParam(name="id",required=true) String id) {
       Result<SimsLesson> result = new Result<SimsLesson>();
       SimsLesson simsLesson = simsLessonService.getById(id);
       if(simsLesson==null) {
           result.error500("未找到对应实体");
       }else {
           result.setResult(simsLesson);
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
     QueryWrapper<SimsLesson> queryWrapper = null;
     try {
         String paramsStr = request.getParameter("paramsStr");
         if (oConvertUtils.isNotEmpty(paramsStr)) {
             String deString = URLDecoder.decode(paramsStr, "UTF-8");
             SimsLesson simsLesson = JSON.parseObject(deString, SimsLesson.class);
             queryWrapper = QueryGenerator.initQueryWrapper(simsLesson, request.getParameterMap());
         }
     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     }

     //Step.2 AutoPoi 导出Excel
     ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
     List<SimsLesson> pageList = simsLessonService.list(queryWrapper);
     //导出文件名称
     mv.addObject(NormalExcelConstants.FILE_NAME, "课程列表列表");
     mv.addObject(NormalExcelConstants.CLASS, SimsLesson.class);
     mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("课程列表列表数据", "导出人:Jeecg", "导出信息"));
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
             List<SimsLesson> listSimsLessons = ExcelImportUtil.importExcel(file.getInputStream(), SimsLesson.class, params);
             simsLessonService.saveBatch(listSimsLessons);
             return Result.ok("文件导入成功！数据行数:" + listSimsLessons.size());
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

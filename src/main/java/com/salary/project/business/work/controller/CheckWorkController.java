package com.salary.project.business.work.controller;

import com.salary.common.utils.DateUtils;
import com.salary.common.utils.StringUtils;
import com.salary.common.utils.TimeHelper;
import com.salary.common.utils.poi.ExcelUtil;
import com.salary.common.utils.security.ShiroUtils;
import com.salary.framework.aspectj.lang.annotation.Log;
import com.salary.framework.aspectj.lang.enums.BusinessType;
import com.salary.framework.web.controller.BaseController;
import com.salary.framework.web.domain.AjaxResult;
import com.salary.framework.web.page.TableDataInfo;
import com.salary.project.business.work.domain.CheckWork;
import com.salary.project.business.work.service.ICheckWorkService;
import com.salary.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 考勤Controller
 *
 * @author au
 * @date 2021-07-07
 */
@Controller
@RequestMapping("/salary/work")
public class CheckWorkController extends BaseController
{
    private String prefix = "business/work";

    @Autowired
    private ICheckWorkService checkWorkService;


    @RequiresPermissions("salary:work:view")
    @GetMapping()
    public String work()
    {
        return prefix + "/work";
    }

    /**
     * 查询考勤列表
     */
    @RequiresPermissions("salary:work:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CheckWork checkWork)
    {
        User user = ShiroUtils.getSysUser();
        List<Map<String,Object>> userRole = checkWorkService.getUserRole(user.getUserId()) ;
        String roleId = userRole.get(0).get("role_id").toString() ;
        if("2".equals(roleId)){
            checkWork.setUserId(user.getUserId());
        }
        startPage();
        List<CheckWork> list = checkWorkService.selectCheckWorkList(checkWork);
        return getDataTable(list);
    }

    /**
     * 导出考勤列表
     */
    @RequiresPermissions("salary:work:export")
    @Log(title = "考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CheckWork checkWork)
    {
        List<CheckWork> list = checkWorkService.selectCheckWorkList(checkWork);
        ExcelUtil<CheckWork> util = new ExcelUtil<CheckWork>(CheckWork.class);
        return util.exportExcel(list, "考勤数据");
    }

    /**
     * 新增考勤
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 下班打卡
     */
    @GetMapping("/closeWork")
    public String closeWork()
    {
        return prefix + "/closeWrok";
    }

    /**
     * 新增保存考勤
     */
    @RequiresPermissions("salary:work:add")
    @Log(title = "考勤", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CheckWork checkWork) throws ParseException {
        //获取登录人信息
        User user =  ShiroUtils.getSysUser() ;
        //获取当前日期
        String mowTime =  TimeHelper.getNowTime_yyyy_MM_dd_HH_ss_mm().substring(0,10);
        //保存用戶信息
        checkWork.setUserId(user.getUserId());
        checkWork.setUserName(user.getUserName());
        //当天已请假，不允许打卡
        CheckWork checkWorkLevel = new CheckWork() ;
        checkWorkLevel.setUserId(user.getUserId());
        checkWorkLevel.setDate(DateUtils.getDate());
        checkWorkLevel.setState("0");
        List<CheckWork> isLecel = checkWorkService.selectCheckWorkList(checkWorkLevel) ;
        if(isLecel.size() > 0){
            return error("您今天已请假，无需打卡，详情请看您的请假条") ;
        }
        //打卡逻辑
        CheckWork search = new CheckWork() ;
        search.setUserId(user.getUserId());
        search.setDate(mowTime);
        List<CheckWork> list = checkWorkService.selectCheckWorkList(search);
        //上班打卡
        if(list.size() == 0){
            if(StringUtils.isBlank(checkWork.getWorkTime())){
                return error("请先执行上班打卡") ;
            }
            return toAjax(checkWorkService.insertCheckWork(checkWork));
        }else{
            //下班打卡
            if(StringUtils.isBlank(checkWork.getCloseTime()) && StringUtils.isNotBlank(checkWork.getWorkTime())){
                return error("上班已打卡，下班打卡请执行下班打卡") ;
            }
            if(StringUtils.isNotBlank(list.get(0).getCloseTime()) && StringUtils.isNotBlank(list.get(0).getWorkTime())){
                return error("今日打卡已完成，请勿重复") ;
            }
            search = list.get(0);
            search.setCloseTime(checkWork.getCloseTime()) ;
//            search.setWorkTime(list.get(0).getWorkTime());
            //上班状态计算
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
            String gd_work_string = new String(mowTime+" 09:00:00");
            String gd_close_string = new String(mowTime+" 17:00:00");
            String a = search.getWorkTime();
            String b = search.getCloseTime();
            String sj_work_string = new String(mowTime+" "+ a);
            String sj_close_string = new String(mowTime+" "+ b);

            long gd_work_int = Long.valueOf(gd_work_string.replaceAll("[-\\s:]",""));
            long gd_close_int = Long.valueOf(gd_close_string.replaceAll("[-\\s:]",""));
            long sj_work_int = Long.valueOf(sj_work_string.replaceAll("[-\\s:]",""));
            long sj_close_int = Long.valueOf(sj_close_string.replaceAll("[-\\s:]",""));
            //判断迟到早退
            if(gd_work_int >= sj_work_int && gd_close_int <= sj_close_int){
                search.setState("1");
            }else if(gd_work_int < sj_work_int && gd_close_int < sj_close_int){
                search.setState("2");
            }else if(gd_work_int > sj_work_int && gd_close_int > sj_close_int){
                search.setState("3");
            }else{
                search.setState("4");
            }
            return toAjax(checkWorkService.updateCheckWork(search));
        }
    }

    /**
     * 修改考勤
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CheckWork checkWork = checkWorkService.selectCheckWorkById(id);
        mmap.put("checkWork", checkWork);
        return prefix + "/edit";
    }

    /**
     * 修改保存考勤
     */
    @RequiresPermissions("salary:work:edit")
    @Log(title = "考勤", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CheckWork checkWork)
    {
        return toAjax(checkWorkService.updateCheckWork(checkWork));
    }

    /**
     * 删除考勤
     */
    @RequiresPermissions("salary:work:remove")
    @Log(title = "考勤", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(checkWorkService.deleteCheckWorkByIds(ids));
    }
}

package com.salary.project.business.level.controller;

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
import com.salary.project.business.level.domain.Level;
import com.salary.project.business.level.service.ILevelService;
import com.salary.project.business.work.domain.CheckWork;
import com.salary.project.business.work.service.ICheckWorkService;
import com.salary.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 请假Controller
 * 
 * @author au
 * @date 2021-07-08
 */
@Controller
@RequestMapping("/business/level")
public class LevelController extends BaseController
{
    private String prefix = "business/level";

    @Autowired
    private ILevelService levelService;

    @Autowired
    private ICheckWorkService checkWorkService ;

    @RequiresPermissions("business:level:view")
    @GetMapping()
    public String level()
    {
        return prefix + "/level";
    }

    /**
     * 查询请假列表
     */
    @RequiresPermissions("business:level:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Level level)
    {
        startPage();
        User user = ShiroUtils.getSysUser();
        List<Map<String,Object>> userRole = checkWorkService.getUserRole(user.getUserId()) ;
        String roleId = userRole.get(0).get("role_id").toString() ;
        if("2".equals(roleId)){
            level.setUserId(user.getUserId());
        }
        List<Level> list = levelService.selectLevelList(level);
        return getDataTable(list);
    }

    /**
     * 导出请假列表
     */
    @RequiresPermissions("business:level:export")
    @Log(title = "请假", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Level level)
    {
        List<Level> list = levelService.selectLevelList(level);
        ExcelUtil<Level> util = new ExcelUtil<Level>(Level.class);
        return util.exportExcel(list, "请假数据");
    }

    /**
     * 新增请假
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存请假
     */
    @RequiresPermissions("business:level:add")
    @Log(title = "请假", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Level level)
    {
        //请假日期小于当前日期不允许
        String nowDate = DateUtils.getDate() ;
        String startDate = level.getDateStart() ;
        long nowDate_long = Long.valueOf(nowDate.replaceAll("[-\\s:]",""));
        long startDate_long = Long.valueOf(startDate.replaceAll("[-\\s:]",""));
        if(nowDate_long > startDate_long){
            return error("请假开始时间不允许小于当前时间") ;
        }
        //获取请加人信息
        User user =  ShiroUtils.getSysUser() ;
        level.setUserId(user.getUserId());
        level.setUserName(user.getUserName());
        return toAjax(levelService.insertLevel(level));
    }

    /**
     * 修改请假
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Level level = levelService.selectLevelById(id);
        mmap.put("level", level);
        return prefix + "/edit";
    }

    /**
     * 修改保存请假
     */
    @RequiresPermissions("business:level:edit")
    @Log(title = "请假", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Level level)
    {
        //已审批不允许修改
        Level levelCheck = levelService.selectLevelById(level.getId()) ;
        if(StringUtils.isNotBlank(levelCheck.getResult())){
            return error("该请假已审批，不可修改") ;
        }
        //请假日期小于当前日期不允许
        String nowDate = DateUtils.getDate() ;
        String startDate = level.getDateStart() ;
        long nowDate_long = Long.valueOf(nowDate.replaceAll("[-\\s:]",""));
        long startDate_long = Long.valueOf(startDate.replaceAll("[-\\s:]",""));
        if(nowDate_long > startDate_long){
            return error("请假开始时间不允许小于当前时间") ;
        }
        return toAjax(levelService.updateLevel(level));
    }

    /**
     * 审批请假
     */
    @GetMapping("/shenpi/{id}")
    public String shenpi(@PathVariable("id") Long id, ModelMap mmap)
    {
        Level level = levelService.selectLevelById(id);
        mmap.put("level", level);
        return prefix + "/shenpi";
    }

    /**
     * 修改保存请假
     */
    @Log(title = "审批", businessType = BusinessType.UPDATE)
    @PostMapping("/shenpi")
    @ResponseBody
    public AjaxResult shenpiSave(Level level)
    {
        //存储请假表信息
        Level levelSet = levelService.selectLevelById(level.getId()) ;
        User user =  ShiroUtils.getSysUser() ;
        levelSet.setApprovalId(user.getUserId());
        levelSet.setApprovalName(user.getUserName());
        levelSet.setResult(level.getResult());
        levelSet.setApprovalReason(level.getApprovalReason());
        levelSet.setApprovalDate(TimeHelper.getNowTime_yyyy_MM_dd_HH_ss_mm());
        //同意请假，更新考勤表
        if(StringUtils.isNotBlank(levelSet.getResult()) && "1".equals(levelSet.getResult())){
            List<String> levelDates = DateUtils.getDays(levelSet.getDateStart(), levelSet.getDateEnd(), "yyyy-MM-dd") ;
            CheckWork checkWorkSearch = new CheckWork() ;
            checkWorkSearch.setUserId(levelSet.getUserId());
            checkWorkSearch.setUserName(levelSet.getUserName());
            checkWorkSearch.setState("0");
            checkWorkSearch.setLevelId(levelSet.getId());
            checkWorkSearch.setLevelState(levelSet.getResult());
            for(String levelDate : levelDates){
                checkWorkSearch.setDate(levelDate);
                checkWorkService.insertCheckWork(checkWorkSearch) ;

            }
        }
        //存储审批条件
        return toAjax(levelService.updateLevel(levelSet));
    }

    /**
     * 删除请假
     */
    @RequiresPermissions("business:level:remove")
    @Log(title = "请假", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        Level level = levelService.selectLevelById(Long.parseLong(ids)) ;
        //已审批不允许修改
        if(StringUtils.isNotBlank(level.getResult())){
            return error("该请假已审批，不可修改") ;
        }
        return toAjax(levelService.deleteLevelByIds(ids));
    }
}

package com.salary.project.salary.controller;

import com.salary.common.utils.poi.ExcelUtil;
import com.salary.framework.aspectj.lang.annotation.Log;
import com.salary.framework.aspectj.lang.enums.BusinessType;
import com.salary.framework.web.controller.BaseController;
import com.salary.framework.web.domain.AjaxResult;
import com.salary.framework.web.page.TableDataInfo;
import com.salary.project.business.salaryallocation.service.IPostSalaryallocationService;
import com.salary.project.salary.domain.SalaryAllocation;
import com.salary.project.salary.service.ISalaryAllocationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 薪资配置Controller
 * 
 * @author au
 * @date 2021-07-05
 */
@Controller
@RequestMapping("/salary/allocation")
public class SalaryAllocationController extends BaseController
{
    private String prefix = "salary";

    @Autowired
    private ISalaryAllocationService salaryAllocationService;

    @Autowired
    private IPostSalaryallocationService postSalaryallocationService ;

    @RequiresPermissions("salary:allocation:view")
    @GetMapping()
    public String allocation()
    {
        return prefix + "/allocation";
    }

    /**
     * 查询薪资配置列表
     */
    @RequiresPermissions("salary:allocation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SalaryAllocation salaryAllocation)
    {
        startPage();
        List<SalaryAllocation> list = salaryAllocationService.selectSalaryAllocationList(salaryAllocation);
        return getDataTable(list);
    }

    /**
     * 导出薪资配置列表
     */
    @RequiresPermissions("salary:allocation:export")
    @Log(title = "薪资配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SalaryAllocation salaryAllocation)
    {
        List<SalaryAllocation> list = salaryAllocationService.selectSalaryAllocationList(salaryAllocation);
        ExcelUtil<SalaryAllocation> util = new ExcelUtil<SalaryAllocation>(SalaryAllocation.class);
        return util.exportExcel(list, "薪资配置数据");
    }

    /**
     * 新增薪资配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存薪资配置
     */
    @RequiresPermissions("salary:allocation:add")
    @Log(title = "薪资配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SalaryAllocation salaryAllocation)
    {
        return toAjax(salaryAllocationService.insertSalaryAllocation(salaryAllocation));
    }

    /**
     * 修改薪资配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SalaryAllocation salaryAllocation = salaryAllocationService.selectSalaryAllocationById(id);
        mmap.put("salaryAllocation", salaryAllocation);
        return prefix + "/edit";
    }

    /**
     * 修改保存薪资配置
     */
    @RequiresPermissions("salary:allocation:edit")
    @Log(title = "薪资配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SalaryAllocation salaryAllocation)
    {
        return toAjax(salaryAllocationService.updateSalaryAllocation(salaryAllocation));
    }

    /**
     * 删除薪资配置
     */
    @RequiresPermissions("salary:allocation:remove")
    @Log(title = "薪资配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        //删除关联表
        String[] split = ids.split(",");

        for (int i = 0; i < split.length; i++) {
            String id = split[i] ;
            postSalaryallocationService.deleteBySalaryId(id) ;
        }
        return toAjax(salaryAllocationService.deleteSalaryAllocationByIds(ids));
    }
}

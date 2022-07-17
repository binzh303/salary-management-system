package com.salary.project.business.detail.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.salary.framework.aspectj.lang.annotation.Log;
import com.salary.framework.aspectj.lang.enums.BusinessType;
import com.salary.project.business.detail.domain.AccountDetail;
import com.salary.project.business.detail.service.IAccountDetailService;
import com.salary.framework.web.controller.BaseController;
import com.salary.framework.web.domain.AjaxResult;
import com.salary.common.utils.poi.ExcelUtil;
import com.salary.framework.web.page.TableDataInfo;

/**
 * 工资核算详情Controller
 * 
 * @author au
 * @date 2021-07-12
 */
@Controller
@RequestMapping("/business/detail")
public class AccountDetailController extends BaseController
{
    private String prefix = "business/detail";

    @Autowired
    private IAccountDetailService accountDetailService;

    @RequiresPermissions("business:detail:view")
    @GetMapping()
    public String detail()
    {
        return prefix + "/detail";
    }

    /**
     * 查询工资核算详情列表
     */
    @RequiresPermissions("business:detail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccountDetail accountDetail)
    {
        startPage();
        List<AccountDetail> list = accountDetailService.selectAccountDetailList(accountDetail);
        return getDataTable(list);
    }

    /**
     * 导出工资核算详情列表
     */
    @RequiresPermissions("business:detail:export")
    @Log(title = "工资核算详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccountDetail accountDetail)
    {
        List<AccountDetail> list = accountDetailService.selectAccountDetailList(accountDetail);
        ExcelUtil<AccountDetail> util = new ExcelUtil<AccountDetail>(AccountDetail.class);
        return util.exportExcel(list, "工资核算详情数据");
    }

    /**
     * 新增工资核算详情
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工资核算详情
     */
    @RequiresPermissions("business:detail:add")
    @Log(title = "工资核算详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccountDetail accountDetail)
    {
        return toAjax(accountDetailService.insertAccountDetail(accountDetail));
    }

    /**
     * 修改工资核算详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AccountDetail accountDetail = accountDetailService.selectAccountDetailById(id);
        mmap.put("accountDetail", accountDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存工资核算详情
     */
    @RequiresPermissions("business:detail:edit")
    @Log(title = "工资核算详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccountDetail accountDetail)
    {
        return toAjax(accountDetailService.updateAccountDetail(accountDetail));
    }

    /**
     * 删除工资核算详情
     */
    @RequiresPermissions("business:detail:remove")
    @Log(title = "工资核算详情", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(accountDetailService.deleteAccountDetailByIds(ids));
    }
}

package com.salary.project.business.salaryallocation.controller;

import java.util.ArrayList;
import java.util.List;

import com.salary.project.salary.domain.SalaryAllocation;
import com.salary.project.salary.service.ISalaryAllocationService;
import com.salary.project.system.post.domain.Post;
import com.salary.project.system.post.service.IPostService;
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
import com.salary.project.business.salaryallocation.domain.PostSalaryallocation;
import com.salary.project.business.salaryallocation.service.IPostSalaryallocationService;
import com.salary.framework.web.controller.BaseController;
import com.salary.framework.web.domain.AjaxResult;
import com.salary.common.utils.poi.ExcelUtil;
import com.salary.framework.web.page.TableDataInfo;

/**
 * 岗位-薪资配置关联Controller
 * 
 * @author au
 * @date 2021-07-10
 */
@Controller
@RequestMapping("/business/salaryallocation")
public class PostSalaryallocationController extends BaseController
{
    private String prefix = "business/salaryallocation";

    @Autowired
    private IPostSalaryallocationService postSalaryallocationService;

    @Autowired
    private IPostService postService;

    @Autowired
    private ISalaryAllocationService salaryAllocationService;

    /**
     * 详细薪资配置  -  查询岗位对应薪资配置列表
     */
    @RequiresPermissions("salary:allocation:list")
    @PostMapping("/salaryallocationDetaillist")
    @ResponseBody
    public TableDataInfo salaryallocationDetaillist(SalaryAllocation salaryAllocation1)
    {
        startPage();
        //关联表信息
        PostSalaryallocation postSalaryallocation = new PostSalaryallocation() ;
        postSalaryallocation.setPostId(salaryAllocation1.getId());
        List<PostSalaryallocation> lists =  postSalaryallocationService.selectPostSalaryallocationList(postSalaryallocation);
        //循环关联表，得到配置表详情
        List<SalaryAllocation> salaryAllocations = new ArrayList<>() ;
        for(PostSalaryallocation list : lists){
            SalaryAllocation salaryAllocation = salaryAllocationService.selectSalaryAllocationById(list.getSalaryAllocationId()) ;
            salaryAllocation.setId(list.getId());//id处理
            salaryAllocations.add(salaryAllocation) ;
        }
        return getDataTable(salaryAllocations);
    }

    /**
     *跳转到  详细薪资配置  页面
     */
//    @RequiresPermissions("system:dict:list")
    @GetMapping("/salaryallocationDetail/{postId}")
    public String salaryallocationDetail(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        mmap.put("postId",postId);
        return prefix + "/allocation";
    }

    @RequiresPermissions("business:postList:view")
    @GetMapping()
    public String postList()
    {
        return prefix + "/post";
    }

    /**
     * 岗位薪资配置 - 岗位列表
     */
    @RequiresPermissions("business:post:list")
    @PostMapping("/postList")
    @ResponseBody
    public TableDataInfo list(Post post)
    {
        startPage();
        List<Post> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    /**
     * 跳转到 岗位薪资配置列表
     */
    @RequiresPermissions("business:salaryallocation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PostSalaryallocation postSalaryallocation)
    {
        startPage();
        List<PostSalaryallocation> list = postSalaryallocationService.selectPostSalaryallocationList(postSalaryallocation);
        return getDataTable(list);
    }

    /**
     * 导出岗位-薪资配置关联列表
     */
    @RequiresPermissions("business:salaryallocation:export")
    @Log(title = "岗位-薪资配置关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PostSalaryallocation postSalaryallocation)
    {
        List<PostSalaryallocation> list = postSalaryallocationService.selectPostSalaryallocationList(postSalaryallocation);
        ExcelUtil<PostSalaryallocation> util = new ExcelUtil<PostSalaryallocation>(PostSalaryallocation.class);
        return util.exportExcel(list, "岗位-薪资配置关联数据");
    }

    /**
     * 跳转到  添加薪资配置   页面
     */
    @GetMapping("/add/{postId}")
    public String add(@PathVariable("postId") String postId, ModelMap mmap)
    {
        mmap.put("postId", postId);
        //获取岗位信息
        Post post = postService.selectPostById(Long.parseLong(postId)) ;
        mmap.put("post",post) ;
        //获取信息配置信息
        List<SalaryAllocation> salaryAllocations = salaryAllocationService.selectSalaryAllocationList(new SalaryAllocation()) ;
        //循环配置项，判断用户是否有此配置
        for(SalaryAllocation salaryAllocationFlag:salaryAllocations){
            PostSalaryallocation postSalaryallocation = new PostSalaryallocation() ;
            postSalaryallocation.setPostId(Long.parseLong(postId));
            postSalaryallocation.setSalaryAllocationId(salaryAllocationFlag.getId());
            List<PostSalaryallocation> list = postSalaryallocationService.selectPostSalaryallocationList(postSalaryallocation) ;
            if(list.size() > 0){
                salaryAllocationFlag.setFlag(true);
            }

        }
        mmap.put("salaryAllocations",salaryAllocations) ;
        return prefix + "/add";
    }

    /**
     * 添加  薪资配置  保存
     */
    @RequiresPermissions("business:salaryallocation:add")
    @Log(title = "岗位-薪资配置关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PostSalaryallocation postSalaryallocation)
    {
        //循环配置项  保存未添加的配置
        Long[] salaryAllocationIds = postSalaryallocation.getSalaryAllocationIds() ;
        for(int i=0;i<salaryAllocationIds.length;i++){
            PostSalaryallocation postSalaryallocationSearch = new PostSalaryallocation() ;
            postSalaryallocationSearch.setPostId(postSalaryallocation.getPostId());
            postSalaryallocationSearch.setSalaryAllocationId(salaryAllocationIds[i]);
            List<PostSalaryallocation> postSalaryallocations = postSalaryallocationService.selectPostSalaryallocationList(postSalaryallocationSearch) ;
            if(postSalaryallocations.size() == 0){
                postSalaryallocationService.insertPostSalaryallocation(postSalaryallocationSearch) ;
            }
        }
        return success() ;
    }

    /**
     * 修改岗位-薪资配置关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PostSalaryallocation postSalaryallocation = postSalaryallocationService.selectPostSalaryallocationById(id);
        mmap.put("postSalaryallocation", postSalaryallocation);
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位-薪资配置关联
     */
    @RequiresPermissions("business:salaryallocation:edit")
    @Log(title = "岗位-薪资配置关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PostSalaryallocation postSalaryallocation)
    {
        return toAjax(postSalaryallocationService.updatePostSalaryallocation(postSalaryallocation));
    }

    /**
     * 删除岗位-薪资配置关联
     */
    @RequiresPermissions("business:salaryallocation:remove")
    @Log(title = "岗位-薪资配置关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(postSalaryallocationService.deletePostSalaryallocationByIds(ids));
    }
}

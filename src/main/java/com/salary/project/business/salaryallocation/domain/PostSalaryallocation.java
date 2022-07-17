package com.salary.project.business.salaryallocation.domain;

import com.salary.framework.aspectj.lang.annotation.Excel;
import com.salary.framework.web.domain.BaseEntity;

import java.util.Arrays;

/**
 * 岗位-薪资配置关联对象 tbl_post_salaryallocation
 * 
 * @author au
 * @date 2021-07-10
 */
public class PostSalaryallocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 岗位主键 */
    @Excel(name = "岗位主键")
    private Long postId;

    /** 薪资配置表id */
    @Excel(name = "薪资配置表id")
    private Long salaryAllocationId;

    /** 配置组 */
    private Long[] salaryAllocationIds;

    @Override
    public String toString() {
        return "PostSalaryallocation{" +
                "id=" + id +
                ", postId=" + postId +
                ", salaryAllocationId=" + salaryAllocationId +
                ", salaryAllocationIds=" + Arrays.toString(salaryAllocationIds) +
                '}';
    }

    public Long[] getSalaryAllocationIds() {
        return salaryAllocationIds;
    }

    public void setSalaryAllocationIds(Long[] salaryAllocationIds) {
        this.salaryAllocationIds = salaryAllocationIds;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    public Long getPostId()
    {
        return postId;
    }
    public void setSalaryAllocationId(Long salaryAllocationId)
    {
        this.salaryAllocationId = salaryAllocationId;
    }

    public Long getSalaryAllocationId()
    {
        return salaryAllocationId;
    }

}

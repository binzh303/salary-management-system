package com.salary.project.business.salaryallocation.service;

import java.util.List;
import com.salary.project.business.salaryallocation.domain.PostSalaryallocation;

/**
 * 岗位-薪资配置关联Service接口
 * 
 * @author au
 * @date 2021-07-10
 */
public interface IPostSalaryallocationService 
{
    /**
     * 查询岗位-薪资配置关联
     * 
     * @param id 岗位-薪资配置关联ID
     * @return 岗位-薪资配置关联
     */
    public PostSalaryallocation selectPostSalaryallocationById(Long id);

    /**
     * 查询岗位-薪资配置关联列表
     * 
     * @param postSalaryallocation 岗位-薪资配置关联
     * @return 岗位-薪资配置关联集合
     */
    public List<PostSalaryallocation> selectPostSalaryallocationList(PostSalaryallocation postSalaryallocation);

    /**
     * 新增岗位-薪资配置关联
     * 
     * @param postSalaryallocation 岗位-薪资配置关联
     * @return 结果
     */
    public int insertPostSalaryallocation(PostSalaryallocation postSalaryallocation);

    /**
     * 修改岗位-薪资配置关联
     * 
     * @param postSalaryallocation 岗位-薪资配置关联
     * @return 结果
     */
    public int updatePostSalaryallocation(PostSalaryallocation postSalaryallocation);

    /**
     * 批量删除岗位-薪资配置关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePostSalaryallocationByIds(String ids);

    /**
     * 删除岗位-薪资配置关联信息
     * 
     * @param id 岗位-薪资配置关联ID
     * @return 结果
     */
    public int deletePostSalaryallocationById(Long id);

    void deleteBySalaryId(String id);
}

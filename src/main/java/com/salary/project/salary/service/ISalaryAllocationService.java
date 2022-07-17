package com.salary.project.salary.service;

import com.salary.project.salary.domain.SalaryAllocation;

import java.util.List;

/**
 * 薪资配置Service接口
 * 
 * @author au
 * @date 2021-07-05
 */
public interface ISalaryAllocationService 
{
    /**
     * 查询薪资配置
     * 
     * @param id 薪资配置ID
     * @return 薪资配置
     */
    public SalaryAllocation selectSalaryAllocationById(Long id);

    /**
     * 查询薪资配置列表
     * 
     * @param salaryAllocation 薪资配置
     * @return 薪资配置集合
     */
    public List<SalaryAllocation> selectSalaryAllocationList(SalaryAllocation salaryAllocation);

    /**
     * 新增薪资配置
     * 
     * @param salaryAllocation 薪资配置
     * @return 结果
     */
    public int insertSalaryAllocation(SalaryAllocation salaryAllocation);

    /**
     * 修改薪资配置
     * 
     * @param salaryAllocation 薪资配置
     * @return 结果
     */
    public int updateSalaryAllocation(SalaryAllocation salaryAllocation);

    /**
     * 批量删除薪资配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalaryAllocationByIds(String ids);

    /**
     * 删除薪资配置信息
     * 
     * @param id 薪资配置ID
     * @return 结果
     */
    public int deleteSalaryAllocationById(Long id);
}

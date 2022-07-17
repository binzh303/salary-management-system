package com.salary.project.salary.service.impl;

import com.salary.common.utils.DateUtils;
import com.salary.common.utils.text.Convert;
import com.salary.project.salary.domain.SalaryAllocation;
import com.salary.project.salary.mapper.SalaryAllocationMapper;
import com.salary.project.salary.service.ISalaryAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 薪资配置Service业务层处理
 * 
 * @author au
 * @date 2021-07-05
 */
@Service
public class SalaryAllocationServiceImpl implements ISalaryAllocationService
{
    @Autowired
    private SalaryAllocationMapper salaryAllocationMapper;

    /**
     * 查询薪资配置
     * 
     * @param id 薪资配置ID
     * @return 薪资配置
     */
    @Override
    public SalaryAllocation selectSalaryAllocationById(Long id)
    {
        return salaryAllocationMapper.selectSalaryAllocationById(id);
    }

    /**
     * 查询薪资配置列表
     * 
     * @param salaryAllocation 薪资配置
     * @return 薪资配置
     */
    @Override
    public List<SalaryAllocation> selectSalaryAllocationList(SalaryAllocation salaryAllocation)
    {
        return salaryAllocationMapper.selectSalaryAllocationList(salaryAllocation);
    }

    /**
     * 新增薪资配置
     * 
     * @param salaryAllocation 薪资配置
     * @return 结果
     */
    @Override
    public int insertSalaryAllocation(SalaryAllocation salaryAllocation)
    {
        salaryAllocation.setCreateTime(DateUtils.getNowDate());
        return salaryAllocationMapper.insertSalaryAllocation(salaryAllocation);
    }

    /**
     * 修改薪资配置
     * 
     * @param salaryAllocation 薪资配置
     * @return 结果
     */
    @Override
    public int updateSalaryAllocation(SalaryAllocation salaryAllocation)
    {
        return salaryAllocationMapper.updateSalaryAllocation(salaryAllocation);
    }

    /**
     * 删除薪资配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSalaryAllocationByIds(String ids)
    {
        return salaryAllocationMapper.deleteSalaryAllocationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除薪资配置信息
     * 
     * @param id 薪资配置ID
     * @return 结果
     */
    @Override
    public int deleteSalaryAllocationById(Long id)
    {
        return salaryAllocationMapper.deleteSalaryAllocationById(id);
    }
}

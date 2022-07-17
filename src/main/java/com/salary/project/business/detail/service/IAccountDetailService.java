package com.salary.project.business.detail.service;

import java.util.List;
import com.salary.project.business.detail.domain.AccountDetail;

/**
 * 工资核算详情Service接口
 * 
 * @author au
 * @date 2021-07-12
 */
public interface IAccountDetailService 
{
    /**
     * 查询工资核算详情
     * 
     * @param id 工资核算详情ID
     * @return 工资核算详情
     */
    public AccountDetail selectAccountDetailById(Long id);

    /**
     * 查询工资核算详情列表
     * 
     * @param accountDetail 工资核算详情
     * @return 工资核算详情集合
     */
    public List<AccountDetail> selectAccountDetailList(AccountDetail accountDetail);

    /**
     * 新增工资核算详情
     * 
     * @param accountDetail 工资核算详情
     * @return 结果
     */
    public int insertAccountDetail(AccountDetail accountDetail);

    /**
     * 修改工资核算详情
     * 
     * @param accountDetail 工资核算详情
     * @return 结果
     */
    public int updateAccountDetail(AccountDetail accountDetail);

    /**
     * 批量删除工资核算详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccountDetailByIds(String ids);

    /**
     * 删除工资核算详情信息
     * 
     * @param id 工资核算详情ID
     * @return 结果
     */
    public int deleteAccountDetailById(Long id);
}

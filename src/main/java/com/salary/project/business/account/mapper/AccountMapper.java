package com.salary.project.business.account.mapper;

import java.util.List;
import com.salary.project.business.account.domain.Account;

/**
 * 工资核算Mapper接口
 * 
 * @author au
 * @date 2021-07-12
 */
public interface AccountMapper 
{
    /**
     * 查询工资核算
     * 
     * @param id 工资核算ID
     * @return 工资核算
     */
    public Account selectAccountById(Long id);

    /**
     * 查询工资核算列表
     * 
     * @param account 工资核算
     * @return 工资核算集合
     */
    public List<Account> selectAccountList(Account account);

    /**
     * 新增工资核算
     * 
     * @param account 工资核算
     * @return 结果
     */
    public int insertAccount(Account account);

    /**
     * 修改工资核算
     * 
     * @param account 工资核算
     * @return 结果
     */
    public int updateAccount(Account account);

    /**
     * 删除工资核算
     * 
     * @param id 工资核算ID
     * @return 结果
     */
    public int deleteAccountById(Long id);

    /**
     * 批量删除工资核算
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccountByIds(String[] ids);
}

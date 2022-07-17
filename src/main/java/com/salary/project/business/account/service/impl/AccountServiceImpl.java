package com.salary.project.business.account.service.impl;

import java.util.List;
import java.util.Map;

import com.salary.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.salary.project.business.account.mapper.AccountMapper;
import com.salary.project.business.account.domain.Account;
import com.salary.project.business.account.service.IAccountService;
import com.salary.common.utils.text.Convert;

/**
 * 工资核算Service业务层处理
 * 
 * @author au
 * @date 2021-07-12
 */
@Service
public class AccountServiceImpl implements IAccountService 
{
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    /**
     * 查询工资核算
     * 
     * @param id 工资核算ID
     * @return 工资核算
     */
    @Override
    public Account selectAccountById(Long id)
    {
        return accountMapper.selectAccountById(id);
    }

    /**
     * 查询工资核算列表
     * 
     * @param account 工资核算
     * @return 工资核算
     */
    @Override
    public List<Account> selectAccountList(Account account)
    {
        return accountMapper.selectAccountList(account);
    }

    /**
     * 新增工资核算
     * 
     * @param account 工资核算
     * @return 结果
     */
    @Override
    public int insertAccount(Account account)
    {
        account.setCreateTime(DateUtils.getNowDate());
        return accountMapper.insertAccount(account);
    }

    /**
     * 修改工资核算
     * 
     * @param account 工资核算
     * @return 结果
     */
    @Override
    public int updateAccount(Account account)
    {
        return accountMapper.updateAccount(account);
    }

    /**
     * 删除工资核算对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAccountByIds(String ids)
    {
        return accountMapper.deleteAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工资核算信息
     * 
     * @param id 工资核算ID
     * @return 结果
     */
    @Override
    public int deleteAccountById(Long id)
    {
        return accountMapper.deleteAccountById(id);
    }

    @Override
    public List<Map<String, Object>> selectUserList() {
        StringBuffer sql = new StringBuffer() ;
        sql.append("select t1.user_id ,t1.user_name,t1.create_time,t3.post_id,t3.post_name from sys_user t1 \n" +
                "left join sys_user_post t2 on t1.user_id = t2.user_id \n" +
                "left join sys_post t3 on t2.post_id = t3.post_id\n" +
                "where t1.status = '0'\n" +
                "and t1.user_id != '1'") ;
        return jdbcTemplate.queryForList(sql.toString());
    }

    @Override
    public List<Map<String, Object>> setlectAllocations(String postId) {
        StringBuffer sql = new StringBuffer() ;
        sql.append("select t3.id,t3.name,t3.type,t3.rule,t3.money from sys_post t1 \n" +
                "left join tbl_post_salaryallocation t2 on t1.post_id = t2.post_id\n" +
                "left join tbl_salary_allocation t3 on t2.salary_allocation_id = t3.id\n" +
                "and t3.state = '1'") ;
        sql.append("where t1.post_id = '").append(postId).append("'") ;
        return jdbcTemplate.queryForList(sql.toString());
    }

    @Override
    public void deleteAccountDetail(Long ids) {
        StringBuffer sql = new StringBuffer() ;
        sql.append("delete from tbl_account_detail where account_id = ?") ;
        jdbcTemplate.update(sql.toString(),new Object[]{ids});
    }

    @Override
    public List<Map<String,Object>> selectAccount(Account account) {
        StringBuffer sql = new StringBuffer() ;
        sql.append("select * from tbl_account t where t.date = '").append(account.getDate()).append("'") ;
        sql.append(" and t.user_id = '").append(account.getUserId()).append("'") ;
        return jdbcTemplate.queryForList(sql.toString());
    }
}

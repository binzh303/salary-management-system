package com.salary.project.business.detail.service.impl;

import java.util.List;
import com.salary.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salary.project.business.detail.mapper.AccountDetailMapper;
import com.salary.project.business.detail.domain.AccountDetail;
import com.salary.project.business.detail.service.IAccountDetailService;
import com.salary.common.utils.text.Convert;

/**
 * 工资核算详情Service业务层处理
 * 
 * @author au
 * @date 2021-07-12
 */
@Service
public class AccountDetailServiceImpl implements IAccountDetailService 
{
    @Autowired
    private AccountDetailMapper accountDetailMapper;

    /**
     * 查询工资核算详情
     * 
     * @param id 工资核算详情ID
     * @return 工资核算详情
     */
    @Override
    public AccountDetail selectAccountDetailById(Long id)
    {
        return accountDetailMapper.selectAccountDetailById(id);
    }

    /**
     * 查询工资核算详情列表
     * 
     * @param accountDetail 工资核算详情
     * @return 工资核算详情
     */
    @Override
    public List<AccountDetail> selectAccountDetailList(AccountDetail accountDetail)
    {
        return accountDetailMapper.selectAccountDetailList(accountDetail);
    }

    /**
     * 新增工资核算详情
     * 
     * @param accountDetail 工资核算详情
     * @return 结果
     */
    @Override
    public int insertAccountDetail(AccountDetail accountDetail)
    {
        accountDetail.setCreateTime(DateUtils.getNowDate());
        return accountDetailMapper.insertAccountDetail(accountDetail);
    }

    /**
     * 修改工资核算详情
     * 
     * @param accountDetail 工资核算详情
     * @return 结果
     */
    @Override
    public int updateAccountDetail(AccountDetail accountDetail)
    {
        return accountDetailMapper.updateAccountDetail(accountDetail);
    }

    /**
     * 删除工资核算详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAccountDetailByIds(String ids)
    {
        return accountDetailMapper.deleteAccountDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除工资核算详情信息
     * 
     * @param id 工资核算详情ID
     * @return 结果
     */
    @Override
    public int deleteAccountDetailById(Long id)
    {
        return accountDetailMapper.deleteAccountDetailById(id);
    }
}

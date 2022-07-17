package com.salary.project.business.work.service.impl;

import com.salary.common.utils.text.Convert;
import com.salary.project.business.work.domain.CheckWork;
import com.salary.project.business.work.mapper.CheckWorkMapper;
import com.salary.project.business.work.service.ICheckWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 考勤Service业务层处理
 * 
 * @author au
 * @date 2021-07-07
 */
@Service
public class CheckWorkServiceImpl implements ICheckWorkService
{
    @Autowired
    private CheckWorkMapper checkWorkMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    /**
     * 查询考勤
     * 
     * @param id 考勤ID
     * @return 考勤
     */
    @Override
    public CheckWork selectCheckWorkById(Long id)
    {
        return checkWorkMapper.selectCheckWorkById(id);
    }

    /**
     * 查询考勤列表
     * 
     * @param checkWork 考勤
     * @return 考勤
     */
    @Override
    public List<CheckWork> selectCheckWorkList(CheckWork checkWork)
    {
        return checkWorkMapper.selectCheckWorkList(checkWork);
    }

    /**
     * 新增考勤
     * 
     * @param checkWork 考勤
     * @return 结果
     */
    @Override
    public int insertCheckWork(CheckWork checkWork)
    {

        return checkWorkMapper.insertCheckWork(checkWork);
    }

    /**
     * 修改考勤
     * 
     * @param checkWork 考勤
     * @return 结果
     */
    @Override
    public int updateCheckWork(CheckWork checkWork)
    {
        return checkWorkMapper.updateCheckWork(checkWork);
    }

    /**
     * 删除考勤对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCheckWorkByIds(String ids)
    {
        return checkWorkMapper.deleteCheckWorkByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除考勤信息
     * 
     * @param id 考勤ID
     * @return 结果
     */
    @Override
    public int deleteCheckWorkById(Long id)
    {
        return checkWorkMapper.deleteCheckWorkById(id);
    }

    @Override
    public int getDays(String userId, String firstDay, String lastDay, String state) {
        return checkWorkMapper.getDays(userId,firstDay,lastDay,state);
    }

    @Override
    public List<Map<String,Object>> getUserRole(Long userId) {
        StringBuffer sql = new StringBuffer() ;
        sql.append("select * from sys_user_role t where t.user_id = '").append(userId).append("'") ;
        return  jdbcTemplate.queryForList(sql.toString()) ;
    }
}

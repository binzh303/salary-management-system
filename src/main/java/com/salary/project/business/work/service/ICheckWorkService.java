package com.salary.project.business.work.service;

import com.salary.project.business.work.domain.CheckWork;

import java.util.List;
import java.util.Map;

/**
 * 考勤Service接口
 * 
 * @author au
 * @date 2021-07-07
 */
public interface ICheckWorkService 
{
    /**
     * 查询考勤
     * 
     * @param id 考勤ID
     * @return 考勤
     */
    public CheckWork selectCheckWorkById(Long id);

    /**
     * 查询考勤列表
     * 
     * @param checkWork 考勤
     * @return 考勤集合
     */
    public List<CheckWork> selectCheckWorkList(CheckWork checkWork);

    /**
     * 新增考勤
     * 
     * @param checkWork 考勤
     * @return 结果
     */
    public int insertCheckWork(CheckWork checkWork);

    /**
     * 修改考勤
     * 
     * @param checkWork 考勤
     * @return 结果
     */
    public int updateCheckWork(CheckWork checkWork);

    /**
     * 批量删除考勤
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckWorkByIds(String ids);

    /**
     * 删除考勤信息
     * 
     * @param id 考勤ID
     * @return 结果
     */
    public int deleteCheckWorkById(Long id);

    int getDays(String user_id, String firstDay, String lastDay, String s);

    List<Map<String,Object>> getUserRole(Long userId);
}

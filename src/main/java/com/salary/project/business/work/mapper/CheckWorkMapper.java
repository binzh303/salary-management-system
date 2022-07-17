package com.salary.project.business.work.mapper;

import com.salary.project.business.work.domain.CheckWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考勤Mapper接口
 * 
 * @author au
 * @date 2021-07-07
 */
public interface CheckWorkMapper 
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
     * 删除考勤
     * 
     * @param id 考勤ID
     * @return 结果
     */
    public int deleteCheckWorkById(Long id);

    /**
     * 批量删除考勤
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckWorkByIds(String[] ids);

    int getDays(@Param("userId") String userId,@Param("firstDay") String firstDay,@Param("lastDay") String lastDay,@Param("state") String state);

}

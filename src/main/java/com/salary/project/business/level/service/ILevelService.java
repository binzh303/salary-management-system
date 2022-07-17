package com.salary.project.business.level.service;

import com.salary.project.business.level.domain.Level;

import java.util.List;

/**
 * 请假Service接口
 * 
 * @author au
 * @date 2021-07-08
 */
public interface ILevelService 
{
    /**
     * 查询请假
     * 
     * @param id 请假ID
     * @return 请假
     */
    public Level selectLevelById(Long id);

    /**
     * 查询请假列表
     * 
     * @param level 请假
     * @return 请假集合
     */
    public List<Level> selectLevelList(Level level);

    /**
     * 新增请假
     * 
     * @param level 请假
     * @return 结果
     */
    public int insertLevel(Level level);

    /**
     * 修改请假
     * 
     * @param level 请假
     * @return 结果
     */
    public int updateLevel(Level level);

    /**
     * 批量删除请假
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLevelByIds(String ids);

    /**
     * 删除请假信息
     * 
     * @param id 请假ID
     * @return 结果
     */
    public int deleteLevelById(Long id);
}

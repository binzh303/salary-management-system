package com.salary.project.business.level.service.impl;

import com.salary.common.utils.DateUtils;
import com.salary.common.utils.text.Convert;
import com.salary.project.business.level.domain.Level;
import com.salary.project.business.level.mapper.LevelMapper;
import com.salary.project.business.level.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请假Service业务层处理
 * 
 * @author au
 * @date 2021-07-08
 */
@Service
public class LevelServiceImpl implements ILevelService
{
    @Autowired
    private LevelMapper levelMapper;

    /**
     * 查询请假
     * 
     * @param id 请假ID
     * @return 请假
     */
    @Override
    public Level selectLevelById(Long id)
    {
        return levelMapper.selectLevelById(id);
    }

    /**
     * 查询请假列表
     * 
     * @param level 请假
     * @return 请假
     */
    @Override
    public List<Level> selectLevelList(Level level)
    {
        return levelMapper.selectLevelList(level);
    }

    /**
     * 新增请假
     * 
     * @param level 请假
     * @return 结果
     */
    @Override
    public int insertLevel(Level level)
    {
        level.setCreateTime(DateUtils.getNowDate());
        return levelMapper.insertLevel(level);
    }

    /**
     * 修改请假
     * 
     * @param level 请假
     * @return 结果
     */
    @Override
    public int updateLevel(Level level)
    {
        return levelMapper.updateLevel(level);
    }

    /**
     * 删除请假对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLevelByIds(String ids)
    {
        return levelMapper.deleteLevelByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请假信息
     * 
     * @param id 请假ID
     * @return 结果
     */
    @Override
    public int deleteLevelById(Long id)
    {
        return levelMapper.deleteLevelById(id);
    }
}

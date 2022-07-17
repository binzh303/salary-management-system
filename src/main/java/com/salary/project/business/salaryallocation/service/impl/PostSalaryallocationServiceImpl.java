package com.salary.project.business.salaryallocation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salary.project.business.salaryallocation.mapper.PostSalaryallocationMapper;
import com.salary.project.business.salaryallocation.domain.PostSalaryallocation;
import com.salary.project.business.salaryallocation.service.IPostSalaryallocationService;
import com.salary.common.utils.text.Convert;

/**
 * 岗位-薪资配置关联Service业务层处理
 * 
 * @author au
 * @date 2021-07-10
 */
@Service
public class PostSalaryallocationServiceImpl implements IPostSalaryallocationService 
{
    @Autowired
    private PostSalaryallocationMapper postSalaryallocationMapper;

    /**
     * 查询岗位-薪资配置关联
     * 
     * @param id 岗位-薪资配置关联ID
     * @return 岗位-薪资配置关联
     */
    @Override
    public PostSalaryallocation selectPostSalaryallocationById(Long id)
    {
        return postSalaryallocationMapper.selectPostSalaryallocationById(id);
    }

    /**
     * 查询岗位-薪资配置关联列表
     * 
     * @param postSalaryallocation 岗位-薪资配置关联
     * @return 岗位-薪资配置关联
     */
    @Override
    public List<PostSalaryallocation> selectPostSalaryallocationList(PostSalaryallocation postSalaryallocation)
    {
        return postSalaryallocationMapper.selectPostSalaryallocationList(postSalaryallocation);
    }

    /**
     * 新增岗位-薪资配置关联
     * 
     * @param postSalaryallocation 岗位-薪资配置关联
     * @return 结果
     */
    @Override
    public int insertPostSalaryallocation(PostSalaryallocation postSalaryallocation)
    {
        return postSalaryallocationMapper.insertPostSalaryallocation(postSalaryallocation);
    }

    /**
     * 修改岗位-薪资配置关联
     * 
     * @param postSalaryallocation 岗位-薪资配置关联
     * @return 结果
     */
    @Override
    public int updatePostSalaryallocation(PostSalaryallocation postSalaryallocation)
    {
        return postSalaryallocationMapper.updatePostSalaryallocation(postSalaryallocation);
    }

    /**
     * 删除岗位-薪资配置关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePostSalaryallocationByIds(String ids)
    {
        return postSalaryallocationMapper.deletePostSalaryallocationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除岗位-薪资配置关联信息
     * 
     * @param id 岗位-薪资配置关联ID
     * @return 结果
     */
    @Override
    public int deletePostSalaryallocationById(Long id)
    {
        return postSalaryallocationMapper.deletePostSalaryallocationById(id);
    }

    @Override
    public void deleteBySalaryId(String id) {
        postSalaryallocationMapper.deleteBySalaryId(id);
    }
}

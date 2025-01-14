package com.betta.other.service.impl;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.utils.DateUtils;
import com.betta.other.domain.TaskInfo;
import com.betta.other.mapper.TaskInfoMapper;
import com.betta.other.service.ITaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务Service业务层处理
 * 
 * @author chenlt
 * @date 2025-01-10
 */
@Service
public class TaskInfoServiceImpl implements ITaskInfoService
{
    @Autowired
    private TaskInfoMapper taskInfoMapper;

    /**
     * 查询任务
     * 
     * @param id 任务主键
     * @return 任务
     */
    @Override
    public TaskInfo selectTaskInfoById(Long id)
    {
        return taskInfoMapper.selectTaskInfoById(id);
    }

    /**
     * 查询任务列表
     * 
     * @param taskInfo 任务
     * @return 任务
     */
    @Override
    @CreateByScope("")
    public List<TaskInfo> selectTaskInfoList(TaskInfo taskInfo)
    {
        return taskInfoMapper.selectTaskInfoList(taskInfo);
    }

    /**
     * 新增任务
     * 
     * @param taskInfo 任务
     * @return 结果
     */
    @Override
    public int insertTaskInfo(TaskInfo taskInfo)
    {
        taskInfo.setCreateTime(DateUtils.getNowDate());
        return taskInfoMapper.insertTaskInfo(taskInfo);
    }

    /**
     * 修改任务
     * 
     * @param taskInfo 任务
     * @return 结果
     */
    @Override
    public int updateTaskInfo(TaskInfo taskInfo)
    {
        taskInfo.setUpdateTime(DateUtils.getNowDate());
        return taskInfoMapper.updateTaskInfo(taskInfo);
    }

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskInfoByIds(Long[] ids)
    {
        return taskInfoMapper.deleteTaskInfoByIds(ids);
    }

    /**
     * 删除任务信息
     * 
     * @param id 任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskInfoById(Long id)
    {
        return taskInfoMapper.deleteTaskInfoById(id);
    }
}

package com.betta.other.mapper;

import com.betta.other.domain.TaskInfo;

import java.util.List;

/**
 * 任务Mapper接口
 * 
 * @author chenlt
 * @date 2025-01-10
 */
public interface TaskInfoMapper 
{
    /**
     * 查询任务
     * 
     * @param id 任务主键
     * @return 任务
     */
    public TaskInfo selectTaskInfoById(Long id);

    /**
     * 查询任务列表
     * 
     * @param taskInfo 任务
     * @return 任务集合
     */
    public List<TaskInfo> selectTaskInfoList(TaskInfo taskInfo);

    /**
     * 新增任务
     * 
     * @param taskInfo 任务
     * @return 结果
     */
    public int insertTaskInfo(TaskInfo taskInfo);

    /**
     * 修改任务
     * 
     * @param taskInfo 任务
     * @return 结果
     */
    public int updateTaskInfo(TaskInfo taskInfo);

    /**
     * 删除任务
     * 
     * @param id 任务主键
     * @return 结果
     */
    public int deleteTaskInfoById(Long id);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskInfoByIds(Long[] ids);
}

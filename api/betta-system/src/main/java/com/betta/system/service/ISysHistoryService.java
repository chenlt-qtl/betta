package com.betta.system.service;

import java.util.List;
import com.betta.system.domain.SysHistory;

/**
 * 系统历史Service接口
 * 
 * @author chenlt
 * @date 2024-07-13
 */
public interface ISysHistoryService 
{
    /**
     * 查询系统历史
     * 
     * @param id 系统历史主键
     * @return 系统历史
     */
    public SysHistory selectSysHistoryById(Long id);

    /**
     * 查询系统历史列表
     * 
     * @param sysHistory 系统历史
     * @return 系统历史集合
     */
    public List<SysHistory> selectSysHistoryList(SysHistory sysHistory);

    /**
     * 新增系统历史
     * 
     * @param sysHistory 系统历史
     * @return 结果
     */
    public int insertSysHistory(SysHistory sysHistory);

    /**
     * 修改系统历史
     * 
     * @param sysHistory 系统历史
     * @return 结果
     */
    public int updateSysHistory(SysHistory sysHistory);

    /**
     * 批量删除系统历史
     * 
     * @param ids 需要删除的系统历史主键集合
     * @return 结果
     */
    public int deleteSysHistoryByIds(Long[] ids);

    /**
     * 删除系统历史信息
     * 
     * @param id 系统历史主键
     * @return 结果
     */
    public int deleteSysHistoryById(Long id);
}

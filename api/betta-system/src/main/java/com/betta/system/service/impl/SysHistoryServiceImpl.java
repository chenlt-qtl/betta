package com.betta.system.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.system.mapper.SysHistoryMapper;
import com.betta.system.domain.SysHistory;
import com.betta.system.service.ISysHistoryService;

/**
 * 系统历史Service业务层处理
 * 
 * @author chenlt
 * @date 2024-07-13
 */
@Service
public class SysHistoryServiceImpl implements ISysHistoryService 
{
    @Autowired
    private SysHistoryMapper sysHistoryMapper;

    /**
     * 查询系统历史
     * 
     * @param id 系统历史主键
     * @return 系统历史
     */
    @Override
    public SysHistory selectSysHistoryById(Long id)
    {
        return sysHistoryMapper.selectSysHistoryById(id);
    }

    /**
     * 查询系统历史列表
     * 
     * @param sysHistory 系统历史
     * @return 系统历史
     */
    @Override
    public List<SysHistory> selectSysHistoryList(SysHistory sysHistory)
    {
        return sysHistoryMapper.selectSysHistoryList(sysHistory);
    }

    /**
     * 新增系统历史
     * 
     * @param sysHistory 系统历史
     * @return 结果
     */
    @Override
    public int insertSysHistory(SysHistory sysHistory)
    {
        sysHistory.setCreateTime(DateUtils.getNowDate());
        return sysHistoryMapper.insertSysHistory(sysHistory);
    }

    /**
     * 修改系统历史
     * 
     * @param sysHistory 系统历史
     * @return 结果
     */
    @Override
    public int updateSysHistory(SysHistory sysHistory)
    {
        sysHistory.setUpdateTime(DateUtils.getNowDate());
        return sysHistoryMapper.updateSysHistory(sysHistory);
    }

    /**
     * 批量删除系统历史
     * 
     * @param ids 需要删除的系统历史主键
     * @return 结果
     */
    @Override
    public int deleteSysHistoryByIds(Long[] ids)
    {
        return sysHistoryMapper.deleteSysHistoryByIds(ids);
    }

    /**
     * 删除系统历史信息
     * 
     * @param id 系统历史主键
     * @return 结果
     */
    @Override
    public int deleteSysHistoryById(Long id)
    {
        return sysHistoryMapper.deleteSysHistoryById(id);
    }
}

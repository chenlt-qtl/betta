package com.betta.es.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.es.mapper.EsHistoryMapper;
import com.betta.es.domain.EsHistory;
import com.betta.es.service.IEsHistoryService;

/**
 * ES历史Service业务层处理
 * 
 * @author chenlt
 * @date 2024-06-16
 */
@Service
public class EsHistoryServiceImpl implements IEsHistoryService 
{
    @Autowired
    private EsHistoryMapper esHistoryMapper;

    /**
     * 查询ES历史
     * 
     * @param id ES历史主键
     * @return ES历史
     */
    @Override
    public EsHistory selectEsHistoryById(Long id)
    {
        return esHistoryMapper.selectEsHistoryById(id);
    }

    /**
     * 查询ES历史列表
     * 
     * @param esHistory ES历史
     * @return ES历史
     */
    @Override
    public List<EsHistory> selectEsHistoryList(EsHistory esHistory)
    {
        return esHistoryMapper.selectEsHistoryList(esHistory);
    }

    /**
     * 新增ES历史
     * 
     * @param esHistory ES历史
     * @return 结果
     */
    @Override
    public int insertEsHistory(EsHistory esHistory)
    {
        return esHistoryMapper.insertEsHistory(esHistory);
    }

    /**
     * 修改ES历史
     * 
     * @param esHistory ES历史
     * @return 结果
     */
    @Override
    public int updateEsHistory(EsHistory esHistory)
    {
        esHistory.setUpdateTime(DateUtils.getNowDate());
        return esHistoryMapper.updateEsHistory(esHistory);
    }

    /**
     * 批量删除ES历史
     * 
     * @param ids 需要删除的ES历史主键
     * @return 结果
     */
    @Override
    public int deleteEsHistoryByIds(Long[] ids)
    {
        return esHistoryMapper.deleteEsHistoryByIds(ids);
    }

    /**
     * 删除ES历史信息
     * 
     * @param id ES历史主键
     * @return 结果
     */
    @Override
    public int deleteEsHistoryById(Long id)
    {
        return esHistoryMapper.deleteEsHistoryById(id);
    }
}

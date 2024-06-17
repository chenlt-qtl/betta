package com.betta.es.service;

import java.util.List;
import com.betta.es.domain.EsHistory;

/**
 * ES历史Service接口
 * 
 * @author chenlt
 * @date 2024-06-17
 */
public interface IEsHistoryService 
{
    /**
     * 查询ES历史
     * 
     * @param id ES历史主键
     * @return ES历史
     */
    public EsHistory selectEsHistoryById(Long id);

    /**
     * 查询ES历史列表
     * 
     * @param esHistory ES历史
     * @return ES历史集合
     */
    public List<EsHistory> selectEsHistoryList(EsHistory esHistory);

    /**
     * 新增ES历史
     * 
     * @param esHistory ES历史
     * @return 结果
     */
    public int insertEsHistory(EsHistory esHistory);

    /**
     * 修改ES历史
     * 
     * @param esHistory ES历史
     * @return 结果
     */
    public int updateEsHistory(EsHistory esHistory);

    /**
     * 批量删除ES历史
     * 
     * @param ids 需要删除的ES历史主键集合
     * @return 结果
     */
    public int deleteEsHistoryByIds(Long[] ids);

    /**
     * 删除ES历史信息
     * 
     * @param id ES历史主键
     * @return 结果
     */
    public int deleteEsHistoryById(Long id);
}

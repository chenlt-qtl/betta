package com.betta.note.mapper;

import java.util.List;
import com.betta.note.domain.History;

/**
 * 历史Mapper接口
 * 
 * @author chenlt
 * @date 2024-06-13
 */
public interface HistoryMapper
{
    /**
     * 查询历史
     * 
     * @param id 历史主键
     * @return 历史
     */
    public History selecthistoryById(Long id);

    /**
     * 查询历史列表
     * 
     * @param history 历史
     * @return 历史集合
     */
    public List<History> selecthistoryList(History history);

    /**
     * 新增历史
     * 
     * @param history 历史
     * @return 结果
     */
    public int inserthistory(History history);

    /**
     * 修改历史
     * 
     * @param history 历史
     * @return 结果
     */
    public int updatehistory(History history);

    /**
     * 删除历史
     * 
     * @param id 历史主键
     * @return 结果
     */
    public int deletehistoryById(Long id);

    /**
     * 批量删除历史
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletehistoryByIds(Long[] ids);
}

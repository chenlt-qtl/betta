package com.betta.note.service.impl;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.note.mapper.HistoryMapper;
import com.betta.note.domain.History;
import com.betta.note.service.IHistoryService;

/**
 * 历史Service业务层处理
 *
 * @author chenlt
 * @date 2024-06-13
 */
@Service
public class HistoryServiceImpl implements IHistoryService {
    @Autowired
    private HistoryMapper historyMapper;

    /**
     * 查询历史
     *
     * @param id 历史主键
     * @return 历史
     */
    @Override
    public History selecthistoryById(Long id) {
        return historyMapper.selecthistoryById(id);
    }

    /**
     * 查询历史列表
     *
     * @param history 历史
     * @return 历史
     */
    @Override
    public List<History> selecthistoryList(History history,String type) {
        history.setParams(Map.of("type",type));
        return historyMapper.selecthistoryList(history);
    }

    /**
     * 新增历史
     *
     * @param history 历史
     * @return 结果
     */
    @Override
    public int inserthistory(History history) {
        return historyMapper.inserthistory(history);
    }

    /**
     * 修改历史
     *
     * @param history 历史
     * @return 结果
     */
    @Override
    public int updatehistory(History history) {
        return historyMapper.updatehistory(history);
    }

    /**
     * 批量删除历史
     *
     * @param ids 需要删除的历史主键
     * @return 结果
     */
    @Override
    public int deletehistoryByIds(Long[] ids) {
        return historyMapper.deletehistoryByIds(ids);
    }

    /**
     * 删除历史信息
     *
     * @param id 历史主键
     * @return 结果
     */
    @Override
    public int deletehistoryById(Long id) {
        return historyMapper.deletehistoryById(id);
    }
}

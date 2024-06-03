package com.betta.eng.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.EngWordMapper;
import com.betta.eng.domain.EngWord;
import com.betta.eng.service.IEngWordService;

/**
 * 单词Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Service
public class EngWordServiceImpl implements IEngWordService 
{
    @Autowired
    private EngWordMapper engWordMapper;

    /**
     * 查询单词
     * 
     * @param id 单词主键
     * @return 单词
     */
    @Override
    public EngWord selectEngWordById(Long id)
    {
        return engWordMapper.selectEngWordById(id);
    }

    /**
     * 查询单词列表
     * 
     * @param engWord 单词
     * @return 单词
     */
    @Override
    public List<EngWord> selectEngWordList(EngWord engWord)
    {
        return engWordMapper.selectEngWordList(engWord);
    }

    @Override
    public List<EngWord> selectWordListByArticle(Long articleId) {
        return engWordMapper.selectWordListByArticleId(articleId);
    }

    /**
     * 新增单词
     * 
     * @param engWord 单词
     * @return 结果
     */
    @Override
    public int insertEngWord(EngWord engWord)
    {
        engWord.setCreateTime(DateUtils.getNowDate());
        return engWordMapper.insertEngWord(engWord);
    }

    /**
     * 修改单词
     * 
     * @param engWord 单词
     * @return 结果
     */
    @Override
    public int updateEngWord(EngWord engWord)
    {
        engWord.setUpdateTime(DateUtils.getNowDate());
        return engWordMapper.updateEngWord(engWord);
    }

    /**
     * 批量删除单词
     * 
     * @param ids 需要删除的单词主键
     * @return 结果
     */
    @Override
    public int deleteEngWordByIds(Long[] ids)
    {
        return engWordMapper.deleteEngWordByIds(ids);
    }

    /**
     * 删除单词信息
     * 
     * @param id 单词主键
     * @return 结果
     */
    @Override
    public int deleteEngWordById(Long id)
    {
        return engWordMapper.deleteEngWordById(id);
    }
}

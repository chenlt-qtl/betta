package com.betta.eng.service;

import java.util.List;
import com.betta.eng.domain.EngWord;

/**
 * 单词Service接口
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
public interface IEngWordService 
{
    /**
     * 查询单词
     * 
     * @param id 单词主键
     * @return 单词
     */
    public EngWord selectEngWordById(Long id);

    /**
     * 查询单词列表
     * 
     * @param engWord 单词
     * @return 单词集合
     */
    public List<EngWord> selectEngWordList(EngWord engWord);

    /**
     * 根据文章查询
     *
     * @return 单词集合
     */
    public List<EngWord> selectWordListByArticle(Long articleId);

    /**
     * 新增单词
     * 
     * @param engWord 单词
     * @return 结果
     */
    public int insertEngWord(EngWord engWord);

    EngWord getWord(String wordName);

    /**
     * 增加文章对应的生词
     *
     * @param articleId
     */
    public void updateByArticle(List<String> wordNames, Long articleId);

    /**
     * 修改单词
     * 
     * @param engWord 单词
     * @return 结果
     */
    public int updateEngWord(EngWord engWord);

    /**
     * 删除单词信息
     *
     * @param id 单词主键
     */
    public void deleteEngWordById(Long id);

    List<EngWord> selectNewList(EngWord engWord);

    void addArticleWord(Long articleId, String wordName);
}

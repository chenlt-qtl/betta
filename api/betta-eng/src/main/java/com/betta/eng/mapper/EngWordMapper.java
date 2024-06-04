package com.betta.eng.mapper;

import java.util.List;
import com.betta.eng.domain.EngWord;

/**
 * 单词Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
public interface EngWordMapper 
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

    public List<EngWord> selectEngWordByWordName(String wordName);

    /**
     * 根据article查询对应的单词
     * @param articleId
     * @return
     */
    public List<EngWord> selectWordListByArticleId(Long articleId);

    /**
     * 新增单词
     * 
     * @param engWord 单词
     * @return 结果
     */
    public int insertEngWord(EngWord engWord);

    /**
     * 修改单词
     * 
     * @param engWord 单词
     * @return 结果
     */
    public int updateEngWord(EngWord engWord);

    /**
     * 删除单词
     * 
     * @param id 单词主键
     * @return 结果
     */
    public int deleteEngWordById(Long id);

    /**
     * 批量删除单词
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEngWordByIds(Long[] ids);

    /**
     * 查找有关联的单词
     * @param engWord
     * @return
     */
    List<EngWord> selectRelList(EngWord engWord);
}

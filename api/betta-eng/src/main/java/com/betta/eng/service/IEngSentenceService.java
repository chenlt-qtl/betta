package com.betta.eng.service;

import java.util.List;

import com.betta.eng.domain.EngSentence;

/**
 * 文章句子Service接口
 *
 * @author ruoyi
 * @date 2024-06-02
 */
public interface IEngSentenceService {
    /**
     * 查询文章句子
     *
     * @param id 文章句子主键
     * @return 文章句子
     */
    public EngSentence selectEngSentenceById(Long id);

    /**
     * 查询文章句子列表
     *
     * @param engSentence 文章句子
     * @return 文章句子集合
     */
    public List<EngSentence> selectEngSentenceList(EngSentence engSentence);

    /**
     * 新增文章句子
     *
     * @param engSentence 文章句子
     * @return 结果
     */
    public int insertEngSentence(EngSentence engSentence);

    /**
     * 修改文章句子
     *
     * @param engSentence 文章句子
     * @return 结果
     */
    public int updateEngSentence(EngSentence engSentence);

    /**
     * 批量删除文章句子
     *
     * @param ids 需要删除的文章句子主键集合
     * @return 结果
     */
    public int deleteEngSentenceByIds(Long[] ids);

    /**
     * 删除文章句子信息
     *
     * @param id 文章句子主键
     * @return 结果
     */
    public int deleteEngSentenceById(Long id);

    int deleteByArticle(Long articleId);

    List<EngSentence> selectPlayList(EngSentence engSentence, boolean inPlayList, String username);
}

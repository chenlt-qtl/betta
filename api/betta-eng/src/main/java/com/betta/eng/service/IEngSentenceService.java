package com.betta.eng.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.betta.eng.domain.EngSentence;
import com.betta.eng.domain.EngWord;
import com.betta.eng.domain.dojo.BatchAddSentences;
import com.betta.eng.domain.vo.SentenceVo;

/**
 * 文章句子Service接口
 *
 * @author ruoyi
 * @date 2024-06-02
 */
public interface IEngSentenceService extends IService<EngSentence> {


    /**
     * 查询文章句子列表
     *
     * @param engSentence 文章句子
     * @return 文章句子集合
     */
    public List<EngSentence> selectEngSentenceList(EngSentence engSentence);


    int deleteByArticle(Long articleId);

    List<EngSentence> selectPlayList(EngSentence engSentence, boolean inPlayList, String username);

    /**
     * 只取前10句 避免内存溢出
     * @param wordName
     * @return
     */
    List<SentenceVo> selectByWordTop10(EngWord word);

    /**
     * 批量增加句子
     *
     * @param batchAddSentences
     * @return
     */
    boolean insertEngSentenceBatch(BatchAddSentences batchAddSentences);
}

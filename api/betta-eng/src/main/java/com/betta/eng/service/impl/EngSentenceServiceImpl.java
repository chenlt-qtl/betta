package com.betta.eng.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
import com.betta.common.utils.StringUtils;
import com.betta.eng.domain.PlayList;
import com.betta.eng.service.IEngSentenceService;
import com.betta.eng.service.IPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.EngSentenceMapper;
import com.betta.eng.domain.EngSentence;

/**
 * 文章句子Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-02
 */
@Service
public class EngSentenceServiceImpl implements IEngSentenceService {
    @Autowired
    private EngSentenceMapper engSentenceMapper;

    @Autowired
    private IPlayListService playListService;

    /**
     * 查询文章句子
     *
     * @param id 文章句子主键
     * @return 文章句子
     */
    @Override
    public EngSentence selectEngSentenceById(Long id) {
        return engSentenceMapper.selectEngSentenceById(id);
    }

    /**
     * 查询文章句子列表
     *
     * @param engSentence 文章句子
     * @return 文章句子
     */
    @Override
    @CreateByScope("")
    public List<EngSentence> selectEngSentenceList(EngSentence engSentence) {
        return engSentenceMapper.selectEngSentenceList(engSentence);
    }

    /**
     * 新增文章句子
     *
     * @param engSentence 文章句子
     * @return 结果
     */
    @Override
    public int insertEngSentence(EngSentence engSentence) {
        engSentence.setCreateTime(DateUtils.getNowDate());
        return engSentenceMapper.insertEngSentence(engSentence);
    }

    /**
     * 修改文章句子
     *
     * @param engSentence 文章句子
     * @return 结果
     */
    @Override
    public int updateEngSentence(EngSentence engSentence) {
        engSentence.setUpdateTime(DateUtils.getNowDate());
        return engSentenceMapper.updateEngSentence(engSentence);
    }

    /**
     * 批量删除文章句子
     *
     * @param ids 需要删除的文章句子主键
     * @return 结果
     */
    @Override
    public int deleteEngSentenceByIds(Long[] ids) {
        return engSentenceMapper.deleteEngSentenceByIds(ids);
    }

    /**
     * 删除文章句子信息
     *
     * @param id 文章句子主键
     * @return 结果
     */
    @Override
    public int deleteEngSentenceById(Long id) {
        return engSentenceMapper.deleteEngSentenceById(id);
    }

    /**
     * 删除article对应的句子
     *
     * @param articleId
     * @return
     */
    @Override
    public int deleteByArticle(Long articleId) {
        return engSentenceMapper.deleteByArticleId(articleId);
    }

    @Override
    public List<EngSentence> selectPlayList(EngSentence engSentence, boolean inPlayList, String username) {
        PlayList playList = new PlayList();
        playList.setUserName(username);
        List<PlayList> playLists = playListService.selectPlayListList(playList);
        String sentenceIds = "";
        if (!playLists.isEmpty()) {
            sentenceIds = playLists.get(0).getSentenceIds();
        }
        if (StringUtils.hasText(sentenceIds)) {
            engSentence.getParams().put(inPlayList ? "existIds" : "notExistIds", sentenceIds);
        } else if (inPlayList) {
            return new ArrayList<>();
        }
        engSentence.setCreateBy(username);
        return engSentenceMapper.selectPlayList(engSentence);
    }

    @Override
    public List<EngSentence> selectByWordTop10(String wordName) {
        return engSentenceMapper.selectByWordTop10(wordName,SecurityUtils.getUsername());
    }
}

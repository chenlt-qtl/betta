package com.betta.eng.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betta.common.utils.SecurityUtils;
import com.betta.common.utils.StringUtils;
import com.betta.eng.domain.EngSentence;
import com.betta.eng.domain.PlayList;
import com.betta.eng.domain.dojo.BatchAddSentences;
import com.betta.eng.domain.vo.SentenceVo;
import com.betta.eng.mapper.EngSentenceMapper;
import com.betta.eng.service.IEngSentenceService;
import com.betta.eng.service.IPlayListService;
import com.betta.eng.utils.EngUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文章句子Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-02
 */
@Service
public class EngSentenceServiceImpl extends ServiceImpl<EngSentenceMapper, EngSentence>  implements IEngSentenceService{
    @Autowired
    private EngSentenceMapper engSentenceMapper;

    @Autowired
    private IPlayListService playListService;


    /**
     * 查询文章句子列表
     *
     * @param sentence 文章句子
     * @return 文章句子
     */
    @Override
    public List<EngSentence> selectEngSentenceList(EngSentence sentence) {
        LambdaQueryWrapper<EngSentence> wrapper = new LambdaQueryWrapper<>();
        //过滤文章ID
        wrapper.eq(sentence.getArticleId()!=null,EngSentence::getArticleId,sentence.getArticleId());
        //过滤内容
        wrapper.like(StrUtil.isNotBlank(sentence.getContent()),EngSentence::getContent,sentence.getContent());
        //过滤解释
        wrapper.like(StrUtil.isNotBlank(sentence.getAcceptation()),EngSentence::getAcceptation,sentence.getAcceptation());
        //过滤创建者
        wrapper.like(EngSentence::getCreateBy,SecurityUtils.getUsername());

        return engSentenceMapper.selectList(wrapper);
    }

    /**
     * 删除article对应的句子
     *
     * @param articleId
     * @return
     */
    @Override
    public int deleteByArticle(Long articleId) {
        LambdaQueryWrapper<EngSentence> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EngSentence::getArticleId,articleId);
        return engSentenceMapper.delete(wrapper);
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

        if(StrUtil.isBlank(sentenceIds) && inPlayList){
            return new ArrayList<>();
        }

        //准备QueryWrapper
        QueryWrapper<EngSentence> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(sentenceIds)) {
            if (inPlayList) {
                queryWrapper.in("eng_article_group.id", sentenceIds);
            } else {
                queryWrapper.notIn("eng_article_group.id", sentenceIds);
            }
        }
        queryWrapper.eq("create_by",username);
        queryWrapper.isNotNull("eng_sentence.mp3");
        queryWrapper.like(StrUtil.isNotBlank(engSentence.getContent()),"content",engSentence.getContent());
        queryWrapper.orderByAsc("group_name","article_name","idx");

        return engSentenceMapper.selectPlayList(queryWrapper);
    }

    @Override
    public List<SentenceVo> selectByWordTop10(String wordName) {
        QueryWrapper<EngSentence> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(wordName),"content",wordName);
        wrapper.like("eng_sentence.create_by",SecurityUtils.getUsername());
        wrapper.orderByAsc("idx");
        wrapper.last("limit 10");

        return engSentenceMapper.selectByWordTop10(wrapper);
    }

    /**
     * 批量保存文章句子
     * @param batchAddSentences
     * @return
     */
    @Override
    @Transactional
    public boolean insertEngSentenceBatch(BatchAddSentences batchAddSentences) {
        //1.获取目前数据库里的总条数 idx需要用到
        QueryWrapper<EngSentence> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as total");
        wrapper.eq("article_id",batchAddSentences.getArticleId());
        Map<String, Object> map = getMap(wrapper);
        long total = (long) map.get("total");

        //2.组装句子对象
        List<String> sentenceStrs = Arrays.stream(batchAddSentences.getSentenceStr().split("\n")).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        List<EngSentence> sentences = new ArrayList<>();
        for (int i = 0; i < sentenceStrs.size(); i++) {
            EngSentence engSentence = new EngSentence();
            engSentence.setArticleId(batchAddSentences.getArticleId());
            engSentence.setContent(sentenceStrs.get(i));
            EngUtils.genMp3Time(engSentence);
            engSentence.setIdx(total + 1 + i );
            sentences.add(engSentence);
        }

        //3.批量增加
        return this.saveBatch(sentences);
    }

}

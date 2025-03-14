package com.betta.eng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betta.common.annotation.CreateByScope;
import com.betta.common.utils.SecurityUtils;
import com.betta.eng.domain.EngUserScore;
import com.betta.eng.domain.vo.EngUserScoreVo;
import com.betta.eng.mapper.EngUserScoreMapper;
import com.betta.eng.service.IEngUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户成绩Service业务层处理
 *
 * @author chenlt
 * @date 2024-07-13
 */
@Service
public class EngUserScoreServiceImpl extends ServiceImpl<EngUserScoreMapper, EngUserScore> implements IEngUserScoreService  {

    @Autowired
    private EngUserScoreMapper engUserScoreMapper;



    @Override
    public void batchUpdate(List<EngUserScore> engUserScoreList) {

        List<String> wordNames = new ArrayList<>(engUserScoreList.size());
        //取出所有word name
        for (EngUserScore engUserScore : engUserScoreList) {
            wordNames.add(engUserScore.getWordName());
        }
        //去数据库里查
        LambdaQueryWrapper<EngUserScore> wrapper = new LambdaQueryWrapper<>();

        //只查询当前用户的
        wrapper.eq(EngUserScore::getUser,SecurityUtils.getUsername());
        wrapper.in(EngUserScore::getWordName,wordNames);
        List<EngUserScore> engUserScores = list(wrapper);

        //迭代查询结果放到MAP中
        Map<String,EngUserScore> dbMap = new HashMap<>(engUserScores.size());
        for (EngUserScore engUserScore : engUserScores) {
            dbMap.put(engUserScore.getWordName(),engUserScore);
        }

        engUserScoreList.forEach(score -> {
            EngUserScore dbScore = dbMap.get(score.getWordName());
            if (!Objects.isNull(dbScore)) {
                //更新分数
                dbScore.setFamiliarity(score.getFamiliarity()+dbScore.getFamiliarity());
                updateById(dbScore);
            } else {
                //新增
                EngUserScore engUserScore = new EngUserScore();
                engUserScore.setUser(SecurityUtils.getUsername());
                engUserScore.setFamiliarity(score.getFamiliarity());
                engUserScore.setWordName(score.getWordName());
                this.save(engUserScore);
            }
        });
    }

    @Override
    @CreateByScope("c")
    public List<EngUserScore> selectEngUserScoreList(EngUserScore engUserScore) {
        return engUserScoreMapper.selectEngUserScoreList(engUserScore);
    }

    /**
     * 查询用户成绩 包括单词明细信息
     * @param engUserScore
     * @return
     */
    @Override
    @CreateByScope("c")
    public List<EngUserScoreVo> selectEngUserScoreVoList(EngUserScore engUserScore) {
        return engUserScoreMapper.selectEngUserScoreVo(engUserScore);
    }

    /**
     * 更新分数
     * @param wordName
     * @param i  增减的分数
     */
    @Override
    public void updateEngUserScore(String wordName, int i) {
        String username = SecurityUtils.getUsername();
        EngUserScore engUserScore = engUserScoreMapper.getByWordName(wordName,username);
        if(!Objects.isNull(engUserScore)){
            //如果数据库里有数据 更新熟悉度
            engUserScore.setFamiliarity(engUserScore.getFamiliarity()+i);
            updateById(engUserScore);
        }else {
            //增加数据
            engUserScore = new EngUserScore();
            engUserScore.setFamiliarity(i);
            engUserScore.setWordName(wordName);
            engUserScore.setUser(username);
            save(engUserScore);
        }
    }
}

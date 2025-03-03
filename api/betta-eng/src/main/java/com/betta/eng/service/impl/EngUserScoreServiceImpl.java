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

import java.util.List;
import java.util.Objects;

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
        LambdaQueryWrapper<EngUserScore> wrapper = new LambdaQueryWrapper<>();

        //只查询当前用户的
        wrapper.eq(EngUserScore::getUser,SecurityUtils.getUsername());

        engUserScoreList.forEach(score -> {
            wrapper.eq(EngUserScore::getWordName,score.getWordName());
            List<EngUserScore> engUserScores = list(wrapper);
            if (!engUserScores.isEmpty()) {
                //更新分数
                EngUserScore engUserScore = engUserScores.get(0);
                engUserScore.setFamiliarity(score.getFamiliarity());
                updateById(engUserScore);
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

package com.betta.eng.service.impl;

import java.util.List;
import java.util.Objects;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
import com.betta.eng.domain.vo.EngUserScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.EngUserScoreMapper;
import com.betta.eng.domain.EngUserScore;
import com.betta.eng.service.IEngUserScoreService;

/**
 * 用户成绩Service业务层处理
 *
 * @author chenlt
 * @date 2024-07-13
 */
@Service
public class EngUserScoreServiceImpl implements IEngUserScoreService {
    @Autowired
    private EngUserScoreMapper engUserScoreMapper;

    /**
     * 新增用户成绩
     *
     * @param engUserScore 用户成绩
     * @return 结果
     */
    @Override
    public int insertEngUserScore(EngUserScore engUserScore) {
        engUserScore.setCreateTime(DateUtils.getNowDate());
        return engUserScoreMapper.insertEngUserScore(engUserScore);
    }

    /**
     * 修改用户成绩
     *
     * @param engUserScore 用户成绩
     * @return 结果
     */
    @Override
    public int updateEngUserScore(EngUserScore engUserScore) {
        return engUserScoreMapper.updateEngUserScore(engUserScore);
    }

    /**
     * 批量删除用户成绩
     *
     * @param ids 需要删除的用户成绩主键
     * @return 结果
     */
    @Override
    public int deleteEngUserScoreByIds(Long[] ids) {
        return engUserScoreMapper.deleteEngUserScoreByIds(ids);
    }

    /**
     * 删除用户成绩信息
     *
     * @param id 用户成绩主键
     * @return 结果
     */
    @Override
    public int deleteEngUserScoreById(Long id) {
        return engUserScoreMapper.deleteEngUserScoreById(id);
    }

    @Override
    public void batchUpdate(List<EngUserScore> engUserScoreList) {
        EngUserScore search = new EngUserScore();
        search.setUser(SecurityUtils.getUsername());
        engUserScoreList.forEach(score -> {
            search.setWordName(score.getWordName());
            List<EngUserScore> engUserScores = engUserScoreMapper.selectEngUserScoreList(search);
            if (!engUserScores.isEmpty()) {
                //更新分数
                EngUserScore engUserScore = engUserScores.get(0);
                engUserScore.setFamiliarity(score.getFamiliarity());
                updateEngUserScore(engUserScore);
            } else {
                //新增
                EngUserScore engUserScore = new EngUserScore();
                engUserScore.setUser(SecurityUtils.getUsername());
                engUserScore.setFamiliarity(score.getFamiliarity());
                engUserScore.setWordName(score.getWordName());
                insertEngUserScore(engUserScore);
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
            updateEngUserScore(engUserScore);
        }else {
            //增加数据
            engUserScore = new EngUserScore();
            engUserScore.setFamiliarity(i);
            engUserScore.setWordName(wordName);
            engUserScore.setUser(username);
            insertEngUserScore(engUserScore);
        }
    }
}

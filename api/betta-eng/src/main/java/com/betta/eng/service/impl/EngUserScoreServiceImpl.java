package com.betta.eng.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import com.betta.common.utils.SecurityUtils;
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
     * 查询用户成绩
     *
     * @param id 用户成绩主键
     * @return 用户成绩
     */
    @Override
    public EngUserScore selectEngUserScoreById(Long id) {
        return engUserScoreMapper.selectEngUserScoreById(id);
    }

    /**
     * 查询用户成绩列表
     *
     * @param engUserScore 用户成绩
     * @return 用户成绩
     */
    @Override
    public List<EngUserScore> selectEngUserScoreList(EngUserScore engUserScore) {
        return engUserScoreMapper.selectEngUserScoreList(engUserScore);
    }

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
        engUserScore.setUpdateTime(DateUtils.getNowDate());
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
    public List<EngUserScore> selectScoreByArticle(Long articleId, int limit) {
        return engUserScoreMapper.selectScoreByArticle(articleId, limit);
    }

    @Override
    public void batchUpdate(List<EngUserScore> engUserScoreList) {
        EngUserScore search = new EngUserScore();
        search.setUser(SecurityUtils.getUsername());
        engUserScoreList.forEach(score -> {
            search.setWordName(score.getWordName());
            List<EngUserScore> engUserScores = engUserScoreMapper.selectEngUserScoreList(search);
            if(!engUserScores.isEmpty()){
                //更新分数
                EngUserScore engUserScore = engUserScores.get(0);
                engUserScore.setFamiliarity(score.getFamiliarity());
                updateEngUserScore(engUserScore);
            }else {
                //新增
                EngUserScore engUserScore = new EngUserScore();
                engUserScore.setUser(SecurityUtils.getUsername());
                engUserScore.setFamiliarity(score.getFamiliarity());
                engUserScore.setWordName(score.getWordName());
                insertEngUserScore(engUserScore);
            }
        });
    }
}

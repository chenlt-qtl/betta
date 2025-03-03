package com.betta.eng.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.betta.eng.domain.EngUserScore;
import com.betta.eng.domain.vo.EngUserScoreVo;

/**
 * 用户成绩Service接口
 *
 * @author chenlt
 * @date 2024-07-13
 */
public interface IEngUserScoreService extends IService<EngUserScore> {

    /**
     * 查询用户成绩列表
     *
     * @param engUserScore 用户成绩
     * @return 用户成绩集合
     */
    public List<EngUserScore> selectEngUserScoreList(EngUserScore engUserScore);


    void batchUpdate(List<EngUserScore> engUserScoreList);

    List<EngUserScoreVo> selectEngUserScoreVoList(EngUserScore engUserScore);

    void updateEngUserScore(String wordName, int i);
}

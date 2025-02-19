package com.betta.eng.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.betta.eng.domain.EngSentence;
import com.betta.eng.domain.vo.SentenceVo;
import org.apache.ibatis.annotations.Param;

/**
 * 文章句子Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-02
 */
public interface EngSentenceMapper  extends BaseMapper<EngSentence>
{

    List<EngSentence> selectPlayList(@Param(Constants.WRAPPER) QueryWrapper<EngSentence> queryWrapper);

    List<SentenceVo> selectByWordTop10(@Param(Constants.WRAPPER) QueryWrapper<EngSentence> ew);

}

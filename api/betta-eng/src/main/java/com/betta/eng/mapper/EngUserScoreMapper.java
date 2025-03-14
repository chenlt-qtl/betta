package com.betta.eng.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betta.eng.domain.EngUserScore;
import com.betta.eng.domain.vo.EngUserScoreVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户成绩Mapper接口
 * 
 * @author chenlt
 * @date 2024-07-13
 */
public interface EngUserScoreMapper extends BaseMapper<EngUserScore>
{

    /**
     * 查询用户成绩列表
     *
     * @param engUserScore 用户成绩
     * @return 用户成绩集合
     */
    public List<EngUserScore> selectEngUserScoreList(EngUserScore engUserScore);

    /**
     * 新增用户成绩
     * 
     * @param engUserScore 用户成绩
     * @return 结果
     */
    public int insertEngUserScore(EngUserScore engUserScore);

    /**
     * 修改用户成绩
     * 
     * @param engUserScore 用户成绩
     * @return 结果
     */
    public int updateEngUserScore(EngUserScore engUserScore);

    /**
     * 删除用户成绩
     * 
     * @param id 用户成绩主键
     * @return 结果
     */
    public int deleteEngUserScoreById(Long id);

    /**
     * 批量删除用户成绩
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEngUserScoreByIds(Long[] ids);


    List<EngUserScoreVo> selectEngUserScoreVo(EngUserScore engUserScore);

    public EngUserScore getByWordName(@Param("wordName") String wordName,@Param("user") String user);
}

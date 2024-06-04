package com.betta.eng.mapper;

import java.util.List;
import com.betta.eng.domain.EngIcibaSentence;

/**
 * 爱词霸例句Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
public interface EngIcibaSentenceMapper 
{
    /**
     * 查询爱词霸例句
     * 
     * @param id 爱词霸例句主键
     * @return 爱词霸例句
     */
    public EngIcibaSentence selectEngIcibaSentenceById(Long id);

    /**
     * 查询爱词霸例句列表
     * 
     * @param engIcibaSentence 爱词霸例句
     * @return 爱词霸例句集合
     */
    public List<EngIcibaSentence> selectEngIcibaSentenceList(EngIcibaSentence engIcibaSentence);

    /**
     * 新增爱词霸例句
     * 
     * @param engIcibaSentence 爱词霸例句
     * @return 结果
     */
    public int insertEngIcibaSentence(EngIcibaSentence engIcibaSentence);

    /**
     * 修改爱词霸例句
     * 
     * @param engIcibaSentence 爱词霸例句
     * @return 结果
     */
    public int updateEngIcibaSentence(EngIcibaSentence engIcibaSentence);

    /**
     * 删除爱词霸例句
     * 
     * @param id 爱词霸例句主键
     * @return 结果
     */
    public int deleteEngIcibaSentenceById(Long id);

    /**
     * 批量删除爱词霸例句
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEngIcibaSentenceByIds(Long[] ids);

    void deleteByWordId(Long wordId);
}

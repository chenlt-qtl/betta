package com.betta.eng.service.impl;

import java.util.List;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.EngIcibaSentenceMapper;
import com.betta.eng.domain.EngIcibaSentence;
import com.betta.eng.service.IEngIcibaSentenceService;

/**
 * 爱词霸例句Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Service
public class EngIcibaSentenceServiceImpl implements IEngIcibaSentenceService 
{
    @Autowired
    private EngIcibaSentenceMapper engIcibaSentenceMapper;

    /**
     * 查询爱词霸例句
     * 
     * @param id 爱词霸例句主键
     * @return 爱词霸例句
     */
    @Override
    public EngIcibaSentence selectEngIcibaSentenceById(Long id)
    {
        return engIcibaSentenceMapper.selectEngIcibaSentenceById(id);
    }

    /**
     * 查询爱词霸例句列表
     * 
     * @param engIcibaSentence 爱词霸例句
     * @return 爱词霸例句
     */
    @Override
    public List<EngIcibaSentence> selectEngIcibaSentenceList(EngIcibaSentence engIcibaSentence)
    {
        return engIcibaSentenceMapper.selectEngIcibaSentenceList(engIcibaSentence);
    }

    /**
     * 新增爱词霸例句
     * 
     * @param engIcibaSentence 爱词霸例句
     * @return 结果
     */
    @Override
    public int insertEngIcibaSentence(EngIcibaSentence engIcibaSentence)
    {
        engIcibaSentence.setCreateTime(DateUtils.getNowDate());
        return engIcibaSentenceMapper.insertEngIcibaSentence(engIcibaSentence);
    }

    /**
     * 修改爱词霸例句
     * 
     * @param engIcibaSentence 爱词霸例句
     * @return 结果
     */
    @Override
    public int updateEngIcibaSentence(EngIcibaSentence engIcibaSentence)
    {
        engIcibaSentence.setUpdateTime(DateUtils.getNowDate());
        return engIcibaSentenceMapper.updateEngIcibaSentence(engIcibaSentence);
    }

    /**
     * 批量删除爱词霸例句
     * 
     * @param ids 需要删除的爱词霸例句主键
     * @return 结果
     */
    @Override
    public int deleteEngIcibaSentenceByIds(Long[] ids)
    {
        return engIcibaSentenceMapper.deleteEngIcibaSentenceByIds(ids);
    }

    /**
     * 删除爱词霸例句信息
     * 
     * @param id 爱词霸例句主键
     * @return 结果
     */
    @Override
    public int deleteEngIcibaSentenceById(Long id)
    {
        return engIcibaSentenceMapper.deleteEngIcibaSentenceById(id);
    }

    @Override
    public void deleteByWordId(Long id) {
        engIcibaSentenceMapper.deleteByWordId(id);
    }
}

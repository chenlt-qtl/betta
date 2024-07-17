package com.betta.eng.service.impl;

import java.util.List;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betta.eng.mapper.EngArticleWordRelMapper;
import com.betta.eng.domain.EngArticleWordRel;
import com.betta.eng.service.IEngArticleWordRelService;

/**
 * 文章单词关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
@Service
public class EngArticleWordRelServiceImpl implements IEngArticleWordRelService 
{
    @Autowired
    private EngArticleWordRelMapper engArticleWordRelMapper;

    /**
     * 查询文章单词关系
     * 
     * @param id 文章单词关系主键
     * @return 文章单词关系
     */
    @Override
    public EngArticleWordRel selectEngArticleWordRelById(Long id)
    {
        return engArticleWordRelMapper.selectEngArticleWordRelById(id);
    }

    /**
     * 查询文章单词关系列表
     * 
     * @param engArticleWordRel 文章单词关系
     * @return 文章单词关系
     */
    @Override
    @CreateByScope("")
    public List<EngArticleWordRel> selectEngArticleWordRelList(EngArticleWordRel engArticleWordRel)
    {
        return engArticleWordRelMapper.selectEngArticleWordRelList(engArticleWordRel);
    }

    /**
     * 新增文章单词关系
     * 
     * @param engArticleWordRel 文章单词关系
     * @return 结果
     */
    @Override
    public int insertEngArticleWordRel(EngArticleWordRel engArticleWordRel)
    {
        engArticleWordRel.setCreateTime(DateUtils.getNowDate());
        return engArticleWordRelMapper.insertEngArticleWordRel(engArticleWordRel);
    }

    /**
     * 批量删除文章单词关系
     * 
     * @param ids 需要删除的文章单词关系主键
     * @return 结果
     */
    @Override
    public int deleteEngArticleWordRelByIds(Long[] ids)
    {
        if(ids.length>0) {
            return engArticleWordRelMapper.deleteEngArticleWordRelByIds(ids);
        }else{
            return 0;
        }
    }

    /**
     * 删除文章单词关系信息
     * 
     * @param id 文章单词关系主键
     * @return 结果
     */
    @Override
    public int deleteEngArticleWordRelById(Long id)
    {
        return engArticleWordRelMapper.deleteEngArticleWordRelById(id);
    }

    @Override
    public int deleteByArticle(Long articleId) {
        return engArticleWordRelMapper.deleteByArticleId(articleId);
    }
}

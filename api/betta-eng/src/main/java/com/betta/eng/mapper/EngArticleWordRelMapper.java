package com.betta.eng.mapper;

import java.util.List;
import com.betta.eng.domain.EngArticleWordRel;

/**
 * 文章单词关系Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-03
 */
public interface EngArticleWordRelMapper 
{
    /**
     * 查询文章单词关系
     * 
     * @param id 文章单词关系主键
     * @return 文章单词关系
     */
    public EngArticleWordRel selectEngArticleWordRelById(Long id);

    /**
     * 查询文章单词关系列表
     * 
     * @param engArticleWordRel 文章单词关系
     * @return 文章单词关系集合
     */
    public List<EngArticleWordRel> selectEngArticleWordRelList(EngArticleWordRel engArticleWordRel);

    /**
     * 新增文章单词关系
     * 
     * @param engArticleWordRel 文章单词关系
     * @return 结果
     */
    public int insertEngArticleWordRel(EngArticleWordRel engArticleWordRel);

    /**
     * 删除文章单词关系
     * 
     * @param id 文章单词关系主键
     * @return 结果
     */
    public int deleteEngArticleWordRelById(Long id);

    /**
     * 批量删除文章单词关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEngArticleWordRelByIds(Long[] ids);

    /**
     * 删除文章对应的关系
     * @param articleId
     * @return
     */
    public int deleteByArticleId(Long articleId);

}

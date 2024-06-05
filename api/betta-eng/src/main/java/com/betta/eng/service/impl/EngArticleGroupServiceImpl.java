package com.betta.eng.service.impl;

import java.util.List;

import com.betta.common.annotation.CreateByScope;
import com.betta.common.exception.ServiceException;
import com.betta.eng.domain.EngArticle;
import com.betta.eng.domain.EngArticleGroup;
import com.betta.eng.mapper.EngArticleGroupMapper;
import com.betta.eng.service.IEngArticleGroupService;
import com.betta.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 英语文章分组Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-30
 */
@Service
public class EngArticleGroupServiceImpl implements IEngArticleGroupService {
    @Autowired
    private EngArticleGroupMapper engArticleGroupMapper;

    @Autowired
    private EngArticleServiceImpl articleService;

    /**
     * 查询英语文章分组
     *
     * @param id 英语文章分组主键
     * @return 英语文章分组
     */
    @Override
    public EngArticleGroup selectEngArticleGroupById(Long id) {
        return engArticleGroupMapper.selectEngArticleGroupById(id);
    }

    /**
     * 查询英语文章分组列表
     *
     * @param engArticleGroup 英语文章分组
     * @return 英语文章分组
     */
    @Override
    @CreateByScope("")
    public List<EngArticleGroup> selectEngArticleGroupList(EngArticleGroup engArticleGroup) {
        return engArticleGroupMapper.selectEngArticleGroupList(engArticleGroup);
    }

    /**
     * 新增英语文章分组
     *
     * @param engArticleGroup 英语文章分组
     * @return 结果
     */
    @Override
    public int insertEngArticleGroup(EngArticleGroup engArticleGroup) {
        engArticleGroup.setCreateTime(DateUtils.getNowDate());
        return engArticleGroupMapper.insertEngArticleGroup(engArticleGroup);
    }

    /**
     * 修改英语文章分组
     *
     * @param engArticleGroup 英语文章分组
     * @return 结果
     */
    @Override
    public int updateEngArticleGroup(EngArticleGroup engArticleGroup) {
        engArticleGroup.setUpdateTime(DateUtils.getNowDate());
        return engArticleGroupMapper.updateEngArticleGroup(engArticleGroup);
    }

    /**
     * 批量删除英语文章分组
     *
     * @param ids 需要删除的英语文章分组主键
     * @return 结果
     */
    @Override
    public int deleteEngArticleGroupByIds(Long[] ids) {

        for (Long id : ids) {
            //检查是否有对应的文章，如果有则不允许删除
            EngArticle article = new EngArticle();
            article.setGroupId(id);
            List<EngArticle> engArticles = articleService.selectEngArticleList(article);
            if(!engArticles.isEmpty()){
                throw new ServiceException("此分组下有文章数据，请先删除文章");
            }
        }

        return engArticleGroupMapper.deleteEngArticleGroupByIds(ids);
    }

}

package com.briup.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
public interface IArticleService {
    public void saveOrUpdate(Article article);

    public void updateStatus(Integer id,String status);

    public void deleteById(Long id);

    public void deleteByIds(List<Long> ids);

    public IPage<Article> query(Integer userId,Long categoryId, String status, String title, Integer charged, String gePublishTime,String lePublishTime,int pageNum, int pageSize);

    public Article queryById(Integer id);
}

package com.briup.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.dao.UserDao;
import com.briup.cms.domain.Article;
import com.briup.cms.dao.ArticleDao;
import com.briup.cms.domain.User;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.briup.cms.util.ResultCode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    UserDao userDao;

    @Override
    public void saveOrUpdate(Article article) {
        if (article==null){
            throw new ServiceException(ResultCode.PARAM_IS_BLANK);
        }
        if (article.getId()==null){
            if (article.getContent()==null||article.getTitle()==null){
                throw new ServiceException(ResultCode.PARAM_NOT_COMPLETE);
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            article.setPublishTime(localDateTime);
            User user = userDao.selectById(article.getUserId());
            if (user.getRoleId()==1){
                article.setStatus("已审核");
            }
            articleDao.insert(article);
        }else {
            Article article1 = articleDao.selectById(article);
            if (article1==null){
                throw new ServiceException(ResultCode.DATA_NONE);
            }
            if (article1.getStatus().equals("已审核")){
                throw new ServiceException(ResultCode.PERMISSION_NO_ACCESS);
            }
            articleDao.updateById(article);
        }

    }

    @Override
    public void updateStatus(Integer id,String status) {
        Article article1 = articleDao.selectById(id);
        if (article1==null){
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper();//照搬

        updateWrapper.eq("id",id);

        updateWrapper.set("status",status);

        articleDao.update(article1,updateWrapper);
    }

    @Override
    public void deleteById(Long id) {
        Article article = articleDao.selectById(id);
        if (article==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
        articleDao.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public IPage<Article> query(Integer userId,Long categoryId, String status, String title, Integer charged, String gePublishTime,String lePublishTime,int pageNum, int pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse=null;
        Date parse1=null;
        try {
            parse = sdf.parse(gePublishTime);
            parse1= sdf.parse(lePublishTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        if (userId==null) {


            wrapper.eq(categoryId != null, Article::getCategoryId, categoryId)
                    .eq(status != null, Article::getStatus, status)
                    .like(title != null, Article::getTitle, title)
                    .eq(charged != null, Article::getCharged, charged)
                    .ge(gePublishTime!=null,Article::getPublishTime,parse)
                    .le(lePublishTime!=null,Article::getPublishTime,parse1);
            articleDao.selectPage(page, wrapper);
            return page;
        }else {
            wrapper.eq(Article::getUserId,userId);
            articleDao.selectPage(page,wrapper);
            return page;
        }
    }

    @Override
    public Article queryById(Integer id) {
        Article article = articleDao.selectById(id);
        return article;
    }
}

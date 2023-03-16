package com.briup.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.dao.ArticleDao;
import com.briup.cms.dao.UserDao;
import com.briup.cms.domain.Article;
import com.briup.cms.domain.Comment;
import com.briup.cms.dao.CommentDao;
import com.briup.cms.domain.User;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.briup.cms.util.ResultCode;
import io.swagger.models.auth.In;
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
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ArticleDao articleDao;

    @Override
    public void addComment(Comment comment) {

        if (comment==null){
            throw new ServiceException(ResultCode.PARAM_IS_BLANK);
        }
        User user = userDao.selectById(comment.getUserId());
        if (user==null){
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        Article article = articleDao.selectById(comment.getArticleId());
        if (article==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setPublishTime(localDateTime);
        commentDao.insert(comment);
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = commentDao.selectById(id);
        if (comment==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
        commentDao.deleteById(id);
    }

    @Override
    public void deleteByIdAll(List<Long> ids) {
        for (Long id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public List<Comment> queryByArticleId(Long ArticleId) {
        Article article = articleDao.selectById(ArticleId);
        if (article==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
        LambdaQueryWrapper<Comment> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getArticleId,ArticleId);
        List<Comment> comments = commentDao.selectList(wrapper);
        return comments;
    }

    @Override
    public IPage<Comment> query(int pageNum, int pageSize, Integer UserId, String content, Integer id, String gePublishTime,String lePublishTime) {
        Page<Comment> page=new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse=null;
        Date parse1=null;
        try {
            if (gePublishTime!=null||lePublishTime!=null) {
                parse = sdf.parse(gePublishTime);
                parse1 = sdf.parse(lePublishTime);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        wrapper.eq(UserId != null, Comment::getUserId, UserId)
                .eq(id != null,Comment::getId, id)
                .like(content != null, Comment::getContent, content)
                .ge(gePublishTime!=null,Comment::getPublishTime,parse)
                .le(lePublishTime!=null,Comment::getPublishTime,parse1);
        commentDao.selectPage(page, wrapper);
        return page;

    }
}

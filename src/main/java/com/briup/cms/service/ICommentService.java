package com.briup.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.dao.CommentDao;
import com.briup.cms.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */

public interface ICommentService {
    public void addComment(Comment comment);

    public void deleteById(Long id);

    public void deleteByIdAll(List<Long> ids);

    public List<Comment> queryByArticleId(Long ArticleId);

    public IPage<Comment> query(int pageNum, int pageSize,Integer UserId,String content,Integer id,String gePublishTime,String lePublishTime);
}

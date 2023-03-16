package com.briup.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.briup.cms.dao.CommentDao;
import com.briup.cms.dao.UserDao;
import com.briup.cms.domain.Article;
import com.briup.cms.domain.Comment;
import com.briup.cms.domain.Subcomment;
import com.briup.cms.dao.SubcommentDao;
import com.briup.cms.domain.User;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ISubcommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.briup.cms.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class SubcommentServiceImpl implements ISubcommentService {
    @Autowired
    SubcommentDao subcommentDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserDao userDao;

    @Override
    public void addSubComment(Subcomment subcomment) {
        if (subcomment==null){
            throw new ServiceException(ResultCode.PARAM_IS_BLANK);
        }
        User user = userDao.selectById(subcomment.getUserId());
        if (user==null){
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        Comment comment = commentDao.selectById(subcomment.getParentId());
        if (comment==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        subcomment.setPublishTime(localDateTime);
        subcommentDao.insert(subcomment);

    }

    @Override
    public void deleteById(Long id) {
        Subcomment subcomment = subcommentDao.selectById(id);
        if (subcomment==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
        subcommentDao.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public List<Subcomment> queryByParentId(Long ParentId) {
        Comment comment = commentDao.selectById(ParentId);
        if (comment==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }
        LambdaQueryWrapper<Subcomment> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Subcomment::getParentId,ParentId);
        List<Subcomment> subcomments = subcommentDao.selectList(wrapper);
        return subcomments;
    }
}

package com.briup.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.domain.Slideshow;
import com.briup.cms.domain.User;
import com.briup.cms.dao.UserDao;
import com.briup.cms.domain.vo.UserVo;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IUserService;
import com.briup.cms.util.JwtUtil;
import com.briup.cms.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserDao userDao;
    @Override
    public void saveOrUpDate(User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setRegisterTime(localDateTime);
        if (user.getUsername()==null||user.getPassword()==null) {
            throw new ServiceException(ResultCode.PARAM_NOT_COMPLETE);
        }
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,user.getUsername());
        Integer integer = userDao.selectCount(lqw);
            if (user.getId() != null) {
                if (user.getVip()!=null) {
                    User user1 = userDao.selectById(user);
                    if (user1.getExpiresTime()==null) {
                        user.setExpiresTime(LocalDateTime.now().plusMonths(user.getVip()));
                    }else {
                        user.setExpiresTime(user1.getExpiresTime().plusMonths(user.getVip()));
                    }
                }
                userDao.updateById(user);
            } else {
                if (integer!=0){
                    throw new ServiceException(ResultCode.USER_HAS_EXISTED);
                }
                userDao.insert(user);
            }
    }

    @Override
    public void deletedById(long id) {
        User user = this.queryById(id);
        userDao.deleteById(user);
    }

    @Override
    public User queryById(long id) {
        User user = userDao.selectById(id);
        if (user==null){
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }else {
            return user;
        }
    }

    @Override
    public IPage<UserVo> query(int pageNum, int pageSize, Integer roleId, String username, String status) {
        Page<UserVo> page=new Page<>(pageNum,pageSize);
        /*LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(status!=null,User::getStatus,status).like(username!=null,User::getUsername,username).eq(roleId!=null,User::getRoleId,roleId);

        userDao.selectPage(page,wrapper);*/
        IPage<UserVo> userVoList = userDao.getUserVoList(page, roleId, username, status);
        return userVoList;
    }

    @Override
    public String login(String username, String password) {
        if (username==null ||password==null){
            throw new ServiceException(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper();
        wrapper.eq(User::getUsername,username);
        wrapper.eq(User::getPassword,password);
        User user = userDao.selectOne(wrapper);
        if (user==null){
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("username",user.getUsername());
        map.put("userId",user.getId());
        String sign = JwtUtil.sign(user.getUsername(), map);
        return sign;
    }


}

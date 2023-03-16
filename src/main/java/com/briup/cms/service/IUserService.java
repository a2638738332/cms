package com.briup.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.briup.cms.domain.vo.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
public interface IUserService {
    public void saveOrUpDate(User user);
    public void deletedById(long id);
    public User queryById(long id);
    public IPage<UserVo> query(int pageNum, int pageSize, Integer roleId, String username, String status);

    public String login(String username,String password);
}

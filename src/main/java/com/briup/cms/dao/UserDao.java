package com.briup.cms.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.briup.cms.domain.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    IPage<UserVo> getUserVoList(Page<UserVo> page, @Param("roleId") Integer roleId, @Param("username") String username, @Param("status") String status);

}

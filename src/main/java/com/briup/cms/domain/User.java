package com.briup.cms.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;
    /*
    * vip
    * */
    private Integer vip;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 会员到期时间
     */
    private LocalDateTime expiresTime;

    /**
     * 用户冻结时间
     */
    private LocalDateTime freezeTime;

    /**
     * 用户删除状态
     */
    @TableLogic
    private Integer deleted;


}

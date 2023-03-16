package com.briup.cms.domain.vo;

import com.briup.cms.domain.Role;
import com.briup.cms.domain.User;
import lombok.Data;

@Data
public class UserVo extends User {
    private Role role;

}

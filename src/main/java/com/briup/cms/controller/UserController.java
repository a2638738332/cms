package com.briup.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.User;
import com.briup.cms.domain.vo.UserVo;
import com.briup.cms.service.impl.UserServiceImpl;
import com.briup.cms.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @ApiOperation("新增或修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value ="传入用户对象")
    })
    @PostMapping("/saveOrUpDate")
    public Result saveOrUpDate(@RequestBody User user){
        userService.saveOrUpDate(user);
        return Result.success();
    }

    @ApiOperation("通过id查找")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="用户id")
    })
    @GetMapping("/queryById/{id}")
    public Result queryById(@PathVariable int id){
        User user = userService.queryById(id);
        return Result.success(user);
    }

    @ApiOperation("通过id逻辑删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="用户id")
    })
    @GetMapping("/deletedById/{id}")
    public Result deletedById(@PathVariable int id){
        userService.deletedById(id);
        return Result.success();
    }

    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value ="要查看第几页"),
            @ApiImplicitParam(name = "pageSize",value ="设置每页显示条数"),
            @ApiImplicitParam(name = "roleId",value ="roleId"),
            @ApiImplicitParam(name = "username",value ="用户名"),
            @ApiImplicitParam(name = "status",value ="启用/禁用")
    })
    @GetMapping("/query")
    public Result query(int pageNum, int pageSize, Integer roleId, String username, String status){
        IPage<UserVo> query = userService.query(pageNum, pageSize, roleId, username, status);
        return Result.success(query);
    }
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value ="用户名"),
            @ApiImplicitParam(name = "password",value ="密码")
    })
    @PostMapping("/login")
    public Result login(String username,String password){
        String login = userService.login(username, password);
        System.out.println("dev改动");
        return Result.success(login);
    }

}


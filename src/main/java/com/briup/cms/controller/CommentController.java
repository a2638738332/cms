package com.briup.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.Comment;
import com.briup.cms.service.impl.CommentServiceImpl;
import com.briup.cms.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @PostMapping("/addComment")
    @ApiOperation("添加评论")
    @ApiImplicitParams({
    })
    private Result addComment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return Result.success();
    }
    @GetMapping("/deleteById")
    @ApiOperation("删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="评论id")
    })
    private Result deleteById(Long id){
        commentService.deleteById(id);
        return Result.success();
    }
@PostMapping("/deleteByIds")
@ApiOperation("批量删除评论")
    private Result deleteByIds(@RequestBody List<Long> ids){
        commentService.deleteByIdAll(ids);
        return Result.success();
    }
@GetMapping("/queryByArticleId")
@ApiOperation("查询文章下所有评论")
@ApiImplicitParams({
        @ApiImplicitParam(name = "ArticleId",value ="文章id")
})
    private  Result queryByArticleId(Long ArticleId){
        List<Comment> comments = commentService.queryByArticleId(ArticleId);
        return Result.success(comments);
    }
    @GetMapping("/query")
    @ApiOperation("多条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "UserId",value ="用户id"),
            @ApiImplicitParam(name = "id",value ="评论id"),
            @ApiImplicitParam(name = "content",value ="评论关键字"),
            @ApiImplicitParam(name = "gePublishTime",value ="起始时间"),
            @ApiImplicitParam(name = "lePublishTime",value ="结束时间"),
            @ApiImplicitParam(name = "pageNum",value ="查询的页数"),
            @ApiImplicitParam(name = "pageSize",value ="每页显示条数"),
    })
    private Result query(int pageNum, int pageSize, Integer UserId, String content, Integer id, String gePublishTime,String lePublishTime){
        IPage<Comment> query = commentService.query(pageNum, pageSize, UserId, content, id, gePublishTime, lePublishTime);
        return Result.success(query);
    }

}


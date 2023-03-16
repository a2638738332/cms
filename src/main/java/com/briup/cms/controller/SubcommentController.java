package com.briup.cms.controller;


import com.briup.cms.domain.Comment;
import com.briup.cms.domain.Subcomment;
import com.briup.cms.service.impl.CommentServiceImpl;
import com.briup.cms.service.impl.SubcommentServiceImpl;
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
@RequestMapping("/subcomment")
public class SubcommentController {
    @Autowired
    SubcommentServiceImpl subcommentService;
    @PostMapping("/addSubComment")
    @ApiOperation("添加二级评论")
    @ApiImplicitParams({
    })
    private Result addComment(@RequestBody Subcomment subcomment){
        subcommentService.addSubComment(subcomment);
        return Result.success();
    }
    @GetMapping("/deleteById")
    @ApiOperation("删除二级评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="评论id")
    })
    private Result deleteById(Long id){
        subcommentService.deleteById(id);
        return Result.success();
    }
    @PostMapping("/deleteByIds")
    @ApiOperation("批量删除二级评论")
    private Result deleteByIds(@RequestBody List<Long> ids){
        subcommentService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/queryByParentId")
    @ApiOperation("查询一级评论下所有评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ArticleId",value ="一级评论id")
    })
    private  Result queryByParentId(Long ArticleId){
        List<Subcomment> subcomments = subcommentService.queryByParentId(ArticleId);
        return Result.success(subcomments);
    }
}


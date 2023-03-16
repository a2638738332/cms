package com.briup.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.Article;
import com.briup.cms.service.impl.ArticleServiceImpl;
import com.briup.cms.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleServiceImpl articleService;
    @PostMapping("/saveOrUpdate")
    @ApiOperation("咨询管理（新增/删除）")
    @ApiImplicitParams({
    })
    private Result saveOrUpdate(@RequestBody Article article){
        articleService.saveOrUpdate(article);
        return Result.success();
    }

    @PostMapping("/updateStatus")
    @ApiOperation("审核文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="要审核的文章id"),
            @ApiImplicitParam(name = "status",value ="(已审核/未审核)")
    })
    private Result updateStatus(Integer id,String status){
        articleService.updateStatus(id,status);
        return Result.success();
    }
    @GetMapping("/deleteById")
    @ApiOperation("根据id删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="要删除的id"),
    })
    private Result deleteById(Long id){
        articleService.deleteById(id);
        return Result.success();
    }
    @PostMapping("/deleteByIds")
    @ApiOperation("批量删除")
    public Result deleteByIds(@RequestBody List<Long> ids){
        articleService.deleteByIds(ids);
        return Result.success();
    }
    @GetMapping("/query")
    @ApiOperation("多条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value ="发布者id"),
            @ApiImplicitParam(name = "categoryId",value ="类别id"),
            @ApiImplicitParam(name = "status",value ="(已审核/未审核)"),
            @ApiImplicitParam(name = "title",value ="标题关键字"),
            @ApiImplicitParam(name = "charged",value ="是否收费（0为免费）"),
            @ApiImplicitParam(name = "gePublishTime",value ="起始时间"),
            @ApiImplicitParam(name = "lePublishTime",value ="结束时间"),
            @ApiImplicitParam(name = "pageNum",value ="查询的页数"),
            @ApiImplicitParam(name = "pageSize",value ="每页显示条数"),
    })
    public Result query(Integer userId,Long categoryId, String status, String title, Integer charged, String gePublishTime,String lePublishTime,int pageNum, int pageSize){


        IPage<Article> query = articleService.query(userId, categoryId, status, title, charged, gePublishTime,lePublishTime, pageNum, pageSize);
        return Result.success(query);
    }
    @GetMapping("/queryById")
    @ApiOperation("根据文章id查找")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="要查找的文章id"),
    })
    private Result queryById(Integer id){
        Article article = articleService.queryById(id);
        return Result.success(article);
    }

}



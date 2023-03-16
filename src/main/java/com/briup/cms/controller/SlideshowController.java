package com.briup.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.Slideshow;
import com.briup.cms.service.impl.SlideshowServiceImpl;
import com.briup.cms.util.Result;
import io.swagger.annotations.Api;
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
@RequestMapping("/slideshow")
public class SlideshowController {
    @Autowired
    SlideshowServiceImpl slideshowService;
    @ApiOperation("新增或修改轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "slideshow",value ="新增或修改轮播图的信息")
    })
    @PostMapping("/saveOrUpdate")
    private Result saveOrUpdate(@RequestBody Slideshow slideshow){
        slideshowService.saveOrUpdate(slideshow);
        return Result.success();
    }
    @ApiOperation("通过id删除轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="删除轮播图的信息的id")
    })
    @GetMapping("/deleteById/{id}")
    private Result deleteById(@PathVariable Long id){
        slideshowService.deleteById(id);
        return Result.success();
    }
    @ApiOperation("通过id批量删除轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value ="批量删除轮播图的信息的id")
    })
    @PostMapping("/deleteByIds")
    private Result deleteByIds(@RequestBody List<Long> ids){
        slideshowService.deleteByIds(ids);
        return Result.success();
    }
    @ApiOperation("通过状态进行分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize",value ="每页条数"),
            @ApiImplicitParam(name = "pageNum",value ="查看第几页"),
            @ApiImplicitParam(name = "status",value ="查询条件（启用/禁用）")
    })
    @RequestMapping("/query")
    public Result query(int pageNum,int pageSize,String status){
        IPage<Slideshow> result = slideshowService.query(pageNum, pageSize, status);
        return Result.success(result);
    }

}


package com.briup.cms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.Category;
import com.briup.cms.domain.vo.CategoryVo;
import com.briup.cms.service.impl.CategoryServiceImpl;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;
    @ApiOperation("新增或修改")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Category category){
        categoryService.saveOrUpdate(category);
        return Result.success();
    }
    @ApiOperation("通过id逻辑删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="用户id")
    })
    @GetMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return Result.success();
    }
    @ApiOperation("批量删除")
    @PostMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody List<Long> ids){
        categoryService.deleteByIds(ids);
        return Result.success();
    }
    @GetMapping("/queryAll")
    @ApiOperation("分页查询全部")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value ="要查询的页数"),
            @ApiImplicitParam(name = "pageSize",value ="每页展示条数")
    })
    public Result queryAll(Integer pageNum, Integer pageSize){
        IPage<Category> categoryIPage = categoryService.queryAll(pageNum, pageSize);
        return Result.success(categoryIPage);
    }
    @GetMapping("/query")
    @ApiOperation("查询所有一级栏目(含2级)")
    public Result query(){
        List<CategoryVo> query = categoryService.query();
        return Result.success(query);
    }


}


package com.briup.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.briup.cms.domain.vo.CategoryVo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
public interface ICategoryService{
    public void saveOrUpdate(Category category);
    public void deleteById(Long id);
    public void deleteByIds(List<Long> ids);
    public IPage<Category> queryAll(Integer pageNum, Integer pageSize);

    public List<CategoryVo> query();


}

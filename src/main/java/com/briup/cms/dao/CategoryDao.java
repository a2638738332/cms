package com.briup.cms.dao;

import com.briup.cms.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.briup.cms.domain.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

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
public interface CategoryDao extends BaseMapper<Category> {

    public List<CategoryVo> query();

}

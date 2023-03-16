package com.briup.cms.dao;

import com.briup.cms.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

}

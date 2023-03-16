package com.briup.cms.domain.vo;

import com.briup.cms.domain.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends Category {
    private List<Category> categorys;
}

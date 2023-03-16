package com.briup.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.domain.Category;
import com.briup.cms.dao.CategoryDao;
import com.briup.cms.domain.vo.CategoryVo;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ICategoryService;
import com.briup.cms.util.ResultCode;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Override
    public void saveOrUpdate(Category category) {
        if (category.getName()==null){
            throw new ServiceException(ResultCode.PARAM_IS_BLANK);
        }
        if (category.getId()==null){
            LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Category::getName,category.getName());
            Integer integer = categoryDao.selectCount(lqw);
            if (integer!=0){
                throw new ServiceException(ResultCode.USER_HAS_EXISTED);
            }
            if (category.getParentId()!=null){
                Category category1 = categoryDao.selectById(category.getParentId());
                if (category1==null){
                    throw new ServiceException(ResultCode.DATA_WRONG);
                }
            }
                //查询最大值
                QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("max(order_num) as OrderNum");
                Category category1 = categoryDao.selectOne(queryWrapper);
                System.out.println(category1.getOrderNum());
                category.setOrderNum(category1.getOrderNum()+1);

            categoryDao.insert(category);
        }else {
            LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Category::getName,category.getName());
            Integer integer = categoryDao.selectCount(lqw);
            if (integer!=0){
                throw new ServiceException(ResultCode.USER_HAS_EXISTED);
            }
            categoryDao.updateById(category);
        }
    }
    @Override
    public void deleteById(Long id){
        Category category = categoryDao.selectById(id);
        if (category==null){
            throw new ServiceException(ResultCode.USER_NOT_EXIST);
        }
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Category::getParentId,id);
        Category category1 = categoryDao.selectOne(lqw);
        if (category1!=null){
            throw new ServiceException(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
        }
        categoryDao.deleteById(id);
    }
    @Override
    public void deleteByIds(List<Long> ids){
        for (Long id : ids) {
            this.deleteById(id);
        }
    }

    @Override
    public IPage<Category> queryAll(Integer pageNum, Integer pageSize) {
        Page<Category> page=new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.select().orderByAsc(Category::getOrderNum).orderByAsc(Category::getParentId);
        /*QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().orderByAsc("order_num","parent_id");*/
        categoryDao.selectPage(page, wrapper);
        return page;
    }

    @Override
    public List<CategoryVo> query() {
        List<CategoryVo> query = categoryDao.query();
        return query;
    }


}
























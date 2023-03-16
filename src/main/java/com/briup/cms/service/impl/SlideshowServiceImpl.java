package com.briup.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.briup.cms.domain.Slideshow;
import com.briup.cms.dao.SlideshowDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ISlideshowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.briup.cms.util.Result;
import com.briup.cms.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class SlideshowServiceImpl implements ISlideshowService {
    @Autowired
    SlideshowDao slideshowDao;

    @Override
    public void saveOrUpdate(Slideshow slideshow) {
        LocalDateTime localDateTime = LocalDateTime.now();
        slideshow.setUploadTime(localDateTime);
        if (slideshow!=null){
            LambdaQueryWrapper<Slideshow> lqw = new LambdaQueryWrapper<>();
            lqw.eq(Slideshow::getUrl,slideshow.getUrl());
            Integer integer = slideshowDao.selectCount(lqw);
            if (integer!=0){
                System.out.println("url已经存在");
                throw new ServiceException(ResultCode.DATA_EXISTED);
            }else {
                if (slideshow.getId() == null) {
                    slideshowDao.insert(slideshow);
                } else {
                    if (slideshowDao.selectById(slideshow.getId()) != null) {
                        slideshowDao.updateById(slideshow);
                    }else {
                        System.out.println("id不存在");
                        throw new ServiceException(ResultCode.DATA_NONE);
                    }
                }
            }
        }else {
            System.out.println("数据为空");
            throw new ServiceException(ResultCode.DATA_EXISTED);
        }

        //判断传入的轮播图是否为空
        //判断url是否唯一（Mybatis-Plus Lambda条件构造器去查询url)
        //如果url已存在 抛出异常
        //判断新增操作还是修改操作
        //slideshow.getId()=null的时候为新增
        //如果不为空则为修改
        //在修改的过程当中，先判断当前的轮播图是否在数据库当中
        //如果存在则修改
    }

    @Override
    public IPage<Slideshow> query(int pageNum, int pageSize, String status) {
        Page<Slideshow> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Slideshow> wrapper = new LambdaQueryWrapper<>();
        if(status!=null){
            wrapper.eq(Slideshow::getStatus,status);
        }
        slideshowDao.selectPage(page,wrapper);
        return page;
    }

    @Override
    public void deleteById(Long id) {
        Slideshow slideshow = slideshowDao.selectById(id);
        if (slideshow==null){
            throw new ServiceException(ResultCode.DATA_NONE);
        }else {
            slideshowDao.deleteById(id);
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {

        //slideshowDao.selectBatchIds(ids);
        List<Long> idm=null;
        //1、调用批量删除方法
        for (long id:
             ids) {
            Slideshow slideshow = slideshowDao.selectById(id);
            if (slideshow!=null){
                if (slideshow.getId()!=1) {
                    slideshowDao.deleteById(id);
                }
            }
        }
        //2、循环调用deleteById
    }
}

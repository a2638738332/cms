package com.briup.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.briup.cms.domain.Slideshow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
public interface ISlideshowService {
    public void saveOrUpdate(Slideshow slideshow);
    public IPage<Slideshow> query(int pageNum,int pageSize,String status);
    public void deleteById(Long id);
    public void deleteByIds(List<Long> ids);
}

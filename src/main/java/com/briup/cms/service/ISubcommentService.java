package com.briup.cms.service;

import com.briup.cms.domain.Subcomment;
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
public interface ISubcommentService{
    public void addSubComment(Subcomment subcomment);

    public void deleteById(Long id);

    public void deleteByIds(List<Long> ids);

    public List<Subcomment> queryByParentId(Long id);
}

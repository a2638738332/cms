package com.briup.cms.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mpInterceptor=new
                MybatisPlusInterceptor();
//2 添加分页拦截器
        mpInterceptor.addInnerInterceptor(new
                PaginationInnerInterceptor());
        return mpInterceptor;
    }
}
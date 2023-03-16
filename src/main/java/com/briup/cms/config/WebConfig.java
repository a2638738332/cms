package com.briup.cms.config;

import com.briup.cms.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用@Configuration注解和代码，替代xml文件进行配置
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加jwt拦截器,并指定拦截路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login");
    }

    /**
     * 创建jwt拦截器对象并加入spring容器
     */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    //跨域问题解决
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")	//配置 http://127.0.0.1:9999
                .allowCredentials(false)//配置 true，则上面配置应改为
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}

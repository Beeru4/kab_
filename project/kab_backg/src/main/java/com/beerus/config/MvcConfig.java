package com.beerus.config;

import com.beerus.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @Author Beerus
 * @Description 拦截器配置
 * @Date 2019-07-16
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private SessionInterceptor sessionInterceptor;

    /**
     * 配置静态资源,避免静态资源请求被拦截
     *
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
       /* registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");*/
        super.addResourceHandlers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                // addPathPatterns 用于添加拦截规则
                .addPathPatterns("/**")
                // excludePathPatterns 用于排除拦截
                // 注意content-path：是不用填写的
                // 项目首页登录接口
                .excludePathPatterns("/")
                // 放过静态资源
                .excludePathPatterns("/static/**")
                // 登入接口
                .excludePathPatterns("/login")
                // 锁定接口
                .excludePathPatterns("/lock_screen")
                // 解锁接口
                .excludePathPatterns("/unLock");
        super.addInterceptors(registry);
    }
}

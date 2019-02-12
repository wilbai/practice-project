package com.wil;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by wil on 2018/8/17.
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /*资源处理器*/
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/"+"/img/");
        //registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/"+"/static/");
    }

}

package com.ebook.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <b><code>WebMvcConfig</code></b>
 * <p/>
 * 配置跨域请求
 * <p/>
 * <b>Creation Time:</b> 2019/1/30 10:23.
 *
 * @author xiedeqi
 * @since nile-cmgdbs-data-monitor-be 0.1.0
 */
//
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}

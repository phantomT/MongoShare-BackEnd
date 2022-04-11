package com.ustc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置
 * @author 田宝宁
 * @date 2022/03/07
 */
@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    /**
     * 不使用@CrossOrigin注解和addCorsMappings方法是因为防止和拦截器冲突
     * 跨域设置失效
     */

    /**
     * 跨域支持
     * @param registry  跨域注册
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("token")
                .maxAge(3600);
    }
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        // 创建UrlBasedCorsConfigurationSource配置源
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        // 创建CorsConfiguration配置
//        CorsConfiguration configuration = new CorsConfiguration();
//        // 允许所有请求来源
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
//        // 允许发送cookie
//        configuration.setAllowCredentials(true);
//        // 允许所有请求方法
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        // 允许所有请求Headers
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        // 设置有效时间为30min
//        configuration.setMaxAge(1800L);
//        source.registerCorsConfiguration("/**", configuration);
//        // 创建FilterRegistrationBean
//        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
//                new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }

    /**
     * 添加拦截器
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }
}

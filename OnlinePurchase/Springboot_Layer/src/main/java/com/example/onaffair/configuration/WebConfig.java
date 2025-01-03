package com.example.onaffair.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置全局 CORS
        registry.addMapping("/api/**")  // 针对 /api 开头的所有路径启用 CORS
                .allowedOrigins(
                        "http://localhost:720",
                        "http://192.168.91.96:720"  // 添加多个允许的来源
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的 HTTP 方法
                .allowedHeaders("*")  // 允许所有请求头
                .allowCredentials(true);  // 允许携带认证信息（如 cookies）
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射运行时路径
        registry.addResourceHandler("/static/avatar/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/target/classes/static/avatar/");
    }
}


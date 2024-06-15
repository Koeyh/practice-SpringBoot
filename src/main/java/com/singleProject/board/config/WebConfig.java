package com.singleProject.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    // 화면(View)에서 사용 할 경로. 처음 사용해보는 유형
    private String resourcePath = "/upload/**";
    private String savePath = "file:////D:/Source/singleProject/development/Visual_Studio_Code/spring_upload_files/";
    
    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
    }

}

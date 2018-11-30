package com.dj.scaffold.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {

    private boolean swaggerShow;
    private String uploadPath;

    public boolean isSwaggerShow() {
        return swaggerShow;
    }

    public void setSwaggerShow(boolean swaggerShow) {
        this.swaggerShow = swaggerShow;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}

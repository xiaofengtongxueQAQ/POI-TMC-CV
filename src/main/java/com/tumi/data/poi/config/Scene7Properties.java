package com.tumi.data.poi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "scene7")
public class Scene7Properties {
    private String protocol;
    private String host;
    private String path;
    private String group;
    private String imagesetParam;
    private String imagesetSuffix;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getImagesetParam() {
        return imagesetParam;
    }

    public void setImagesetParam(String imagesetParam) {
        this.imagesetParam = imagesetParam;
    }

    public String getImagesetSuffix() {
        return imagesetSuffix;
    }

    public void setImagesetSuffix(String imagesetSuffix) {
        this.imagesetSuffix = imagesetSuffix;
    }

    public String getScene7Url() {
        String url = protocol + "://" + host + path + group;
        return url;
    }
}

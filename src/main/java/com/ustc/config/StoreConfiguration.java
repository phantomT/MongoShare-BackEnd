package com.ustc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 田宝宁
 */
@Component
@ConfigurationProperties(prefix = "store")
public class StoreConfiguration {

    private String storePath;

    public String getStorePath() {
        return storePath;
    }

    public StoreConfiguration setStorePath(String storePath) {
        this.storePath = storePath;
        return this;
    }
}

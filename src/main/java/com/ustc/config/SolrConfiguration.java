package com.ustc.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 田宝宁
 * @date 2021/6/27
 */
@Configuration
@ConfigurationProperties(prefix = "solr")
public class SolrConfiguration {
    private String path;

    /**
     * 获取SolrClient
     * @return SolrClient
     */
    @Bean(name = "solrClient")
    public SolrClient getSolrClient(){
        return new HttpSolrClient.Builder(path).build();
    }

    public String getPath() {
        return path;
    }

    public SolrConfiguration setPath(String path) {
        this.path = path;
        return this;
    }
}

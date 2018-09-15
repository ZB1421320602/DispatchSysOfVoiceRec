package com.telecom.ccs.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;

@Configuration
public class ElasticSearchConfig {

    private Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Value("${elasticsearch.ip}")
    private String hostName;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Value("${elasticsearch.pool}")
    private String poolSize;

    @Bean
    public Client client() {

        TransportClient client = null;

        try {

            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName) //集群名字
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
                    .build();
            //配置信息Settings自定义
            client = new PreBuiltTransportClient(esSetting);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), port));

        } catch (Exception e) {

            logger.error(e.toString());

        }

        return client;

    }



    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {

        return new ElasticsearchTemplate(client);

    }
}

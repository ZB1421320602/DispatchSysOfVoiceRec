package com.telecom.ccs.utils.elasticsearch;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class ElasticSearchOps {

    private static  Logger logger = LoggerFactory.getLogger(ElasticSearchOps.class);

    @Autowired
    private ElasticsearchOperations esop;

    private static ElasticsearchOperations  myesop;

    private static Client client;

    @PostConstruct
    public void init() {
        myesop = esop;
        client = myesop.getClient();
    }


    /* 判断索引是否存在 */
    public static  boolean indexIsExisted(String index){

        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = client.admin().indices().exists(request).actionGet();

        if(response.isExists()){
            return  true;
        }else{
            return false;
        }

    }

    /**
     * 判断type是否存在
     *
     * @param _type
     * @return
     */
    public static boolean typeIsExists(String _index, String _type) {
        String[] indices = {_index};
        TypesExistsRequest request = new TypesExistsRequest(indices, _type);
        TypesExistsResponse response = client.admin().indices().typesExists(request).actionGet();
        if (response.isExists()) {
            logger.info(_index + "." + _type + " 存在");
            return true;
        } else {
            logger.info(_index + "." + _type + " 不存在");
            return false;
        }
    }

    /**
     * 创建索引
     *
     * @param indexName
     */
    public static boolean createIndex(String indexName) {

        boolean flag = false;
        try {
            CreateIndexResponse indexResponse = client
                    .admin()
                    .indices()
                    .prepareCreate(indexName)
                    .get();

            if (indexResponse.isAcknowledged()){
                logger.info("索引 " + indexName + " 创建成功");
                return true;
            }else{
                logger.info("索引 " + indexName + " 创建失败");
                return false;
            }

        } catch (ElasticsearchException e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 创建类型
     *
     * @param indexName
     */
    public static boolean createType(String indexName,String type){

        boolean flag = false;
        try {
           flag =  createMapper(indexName,type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }


    /**
     * 创建Mapping
     *
     * @param index
     * @param type
     */
    public static void fromatMapping(String index, String type) {
        try {
            XContentBuilder builder =
                    XContentFactory.jsonBuilder()
                            .startObject()
                            .startObject("properties")
                            .startObject("serialNumber").field("type", "text").field("index", "false").endObject()
                            .startObject("insertTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .startObject("callStartTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .startObject("callEndTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .startObject("callNumber").field("type", "text").field("index", "false").endObject()
                            .startObject("calledNumber").field("type", "text").field("index", "false").endObject()
                            .startObject("callDirection").field("type", "long").endObject()
                            .startObject("isEachRecord").field("type", "long").endObject()
                            .startObject("lineType").field("type", "long").endObject()
                            .startObject("channelNumber").field("type", "long").endObject()
                            .startObject("signalType").field("type", "long").endObject()
                            .startObject("holdDuration").field("type", "double").endObject()
                            .startObject("isImportance").field("type", "long").endObject()
                            .startObject("handleState").field("type", "long").endObject()
                            .startObject("reserve1").field("type", "text").field("index", "false").endObject()
                            .startObject("reserve2").field("type", "text").field("index", "false").endObject()
                            .startObject("reserve3").field("type", "text").field("index", "false").endObject()
                            .startObject("reserve4").field("type", "text").endObject()
                            .startObject("reserve5").field("type", "text").endObject()
                            .startObject("reserve6").field("type", "long").endObject()
                            .startObject("reserve7").field("type", "long").endObject()
                            .startObject("reserve8").field("type", "double").endObject()
                            .startObject("reserve9").field("type", "double").endObject()
                            .startObject("reserve10").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .startObject("reserve11").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .startObject("audioList").field("type", "nested")
                            .startObject("properties")
                            .startObject("audioLength").field("type", "double").endObject()
                            .startObject("audioPath").field("type", "text").field("index", "false").endObject()
                            .startObject("audioNumber").field("type", "text").field("index", "false").endObject()
                            .startObject("reserve1").field("type", "text").field("index", "false").endObject()
                            .startObject("reserve2").field("type", "text").field("index", "false").endObject()
                            .startObject("reserve3").field("type", "text").endObject()
                            .startObject("reserve4").field("type", "long").endObject()
                            .startObject("reserve5").field("type", "double").endObject()
                            .startObject("reserve6").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .startObject("engineList").field("type", "nested")
                            .startObject("properties")
                            .field("confirmEndTime").startObject().field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .field("confirmTime").startObject().field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .field("engineStatus").startObject().field("type", "long").endObject()
                            .field("engineName").startObject().field("type", "text").field("index", "false").endObject()
                            .startObject("reserve1").field("type", "text").field("index", "false").endObject()
                            .startObject("reserve2").field("type", "text").endObject()
                            .startObject("reserve3").field("type", "long").endObject()
                            .startObject("reserve4").field("type", "double").endObject()
                            .startObject("reserve5").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                            .startObject("engineResult").field("type", "nested")
                            .startObject("properties")
                            .field("duration").startObject().field("type", "double").endObject()
                            .field("result").startObject().field("index", "false").field("type", "text").endObject()
                            .field("judgeState").startObject().field("type", "long").endObject()
                            .field("serialNumber").startObject().field("type", "long").endObject()
                            .field("confidence").startObject().field("type", "double").endObject()
                            .field("remark").startObject().field("index", "false").field("type", "text").endObject()
                            .field("startTime").startObject().field("type", "double").endObject()
                            .startObject("reserve1").field("type", "text").endObject()
                            .startObject("reserve2").field("type", "long").endObject()
                            .startObject("reserve3").field("type", "double").endObject()
                            .endObject()
                            .endObject()
                            .endObject()
                            .endObject()
                            .endObject()
                            .endObject()
                            .endObject()
                            .endObject();
            System.out.println(builder.string());
            PutMappingRequest mappingRequest = Requests.putMappingRequest(index).source(builder).type(type);

            System.out.println(mappingRequest.toString());
            client.admin().indices().putMapping(mappingRequest).actionGet();
            System.out.println(index + "." + type + " 格式化成功");
        } catch (ElasticsearchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建 语音相关  es Mapping
     */
    public static boolean createMapper(String index, String type) throws IOException {
        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("serialNumber").field("type", "keyword").endObject()
                .startObject("audioPath").field("type", "keyword").endObject()

                //voiceInfo
                .startObject("voiceInfo")
                .startObject("properties")
                .startObject("audioSize").field("type", "long").endObject()
                .startObject("callStartTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("callEndTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("holdDuration").field("type", "long").endObject()
                .startObject("customerNumber").field("type", "keyword").endObject()
                .startObject("seatNumber").field("type", "keyword").endObject()
                .startObject("callDirection").field("type", "long").endObject()
                .startObject("groupId").field("type", "keyword").endObject()
                .startObject("seatGroup").field("type", "keyword").endObject()
                .startObject("seatId").field("type", "keyword").endObject()
                .startObject("seatName").field("type", "keyword").endObject()
                .startObject("proPhoneNum").field("type", "keyword").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("province").field("type", "keyword").endObject()
                .startObject("isEachRecord").field("type", "keyword").endObject()
                .startObject("onHook").field("type", "keyword").endObject()
                .startObject("callerloc").field("type", "keyword").endObject()
                .startObject("customerStart").field("type", "keyword").endObject()
                .startObject("satisfaction").field("type", "keyword").endObject()
                .startObject("dissatisfactionMsg").field("type", "keyword").endObject()
                .startObject("reCallFlag").field("type", "integer").endObject()
                .startObject("workNumber").field("type", "keyword").endObject()
                .startObject("sendMsg").field("type", "integer").endObject()
                .startObject("message").field("type", "keyword").endObject()
                .startObject("proStatus").field("type", "integer").endObject()
                .endObject()
                .endObject()

                //sttInfo
                .startObject("sttInfo")
                .startObject("properties")
                .startObject("sttStartTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("sttEndTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()

                //interrupted
                .startObject("interrupted").field("type", "nested")
                .startObject("properties")
                .startObject("start").field("type", "double").endObject()
                .startObject("end").field("type", "double").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .endObject()
                .endObject()

                //silences
                .startObject("silences").field("type", "nested")
                .startObject("properties")
                .startObject("start").field("type", "double").endObject()
                .startObject("end").field("type", "double").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .endObject()
                .endObject()

                //sttSentences
                .startObject("sttSentences").field("type", "nested")
                .startObject("properties")
                .startObject("channel").field("type", "integer").endObject()
                .startObject("start").field("type", "double").endObject()
                .startObject("end").field("type", "double").endObject()
                .startObject("centent").field("type", "keyword").endObject()
                .startObject("emotion").field("type", "integer").endObject()
                .startObject("speed").field("type", "double").endObject()
                .startObject("energy").field("type", "integer").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("keywords").field("type", "nested")
                .startObject("properties")
                .startObject("keywordStart").field("type", "double").endObject()
                .startObject("keywordEnd").field("type", "double").endObject()
                .startObject("keyword").field("type", "keyword").endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                //businessTags
                .startObject("businessTags").field("type", "nested")
                .startObject("properties")
                .startObject("businessId").field("type", "long").endObject()
                .startObject("business").field("type", "keyword").endObject()
                .startObject("proType").field("type", "integer").endObject()
                .startObject("proValue").field("type", "integer").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .endObject()
                .endObject()

                //ruleInfos
                .startObject("ruleInfos").field("type", "nested")
                .startObject("properties")
                .startObject("ruleId").field("type", "long").endObject()
                .startObject("ruleName").field("type", "keyword").endObject()
                .startObject("startTime").field("type", "double").endObject()
                .startObject("endTime").field("type", "double").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .endObject()
                .endObject()

                //workScores
                .startObject("workScores").field("type", "nested")
                .startObject("properties")
                .startObject("workId").field("type", "long").endObject()
                .startObject("workName").field("type", "keyword").endObject()
                .startObject("preQuality").field("type", "integer").endObject()
                .startObject("proStatus").field("type", "integer").endObject()
                .startObject("workScore").field("type", "integer").endObject()
                .startObject("scoreNote").field("type", "keyword").endObject()
                .startObject("proUserId").field("type", "long").endObject()
                .startObject("modelRules").field("type", "nested")
                .startObject("properties")
                .startObject("modelId").field("type", "long").endObject()
                .startObject("modelName").field("type", "keyword").endObject()
                .startObject("ruleId").field("type", "long").endObject()
                .startObject("preQuality").field("type", "integer").endObject()
                .startObject("ruleName").field("type", "keyword").endObject()
                .startObject("fateful").field("type", "integer").endObject()
                .startObject("ruleTimes").field("type", "integer").endObject()
                .startObject("ruleScore").field("type", "integer").endObject()
                .startObject("ruleDetails").field("type", "nested")
                .startObject("properties")
                .startObject("startTime").field("type", "double").endObject()
                .startObject("endTime").field("type", "double").endObject()
                .startObject("ruleScore").field("type", "integer").endObject()
                .startObject("matchStatus").field("type", "integer").endObject()
                .startObject("proStatus").field("type", "integer").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("appealNote").field("type", "keyword").endObject()
                .startObject("appealTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("appealUserId").field("type", "long").endObject()
                .startObject("proNote").field("type", "keyword").endObject()
                .startObject("proTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("proUserId").field("type", "long").endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()

                //wordFrequency
                .startObject("wordFrequency").field("type", "nested")
                .startObject("properties")
                .startObject("keyword").field("type", "keyword").endObject()
                .startObject("exists").field("type", "integer").endObject()
                .startObject("count").field("type", "integer").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .endObject()
                .endObject()

                //testInfos
                .startObject("testInfos").field("type", "nested")
                .startObject("properties")
                .startObject("workId").field("type", "long").endObject()
                .startObject("workName").field("type", "keyword").endObject()
                .startObject("workScore").field("type", "integer").endObject()
                .startObject("testModelRules").field("type", "nested")
                .startObject("properties")
                .startObject("modelId").field("type", "long").endObject()
                .startObject("modelName").field("type", "keyword").endObject()
                .startObject("ruleId").field("type", "long").endObject()
                .startObject("ruleName").field("type", "keyword").endObject()
                .startObject("startTime").field("type", "double").endObject()
                .startObject("endTime").field("type", "double").endObject()
                .startObject("ruleScore").field("type", "integer").endObject()
                .startObject("matchStatus").field("type", "integer").endObject()
                .startObject("inputTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("proNote").field("type", "keyword").endObject()
                .startObject("proTime").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd").field("type", "date").endObject()
                .startObject("proUserId").field("type", "long").endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject();

        //index：索引名   type：类型名（可以自己定义）
        PutMappingRequest putmap = Requests.putMappingRequest(index).type(type).source(mapping);
        //为索引添加映射
        PutMappingResponse response  =  client.admin().indices().putMapping(putmap).actionGet();

        if(response.isAcknowledged()){
            return true;
        }else{
            return false;
        }
    }

}

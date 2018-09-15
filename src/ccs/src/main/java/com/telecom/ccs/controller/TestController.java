package com.telecom.ccs.controller;

import com.alibaba.fastjson.JSON;
import com.telecom.ccs.entities.BusinessTag;
import com.telecom.ccs.entities.CCS;
import com.telecom.ccs.entities.SttInfo;
import com.telecom.ccs.entities.VoiceInfo;
import com.telecom.ccs.utils.file.GenerateSessionId;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private ElasticsearchOperations esop;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = "/insertccs",method = RequestMethod.GET)
    public String   insert(){

        String sid =  GenerateSessionId.getSid();
        CCS ccs = new CCS();
        ccs.setSerialNumber(sid);
        ccs.setAudioPath("/home/zhangbo/elasticsearch/voidespace");

        VoiceInfo voiceInfo = new VoiceInfo();
        voiceInfo.setCallStartTime(new Date());
        voiceInfo.setHoldDuration(120);
        voiceInfo.setCustomerNumber("18811353790");
        voiceInfo.setSeatNo("telecom60001");
        voiceInfo.setInputTime(new Date());

        SttInfo sttInfo = new SttInfo();
        List<BusinessTag> businessTagList = new ArrayList<BusinessTag>();

        ccs.setVoiceInfo(voiceInfo);
        ccs.setSttInfo(sttInfo);
        ccs.setBusinessTags(businessTagList);



        System.out.println(ccs.toString());

       String json =  JSON.toJSONString(ccs);

       Client client = esop.getClient();
      IndexRequestBuilder builder =  client.prepareIndex("zhangbo","ccs",sid).setSource(json);

      IndexResponse response = builder.get();

      logger.info("after addData:  status : "+ response.status().getStatus());
      logger.info("after addData:  id : "+ response.getId());



       return "add success.";

    }

   @RequestMapping(value = "/searchccs",method = RequestMethod.GET)
    public String   select(){
        Client client = esop.getClient();

        SearchResponse response = client.prepareSearch("zhangbo")
                .setTypes("ccs")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("serialNumber","00001"))
                .execute()
                .actionGet();

        logger.info("after searchData:  status : "+ response.status().getStatus());
        logger.info("after searchData:  id : "+ response.getHits());
        //SearchHits sh = response.getHits();

        SearchHits hits = response.getHits();
        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.print("主键值：" + hits.getAt(i).getId() + "—>");
            System.out.println("audioPath: "+ hits.getAt(i).getSource().get("audioPath"));
        }




       String value = getkeyvalue("18811353790");

        return "ok"+ value;
    }


    public   String getkeyvalue(String key){

        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        System.out.println("redis info: get key:"+key+" --> value:"+value);
        return value;

    }
}

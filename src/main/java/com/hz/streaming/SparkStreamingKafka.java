package com.hz.streaming;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.hz.streaming.config.ConfigAll;
import com.hz.streaming.message.Message;
import com.hz.streaming.utils.Constance;
import com.hz.streaming.utils.GenerateInstance;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkFiles;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SparkStreamingKafka {

    public static void main(String[] args) {
        ConfigAll.init();
        Map<String, String> map = ConfigAll.configMap;
        Map<String, Object> kafkaParams = new HashMap<String,Object>();
        kafkaParams.put("bootstrap.servers", map.get(Constance.APP_BOOTSTRAP_SERVER));//kafka地址
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");//key序列化
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");//value序列化
        kafkaParams.put("group.id", map.get(Constance.APP_GROUP_ID));//GroupId

        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        //sparkconf
        SparkConf conf=new SparkConf();
        conf.setAppName(Constance.APP_NAME);
        conf.setMaster("local[2]");


        long duration = Long.parseLong(map.get(Constance.APP_DURATION));
        JavaStreamingContext jssc=new JavaStreamingContext(conf, Durations.seconds(duration));
        //checkpoint地址
        String directory=map.get(Constance.APP_CHECKPOINT);
        //topics
        String[] topics=map.get(Constance.APP_TOPICS).split(",");
        Collection<String> topic = Arrays.asList(topics);


        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        jssc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topic, kafkaParams)
                );

       // jssc.checkpoint(directory);//做checkpoint


        //具体处理逻辑
        Message msg=(Message) GenerateInstance.getInstance(map.get(Constance.APP_MESSAGE_CLASS));
        msg.setConfig(map);
        msg.exec(stream);



        jssc.start();
        try {
            jssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




}

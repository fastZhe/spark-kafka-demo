package com.hz.streaming.message.messageImpl;

import com.hz.streaming.message.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Map;

public class MessageWordCount implements Message{
    @Override
    public void setConfig(Map<String, String> config) {

    }

    @Override
    public void exec(Object obj) {
        JavaInputDStream<ConsumerRecord<String, String>> stream = (JavaInputDStream<ConsumerRecord<String, String>>) obj;
        stream.map(rdd->rdd.value()).flatMap(rdd-> (Arrays.asList(rdd.split(" ")).iterator()))
                .mapToPair(rdd->new Tuple2<>(rdd,1)).reduceByKey((r1,r2)->(r1+r2)).print();

    }
}

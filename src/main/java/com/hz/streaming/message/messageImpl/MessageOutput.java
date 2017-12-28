package com.hz.streaming.message.messageImpl;

import com.hz.streaming.config.ConfigAll;
import com.hz.streaming.message.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import scala.Tuple2;

import java.util.Map;

public class MessageOutput implements Message {

    private Map<String,String> config;


    @Override
    public void setConfig(Map<String, String> config) {
        this.config=config;
    }

    public void exec(Object obj) {
        JavaInputDStream<ConsumerRecord<String, String>> stream = (JavaInputDStream<ConsumerRecord<String, String>>) obj;
        stream.map(line->line.value()).foreachRDD(rdd-> rdd.foreach(rd-> System.out.println("========"+rd)));
    }

}

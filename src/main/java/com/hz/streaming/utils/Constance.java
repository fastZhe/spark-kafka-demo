package com.hz.streaming.utils;

public class Constance {
    //应用名称
    public final static String APP_NAME="kafka-spark";
    //拉取数据间隔时间
    public final static String APP_DURATION="duration";
    //spark checkpoint地址
    public final static String APP_CHECKPOINT="/kafka-spark-example";
    //kafka的server
    public final static String APP_BOOTSTRAP_SERVER="bootstrap.servers";
    //kafka主题
    public final static String APP_TOPICS="topic";
    //kafka消费group
    public final static String APP_GROUP_ID="group.id";
    //saprk处理消息的具体业务类
    public final static String APP_MESSAGE_CLASS="message.class";


}

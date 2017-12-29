### spark-kafka整合案例
* com.hz.streaming.message.Message 是处理消息的超类
* com.hz.streaming.SparkStreamingKafka 主函数



    yarn 提交语句：
    
    spark-submit \
    --class com.hz.streaming.SparkStreamingKafka \
    --master yarn \
    --deploy-mode cluster \
    --executor-memory 1G \
    --num-executors 1 \
    --files /opt/app/config.properties \
    /opt/app/target/kafka_Spark-1.0-SNAPSHOT.jar
    /opt/app/config.properties
    ----------------------------------------------
    standlone 提交语句
    
    spark-submit \
    --class com.hz.streaming.SparkStreamingKafka \
    --master spark://hz:7077 \
    --executor-memory 1G \
    --num-executors 1 \
    /opt/app/target/kafka_Spark-1.0-SNAPSHOT.jar
    
1.  --files 附加的配置文件等
2.  最后为jar包 以及相关参数（主函数的参数）



### config.properties配置
    duration=15   //时间窗口、间隔
    bootstrap.servers=10.211.55.5:9092    //kafka 地址
    topic=test      //消费主题
    group.id=kafka  //消费组
    message.class=com.hz.streaming.message.messageImpl.MessageOutput //处理消息执行类

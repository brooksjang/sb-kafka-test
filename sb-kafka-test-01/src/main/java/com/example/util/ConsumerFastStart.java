package com.example.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerFastStart {
    private static final String brokerList = "http://192.168.126.135:9092";
    private static final String groupId = "group.demo";
    private static final String topic = "brooks";

    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置key序列化器
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //设置值序列化器
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //设置集群地址
        properties.put("bootstrap.servers", brokerList);
        //设置消费组
        properties.put("group.id", groupId);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singletonList(topic));

        while (true){
            //一秒监听1次
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord<String, String> record : records){
                System.out.println(record.value());
            }
        }
    }
}

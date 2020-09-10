package com.example.util;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProduceFastStart {
    private static final String brokerList = "http://192.168.126.135:9092";

    private static final String topic = "brooks";

    public static void main(String[] args) {
        //初始化kafka
        Properties properties = new Properties();
        //设置key序列化器
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);
        //设置值序列化器
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置集群地址
        properties.put("bootstrap.servers", brokerList);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "kafka-demo", "Hello kafka-Consumer! I am springboot");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}

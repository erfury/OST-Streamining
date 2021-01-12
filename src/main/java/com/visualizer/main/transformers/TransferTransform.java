package com.visualizer.main.transformers;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class TransferTransform {
    public static void main(String[] args) {

        StreamsBuilder builder=new StreamsBuilder();


        KStream<String, String> inputTopic = builder.stream("Trans_Topic");

                inputTopic
                       .filter((k,v)->{
                           return Long.parseLong(v.split(",")[6]) < 51L;
                       }).to("trans_topic_result_1");
        inputTopic
                .filter((k,v)->{
                    return Long.parseLong(v.split(",")[6]) > 51L;
                }).to("trans_topic_result_2");



        KafkaStreams kafkaStreams=new KafkaStreams(builder.build(), getProperties());
        kafkaStreams.start();

    }

    private static Properties getProperties() {
        String bootstrapServers="127.0.0.1:9092";
        Properties properties=new Properties();
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG,"my-first-app");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        return properties;
    }
}

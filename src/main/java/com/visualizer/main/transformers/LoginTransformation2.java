package com.visualizer.main.transformers;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class LoginTransformation2 {
    public static void main(String[] args) {

        StreamsBuilder builder=new StreamsBuilder();

        KStream<String,String> inputTopic=builder.stream("login_history_input");
        KStream<String, String> filteredLoginHistory = inputTopic.filter(
                (k,v) ->{
                    try{
                        return v.split(",")[1].equalsIgnoreCase("233823840");
                    }catch (Exception e){
                        return false;
                    }

                }
        );

        filteredLoginHistory.foreach((k,v)->{
            System.out.println(v);
        });
        filteredLoginHistory.to("second_filtered_login_history_output");

        KafkaStreams kafkaStreams=new KafkaStreams(builder.build(), getProperties());
        kafkaStreams.start();

    }

    private static Properties getProperties() {
        String bootstrapServers="127.0.0.1:9092";
        Properties properties=new Properties();
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG,"my-login-filter2-app");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        return properties;
    }
}
/*

 */
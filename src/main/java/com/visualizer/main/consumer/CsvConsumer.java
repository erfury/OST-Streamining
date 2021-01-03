package com.visualizer.main.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class CsvConsumer {
    public static void main(String[] args) {
        String bootstrapServers = "127.0.0.1:9092";
        String groupId="my-app";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String,String> consumer=new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singleton("test_output_filtered_3"));

        while (true){
            ConsumerRecords<String, String> record =consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord record1: record){

                //insert to cassandra using cassandrarepository of spring data or manually
                //first parse the record value split it by ","
                System.out.println(record1.value()+" : "+record1.key()+" : ");

            }
        }
    }
}

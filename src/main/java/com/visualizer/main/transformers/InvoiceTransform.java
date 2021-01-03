package com.visualizer.main.transformers;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

//Here we are taking data from "Invoice_Topic" and trying to do some data transformation
//After transformation we are sending it to "transformed_invoice"


public class InvoiceTransform {

    public static void main(String[] args) {

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> inputTopic = builder.stream("Invoice_Topic");

        KStream<String, String> filteredColumns = inputTopic.mapValues(value->{
            String[]splittedValue = value.split(",");
            String newValue = splittedValue[2] + "," + "," + splittedValue[6] + "," +"," + splittedValue[7] + "," + splittedValue[10] + "," + splittedValue[11];
            return newValue;
        });

        filteredColumns
                .filter((k,v)->{
                    return Long.parseLong(v.split(",")[2]) < 97L;
                }).to("invoice_topic_result_1");
        filteredColumns
                .filter((k,v)->{
                    return Long.parseLong(v.split(",")[2]) > 97L;
                }).to("invoice_topic_result_2");



//        neededColumns.foreach((k,v)->{
//            System.out.println(v);
//        });
//        neededColumns.to("transformed_invoice");

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

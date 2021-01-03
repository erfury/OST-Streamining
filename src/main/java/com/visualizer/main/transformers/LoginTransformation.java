package com.visualizer.main.transformers;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class LoginTransformation {

    public static void main(String[] args) {

        StreamsBuilder builder = new StreamsBuilder();

        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();
        KStream<String, String> inputTopic = builder.stream("login_history_input");
        KTable<String, Long> filtered = inputTopic
                .selectKey((k, v) ->
                        {
                            System.out.println("Inter result: key:" + k + "|| value:" + v.split(",")[1]);
                            return v.split(",")[1];
                        }
                )
                .groupByKey()
                .count();
        System.out.println("-------");
        filtered.toStream().foreach((k, v) -> System.out.println("Key value pair: " + k + "|" + v));
        System.out.println("-------");
        KStream<String, String> result = filtered.toStream().mapValues(v->""+v);
        result.to("filtered_login_history_output", Produced.with(stringSerde, stringSerde));

        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), getProperties());
        kafkaStreams.start();
    }

    private static Properties getProperties() {
        String bootstrapServers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "my-login-filter1-app");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return properties;
    }
}

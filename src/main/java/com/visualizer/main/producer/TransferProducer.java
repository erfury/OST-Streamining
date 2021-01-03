package com.visualizer.main.producer;

import com.opencsv.CSVReader;
import com.visualizer.main.model.Transfer_History;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileReader;
import java.util.*;

public class TransferProducer {

    public static void main(String[] args) throws Exception {
        System.out.println(readAll());
    }

    public static Properties getProperties() {
        String bootstrapServers="127.0.0.1:9092";
        Properties properties=new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }

    public static List<Transfer_History> readAll() throws Exception {

            CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Faizan\\Music\\Visualizer-master\\src\\main\\resources\\Transfer_Last.csv"));
            Properties properties = getProperties();

            List<Transfer_History> trns = new ArrayList<>();

            // read line by line
            String[] csvRecord = null;
            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
            while ((csvRecord = reader.readNext()) != null) {
                Transfer_History trn = new Transfer_History();
                trn.setId(csvRecord[0]);
                trn.setTransferId(csvRecord[1]);
                trn.setPartyId(csvRecord[2]);
                trn.setCurrency(csvRecord[3]);
                trn.setAmount(csvRecord[4]);
                trn.setValueDate(csvRecord[5]);
                trn.setCounts(csvRecord[6]);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("Trans_Topic", trn.toString());
                producer.send(record);
                producer.flush();
            }
            return trns;

    }
}
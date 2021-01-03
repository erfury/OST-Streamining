package com.visualizer.main.producer;
import com.opencsv.CSVReader;
import com.visualizer.main.model.LoginHistoryModel;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.io.FileReader;
import java.util.*;
public class LoginHistory_Producer {
    public static void main(String[] args) throws Exception {
        System.out.println(readAll());


    }


    private static Properties getProperties() {
        String bootstrapServers="127.0.0.1:9092";
        Properties properties=new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }

    public static List<LoginHistoryModel> readAll() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\User\\Desktop\\Visualizer-master\\src\\main\\resources\\customer_login_history.csv"));
        Properties properties = getProperties();

        List<LoginHistoryModel> logins = new ArrayList<>();

        // read line by line
        String[] csvRecord = null;
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        while ((csvRecord = reader.readNext()) != null) {
            LoginHistoryModel login = new LoginHistoryModel();
            login.setId(csvRecord[0]);
            login.setPartyId(csvRecord[1]);
            login.setDateTime(csvRecord[2]);
            ProducerRecord<String,String> record=new ProducerRecord<String,String>("login_history_input",csvRecord[1],login.toString());
            producer.send(record);
            producer.flush();
        }

        return logins;
    }
}

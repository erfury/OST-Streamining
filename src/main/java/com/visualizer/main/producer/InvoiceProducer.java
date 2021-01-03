package com.visualizer.main.producer;

import com.opencsv.CSVReader;
import com.visualizer.main.model.Invoice_history;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileReader;
import java.util.*;

//In this Invoice Producer, we are are taking the "invoice_history.csv" data from resources and sending
//it to "Invoice_Topic"

public class InvoiceProducer {

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

    public static List<Invoice_history> readAll() throws Exception {
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\mishr\\Music\\OST-Streamining\\src\\main\\resources\\invoice_history_data.csv"));
        Properties properties = getProperties();

        List<Invoice_history> invs = new ArrayList<>();

        // read line by line
        String[] csvRecord = null;
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        while ((csvRecord = reader.readNext()) != null) {
            Invoice_history inv = new Invoice_history();
            inv.setId(csvRecord[0]);
            inv.setInvoiceId(csvRecord[1]);
            inv.setPartyId(csvRecord[2]);
            inv.setCurrency(csvRecord[3]);
            inv.setTaxExclusiveAmount(csvRecord[4]);
            inv.setTaxInclusiveAmount(csvRecord[5]);
            inv.setPayableAmount(csvRecord[6]);
            inv.setIssueDate(csvRecord[7]);
            inv.setTaxPointDate(csvRecord[8]);
            inv.setDueDate(csvRecord[9]);
            inv.setIssueYear(csvRecord[10]);
            inv.setTotalInvoice(csvRecord[11]);

            ProducerRecord<String, String> record = new ProducerRecord<String, String>("Invoice_Topic", inv.toString());
            producer.send(record);
            producer.flush();
        }

        return invs;
    }

}

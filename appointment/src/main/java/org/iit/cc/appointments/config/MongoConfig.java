package org.iit.cc.appointments.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb+srv://dhananjana20240366:nGfImOQdee3lXdY7@cloudcomputing.zkv5y.mongodb.net/?retryWrites=true&w=majority&appName=CloudComputing"), "Appointments");
    }

}

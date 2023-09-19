package com.mongo.quote.persistence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.mongo.quote.persistence.repository")
public class MongoDBConfig {

}

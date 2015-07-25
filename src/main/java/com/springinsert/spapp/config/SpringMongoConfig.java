package com.springinsert.spapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
 
import com.mongodb.MongoClient;
 
/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
public class SpringMongoConfig{
 
	/**
	 * This method creates a instance for using MongoDB CRUD operations.
	 */
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
 
		MongoTemplate mongoTemplate = 
		    new MongoTemplate(new MongoClient("localhost"),"person");
		return mongoTemplate;
 
	}
 
}
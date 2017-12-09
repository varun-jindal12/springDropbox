package com.dropbox.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoDBconfig {
    public @Bean MongoDbFactory mongoDbFactory(){
      return new SimpleMongoDbFactory(new MongoClient(),"admin");
  }
  /*public @Bean MongoClient mongoClient(){
       return new MongoClient("localhost");
  }*/

}

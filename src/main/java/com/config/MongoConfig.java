package com.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.*.repository")
class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongoDB.databaseName}")
    private String databaseName;

    @Override
    public Mongo mongo() throws Exception {
        if(System.getenv("OPENSHIFT_MONGODB_DB_URL")!=null){
            return new MongoClient(new MongoClientURI(System.getenv("OPENSHIFT_MONGODB_DB_URL")));
        }else{
            return new MongoClient();
        }

    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.*.repository";
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }
}

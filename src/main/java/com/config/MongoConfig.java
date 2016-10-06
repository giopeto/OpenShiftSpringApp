package com.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
//@EnableMongoRepositories(basePackages = "com")
@EnableMongoRepositories(basePackages="com.groups.repository")
class MongoConfig extends AbstractMongoConfiguration {
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}

	@Override
	protected String getDatabaseName() {
		return "springdata";
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.groups.repository";
	}
}

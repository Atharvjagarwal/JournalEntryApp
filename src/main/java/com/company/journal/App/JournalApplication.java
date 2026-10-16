package com.company.journal.App;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public CommandLineRunner diagnoseMongo(MongoTemplate mongoTemplate, Environment env) {
		return args -> {
			System.out.println("================================================");
			System.out.println("1. ACTIVE DATABASE IN MONGO TEMPLATE : " + mongoTemplate.getDb().getName());
			System.out.println("2. RESOLVED 'spring.data.mongodb.uri': " + env.getProperty("spring.data.mongodb.uri"));
			System.out.println("3. RESOLVED 'spring.data.mongodb.database': " + env.getProperty("spring.data.mongodb.database"));
			System.out.println("================================================");
		};
	}

}

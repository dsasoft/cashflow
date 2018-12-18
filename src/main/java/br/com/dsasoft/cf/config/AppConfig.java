package br.com.dsasoft.cf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import br.com.dsasoft.cf.db.AccountRepository;

// TODO WebMvcConfigurerAdapter
@Configuration
@EnableMongoRepositories(basePackageClasses = { AccountRepository.class })
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

	@Value("${app.version}")
	private String appVersion;

	@Value("${mongodb.user}")
	private String mongoUser;

	@Value("${mongodb.pass}")
	private String mongoPass;

	@Value("${mongodb.url}")
	private String url;

	@Value("${mongodb.database}")
	private String database;

	public String getAppVersion() {
		return appVersion;
	}

	public String getMongoUser() {
		return mongoUser;
	}

	public String getMongoPass() {
		return mongoPass;
	}

	public String getUrl() {
		return url;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public MongoDatabase mongoDB() {
		MongoClient mongoClient = new MongoClient(new ServerAddress(
				"mongodb+srv://" + this.getMongoUser() + ":" + this.getMongoPass() + ":@" + this.getUrl()));

		MongoDatabase database = mongoClient.getDatabase(this.database);

		mongoClient.close();

		return database;
	}
}

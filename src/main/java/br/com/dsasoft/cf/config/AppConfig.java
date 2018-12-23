package br.com.dsasoft.cf.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import br.com.dsasoft.cf.db.AccountRepository;
import br.com.dsasoft.cf.db.CostCenterRepository;

// TODO WebMvcConfigurerAdapter
@Configuration
@EnableMongoRepositories(basePackageClasses = { AccountRepository.class,
		CostCenterRepository.class })
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

	@Value("${app.name}")
	private String appName;

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

	public String getAppName() {
		return appName;
	}

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

	public String getDatabase() {
		return database;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

//	@Bean
	public MongoClient mongoClient2() {

		MongoClient mongoClient = MongoClients.create("mongodb://" + this.getMongoUser() + ":"
				+ this.getMongoPass() + "@" + this.getUrl() + "/?authSource=cashflow");

		return mongoClient;
	}

	public MongoClient mongoClient3() {

		char[] password = getMongoPass().toCharArray();

		MongoCredential credential = MongoCredential.createCredential(getMongoUser(),
				getDatabase(), password);

		MongoClientOptions.Builder opt = MongoClientOptions.builder();
		opt.connectTimeout(Integer.MAX_VALUE);
		opt.serverSelectionTimeout(Integer.MAX_VALUE);

		MongoClient mongoClient = MongoClients
				.create(MongoClientSettings.builder().applicationName(getAppName())
						.applyToClusterSettings(builder -> builder
								.hosts(Arrays.asList(new ServerAddress(getUrl(), 27017)))
								.serverSelectionTimeout(600000l, TimeUnit.MILLISECONDS))
						.credential(credential)

						.build());
		return mongoClient;
	}

//	@Bean(name = "mongoTemplate")
	public MongoTemplate getMongoTemplate() {
		return new MongoTemplate(mongoClient3(), getDatabase());

	}
}

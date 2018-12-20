package br.com.dsasoft.cf.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

// TODO WebMvcConfigurerAdapter
@Configuration
//@EnableMongoRepositories(basePackageClasses = { AccountRepository.class })
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

	public String getDatabase() {
		return database;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

//	public MongoClient mongoClient() {
////		MongoClient mongoClient = new MongoClient(new ServerAddress(
////	"mongodb+srv://kay:myRealPassword@cluster0.mongodb.net/admin");
//
//		MongoClientURI uri = new MongoClientURI(
//				"mongodb://" + this.getMongoUser() + ":" + this.getMongoPass() + "@" + this.getUrl() + "/cashflow");
//
//		System.out.println("\n\n\n*****************************\n\n");
//		System.out.println(" >>>>>>> " + uri);
//		System.out.println("\n\n\n*****************************\n\n");
//
//		MongoClient mongoClient = new MongoClient(uri);
////		MongoDatabase database = mongoClient.getDatabase("test");
//
//		return mongoClient;
//	}

	@Bean
	public MongoClient mongoClient2() {

		MongoClient mongoClient = MongoClients.create("mongodb://" + this.getMongoUser() + ":" + this.getMongoPass()
				+ "@" + this.getUrl() + "/?authSource=cashflow");

		return mongoClient;
	}

	public MongoClient mongoClient3() {

		char[] password = getMongoPass().toCharArray();

		MongoCredential credential = MongoCredential.createCredential(getMongoUser(), getDatabase(), password);

		MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
				.applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(getUrl(), 27017))))
				.credential(credential)

				.build());

		return mongoClient;
	}

	@Bean(name = "mongoTemplate")
	public MongoTemplate getMongoTemplate() {
		return new MongoTemplate(mongoClient3(), getDatabase());

	}
}

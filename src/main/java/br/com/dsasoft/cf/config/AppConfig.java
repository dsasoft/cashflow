package br.com.dsasoft.cf.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

// TODO WebMvcConfigurerAdapter
@Configuration
//@EnableMongoRepositories(basePackageClasses = { AccountRepository.class,
//		CostCenterRepository.class })
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

	@Value("${mongodb.cluster.url0}")
	private String cluster0Url;

	@Value("${mongodb.cluster.url0}")
	private String cluster1Url;

	@Value("${mongodb.cluster.url0}")
	private String cluster2Url;

	@Value("${mongodb.cluster.port}")
	private Integer port;

	@Value("${mongodb.cluster.replicaSet}")
	private String replicaSetName;

	@Value("${mongodb.cluster.authSource}")
	private String authSource;

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

	public String getCluster0Url() {
		return cluster0Url;
	}

	public String getCluster1Url() {
		return cluster1Url;
	}

	public String getCluster2Url() {
		return cluster2Url;
	}

	public String getReplicaSetName() {
		return replicaSetName;
	}

	public String getAuthSource() {
		return authSource;
	}

	public Integer getPort() {
		return port;
	}

	public String getDatabase() {
		return database;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public MongoClient mongoClient() {
		return MongoClients.create(new ConnectionString("mongodb://" + getMongoUser() + ":"
				+ getMongoPass() + "@" + getCluster0Url() + ":" + getPort() + ","
				+ getCluster1Url() + ":" + getPort() + "," + getCluster2Url() + ":" + getPort()
				+ "/" + getDatabase() + "?ssl=true&replicaSet=" + getReplicaSetName()
				+ "&authSource=" + getAuthSource() + "&retryWrites=true"));
	}

	@Deprecated
	public MongoClient mongoClient3() {

		char[] password = getMongoPass().toCharArray();

		MongoCredential credential = MongoCredential.createCredential(getMongoUser(),
				getDatabase(), password);

		MongoClientOptions.Builder opt = MongoClientOptions.builder();
		opt.connectTimeout(Integer.MAX_VALUE);
		opt.serverSelectionTimeout(Integer.MAX_VALUE);

		MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
				.applicationName(getAppName())
				.applyToClusterSettings(builder -> builder
						.hosts(Arrays.asList(new ServerAddress(getCluster0Url(), getPort()),
								new ServerAddress(getCluster1Url(), getPort()),
								new ServerAddress(getCluster2Url(), getPort())))
						.requiredReplicaSetName("Cluster0-shard-0")

						.serverSelectionTimeout(600000l, TimeUnit.MILLISECONDS))
				.credential(credential)

				.build());
		return mongoClient;
	}

	@Bean(name = "mongoTemplate")
	public MongoTemplate getMongoTemplate() {
		return new MongoTemplate(mongoClient(), getDatabase());

	}
}

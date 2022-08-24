package sample;

import com.mongodb.ConnectionString;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainerProvider;

public class SampleTestcontainers {

	@TestConfiguration(proxyBeanMethods = false)
	public static class MongoDbConfiguration {

		@Bean
		MongoDBContainer mongoDbContainer() {
			MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:5.0.11");
			mongoDbContainer.start();
			return mongoDbContainer;
		}

		@Bean
		MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer(MongoDBContainer mongoDbContainer) {
			return clientSettingsBuilder -> clientSettingsBuilder
					.applyConnectionString(new ConnectionString(mongoDbContainer.getReplicaSetUrl()));
		}

	}

	@TestConfiguration(proxyBeanMethods = false)
	public static class PostgreSqlConfiguration {

		@Bean
		JdbcDatabaseContainer<?> postgresContainer() {
			JdbcDatabaseContainer<?> postgreSqlContainer = new PostgreSQLContainerProvider().newInstance("14.5-alpine");
			postgreSqlContainer.start();
			return postgreSqlContainer;
		}

		@Bean
		HikariDataSource dataSource(JdbcDatabaseContainer<?> databaseContainer) {
			HikariDataSource dataSource = new HikariDataSource();
			dataSource.setJdbcUrl(databaseContainer.getJdbcUrl());
			dataSource.setUsername(databaseContainer.getUsername());
			dataSource.setPassword(databaseContainer.getPassword());
			return dataSource;
		}

	}

	@TestConfiguration(proxyBeanMethods = false)
	public static class RedisConfiguration {

		@Bean
		GenericContainer<?> redisContainer() {
			GenericContainer<?> redisContainer = new GenericContainer<>("redis:7.0.4-alpine").withExposedPorts(6379);
			redisContainer.start();
			return redisContainer;
		}

		@Bean
		LettuceConnectionFactory redisConnectionFactory(GenericContainer<?> redisContainer) {
			return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisContainer.getHost(),
					redisContainer.getFirstMappedPort()));
		}

	}

}

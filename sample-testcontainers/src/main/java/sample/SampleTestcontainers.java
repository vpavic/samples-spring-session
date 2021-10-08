package sample;

import com.mongodb.ConnectionString;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public class SampleTestcontainers {

	@TestConfiguration(proxyBeanMethods = false)
	public static class MongoDbConfiguration {

		@Bean
		MongoDBContainer mongoDbContainer() {
			MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.4.6");
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
		PostgreSQLContainer<?> postgresContainer() {
			PostgreSQLContainer<?> postgreSqlContainer = new PostgreSQLContainer<>("postgres:13.3-alpine");
			postgreSqlContainer.start();
			return postgreSqlContainer;
		}

		@Bean
		HikariDataSource dataSource(PostgreSQLContainer<?> postgresContainer) {
			HikariDataSource dataSource = new HikariDataSource();
			dataSource.setJdbcUrl(postgresContainer.getJdbcUrl());
			dataSource.setUsername(postgresContainer.getUsername());
			dataSource.setPassword(postgresContainer.getPassword());
			return dataSource;
		}

	}

	@TestConfiguration(proxyBeanMethods = false)
	public static class RedisConfiguration {

		@Bean
		GenericContainer<?> redisContainer() {
			GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.4-alpine")
					.withExposedPorts(6379);
			redisContainer.start();
			return redisContainer;
		}

		@Bean
		LettuceConnectionFactory redisConnectionFactory(GenericContainer<?> redisContainer) {
			return new LettuceConnectionFactory(new RedisStandaloneConfiguration(
					redisContainer.getContainerIpAddress(), redisContainer.getFirstMappedPort()));
		}

	}

}

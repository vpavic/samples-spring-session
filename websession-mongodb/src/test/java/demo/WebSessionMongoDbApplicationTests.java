package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSessionMongoDbApplicationTests {

	@Test
	public void contextLoads() {
	}

	@TestConfiguration
	static class Config {

		@Bean
		public GenericContainer mongoContainer() {
			GenericContainer mongoContainer = new GenericContainer("mongo:4.0.0").withExposedPorts(27017);
			mongoContainer.start();
			return mongoContainer;
		}

		@Bean
		public com.mongodb.MongoClient mongo() {
			return new com.mongodb.MongoClient(mongoContainer().getContainerIpAddress(),
					mongoContainer().getFirstMappedPort());
		}

		@Bean
		public com.mongodb.reactivestreams.client.MongoClient reactiveStreamsMongoClient() {
			return com.mongodb.reactivestreams.client.MongoClients.create("mongodb://"
					+ mongoContainer().getContainerIpAddress() + ":" + mongoContainer().getFirstMappedPort());
		}

	}

}

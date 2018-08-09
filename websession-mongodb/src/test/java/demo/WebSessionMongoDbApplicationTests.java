package demo;

import com.mongodb.MongoClient;
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
		public MongoClient mongo() {
			return new MongoClient(mongoContainer().getContainerIpAddress(), mongoContainer().getFirstMappedPort());
		}

	}

}

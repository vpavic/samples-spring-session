package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
@Testcontainers
class SampleHttpSessionMongoDbApplicationTests {

	@Container
	static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.4.6");

	@DynamicPropertySource
	static void mongoDbProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
	}

	@Test
	void contextLoads() {
	}

}

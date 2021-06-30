package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.session.data.mongo.ReactiveMongoSessionRepository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class SampleWebSessionMongoDbApplicationTests {

	@Container
	private static final MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.4.6");

	@DynamicPropertySource
	static void mongoDbProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
	}

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context.getBeansOfType(ReactiveMongoSessionRepository.class)).isNotNull();
	}

}

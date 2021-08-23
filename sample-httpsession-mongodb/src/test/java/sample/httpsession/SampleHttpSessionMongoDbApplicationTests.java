package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.session.data.mongo.MongoIndexedSessionRepository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class SampleHttpSessionMongoDbApplicationTests {

	@Container
	private static final MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.4.6");

	@DynamicPropertySource
	static void mongoDbProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
	}

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context.getBeansOfType(MongoIndexedSessionRepository.class)).hasSize(1);
		assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
	}

}

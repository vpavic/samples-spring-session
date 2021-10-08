package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.mongo.MongoIndexedSessionRepository;

import sample.SampleTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SampleTestcontainers.MongoDbConfiguration.class)
class SampleHttpSessionMongoDbApplicationTests {

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(MongoIndexedSessionRepository.class)).hasSize(1);
	}

	@Test
	void sessionsEndpointIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
	}

}

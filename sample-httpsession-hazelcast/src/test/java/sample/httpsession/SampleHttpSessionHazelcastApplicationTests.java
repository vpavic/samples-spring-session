package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.session.hazelcast.HazelcastIndexedSessionRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SampleHttpSessionHazelcastApplicationTests {

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(HazelcastIndexedSessionRepository.class)).hasSize(1);
	}

	@Test
	void sessionsEndpointIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
	}

}

package sample.httpsession;

import org.infinispan.spring.embedded.session.InfinispanEmbeddedSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SampleHttpSessionInfinispanApplicationTests {

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(InfinispanEmbeddedSessionRepository.class)).hasSize(1);
	}

	@Test
	void sessionsEndpointIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
	}

}

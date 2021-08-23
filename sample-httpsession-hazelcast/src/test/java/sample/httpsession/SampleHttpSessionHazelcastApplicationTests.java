package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.session.hazelcast.Hazelcast4IndexedSessionRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SampleHttpSessionHazelcastApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context.getBeansOfType(Hazelcast4IndexedSessionRepository.class)).hasSize(1);
		assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
	}

}

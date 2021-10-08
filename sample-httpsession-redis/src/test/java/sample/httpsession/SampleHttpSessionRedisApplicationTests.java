package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;

import sample.SampleTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SampleTestcontainers.RedisConfiguration.class)
class SampleHttpSessionRedisApplicationTests {

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(RedisIndexedSessionRepository.class)).hasSize(1);
	}

	@Test
	void sessionsEndpointIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
	}

}

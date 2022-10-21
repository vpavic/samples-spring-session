package sample.httpsession;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.RedisSessionRepository;

import sample.SampleTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Import(SampleTestcontainers.RedisConfiguration.class)
class SampleHttpSessionRedisApplicationTests {

	@Nested
	@SpringBootTest
	class DefaultRepository {

		@Test
		void sessionRepositoryIsRegistered(ApplicationContext context) {
			assertThat(context.getBeansOfType(RedisSessionRepository.class)).hasSize(1);
		}

		@Test
		void sessionsEndpointIsRegistered(ApplicationContext context) {
			assertThat(context.getBeansOfType(SessionsEndpoint.class)).isEmpty();
		}

	}

	@Nested
	@SpringBootTest(properties = "spring.session.redis.repository-type=indexed")
	class IndexedRepository {

		@Test
		void sessionRepositoryIsRegistered(ApplicationContext context) {
			assertThat(context.getBeansOfType(RedisIndexedSessionRepository.class)).hasSize(1);
		}

		@Test
		void sessionsEndpointIsRegistered(ApplicationContext context) {
			assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
		}

	}

}

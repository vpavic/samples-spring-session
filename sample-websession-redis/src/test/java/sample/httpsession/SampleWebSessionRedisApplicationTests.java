package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.ReactiveRedisSessionRepository;

import sample.SampleTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SampleTestcontainers.RedisConfiguration.class)
class SampleWebSessionRedisApplicationTests {

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(ReactiveRedisSessionRepository.class)).hasSize(1);
	}

}

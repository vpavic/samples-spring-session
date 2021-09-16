package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.session.data.redis.ReactiveRedisSessionRepository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class SampleWebSessionRedisApplicationTests {

	@Container
	private static final GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.4-alpine")
			.withExposedPorts(6379);

	@DynamicPropertySource
	static void redisProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.redis.host", redisContainer::getContainerIpAddress);
		registry.add("spring.redis.port", redisContainer::getFirstMappedPort);
	}

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(ReactiveRedisSessionRepository.class)).hasSize(1);
	}

}

package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.session.SessionsEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;

import sample.SampleTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SampleTestcontainers.PostgreSqlConfiguration.class)
class SampleHttpSessionJdbcApplicationTests {

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(JdbcIndexedSessionRepository.class)).hasSize(1);
	}

	@Test
	void sessionsEndpointIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(SessionsEndpoint.class)).hasSize(1);
	}

}

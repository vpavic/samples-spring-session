package sample.httpsession;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SampleHttpSessionJdbcApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context.getBeansOfType(JdbcIndexedSessionRepository.class)).isNotNull();
	}

}

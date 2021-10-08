package sample.httpsession;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.mongo.ReactiveMongoSessionRepository;

import sample.SampleTestcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SampleTestcontainers.MongoDbConfiguration.class)
class SampleWebSessionMongoDbApplicationTests {

	@Test
	void sessionRepositoryIsRegistered(ApplicationContext context) {
		assertThat(context.getBeansOfType(ReactiveMongoSessionRepository.class)).hasSize(1);
	}

}

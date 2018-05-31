package demo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = WebSessionRedisApplicationTests.Initializer.class)
public class WebSessionRedisApplicationTests {

	private static GenericContainer container = new GenericContainer("redis:4.0.9").withExposedPorts(6379);

	@BeforeClass
	public static void setUpClass() {
		container.start();
	}

	@AfterClass
	public static void tearDownClass() {
		container.stop();
	}

	@Test
	public void contextLoads() {
	}

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		@Override
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of("spring.redis.host=" + container.getContainerIpAddress(), "spring.redis.port=" +
					container.getFirstMappedPort()).applyTo(configurableApplicationContext.getEnvironment());
		}

	}

}

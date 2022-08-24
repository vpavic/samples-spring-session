package sample.httpsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class SampleHttpSessionJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleHttpSessionJdbcApplication.class, args);
	}

}

package sample.httpsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class SampleHttpSessionRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleHttpSessionRedisApplication.class, args);
	}

}

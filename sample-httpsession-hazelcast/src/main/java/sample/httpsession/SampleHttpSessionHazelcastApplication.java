package sample.httpsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class SampleHttpSessionHazelcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleHttpSessionHazelcastApplication.class, args);
	}

}

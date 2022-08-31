package sample.websession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class SampleWebSessionMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleWebSessionMongoDbApplication.class, args);
	}

}

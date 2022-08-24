package sample.httpsession;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.spring.embedded.session.configuration.EnableInfinispanEmbeddedHttpSession;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(proxyBeanMethods = false)
@EnableInfinispanEmbeddedHttpSession
public class SampleHttpSessionInfinispanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleHttpSessionInfinispanApplication.class, args);
	}

	@Bean
	InfinispanGlobalConfigurer infinispanGlobalConfigurer() {
		return () -> GlobalConfigurationBuilder.defaultClusteredBuilder().build();
	}

	@Bean
	InfinispanCacheConfigurer infinispanCacheConfigurer() {
		return manager -> manager.defineConfiguration("sessions", new ConfigurationBuilder().build());
	}

}

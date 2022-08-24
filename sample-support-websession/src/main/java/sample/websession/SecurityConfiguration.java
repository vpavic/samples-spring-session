package sample.websession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration(proxyBeanMethods = false)
class SecurityConfiguration {

	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		return httpSecurity
				.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
				.formLogin(withDefaults())
				.build();
	}

}

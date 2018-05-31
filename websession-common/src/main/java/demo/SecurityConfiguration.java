package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		// @formatter:off
		http
			.authorizeExchange()
				.anyExchange().authenticated()
				.and()
			.formLogin();
		// @formatter:on

		return http.build();
	}

}

package sample.httpsession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

	@Bean
	public SpringSessionRememberMeServices rememberMeServices() {
		return new SpringSessionRememberMeServices();
	}

	@Bean
	public SpringSessionBackedSessionRegistry<?> sessionRegistry(
			FindByIndexNameSessionRepository<?> sessionRepository) {
		return new SpringSessionBackedSessionRegistry<>(sessionRepository);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, RememberMeServices rememberMeServices,
			SessionRegistry sessionRegistry) throws Exception {
		return httpSecurity
				.authorizeRequests(requests -> requests.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.rememberMe(configurer -> configurer.rememberMeServices(rememberMeServices))
				.sessionManagement(configurer -> configurer.maximumSessions(1).sessionRegistry(sessionRegistry))
				.build();
	}

}

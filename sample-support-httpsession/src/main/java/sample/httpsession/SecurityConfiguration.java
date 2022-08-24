package sample.httpsession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration(proxyBeanMethods = false)
class SecurityConfiguration {

	@Bean
	SpringSessionRememberMeServices rememberMeServices() {
		return new SpringSessionRememberMeServices();
	}

	@Bean
	<S extends Session> SpringSessionBackedSessionRegistry<S> sessionRegistry(
			FindByIndexNameSessionRepository<S> sessionRepository) {
		return new SpringSessionBackedSessionRegistry<>(sessionRepository);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, RememberMeServices rememberMeServices,
			SessionRegistry sessionRegistry) throws Exception {
		return httpSecurity
				.authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
				.formLogin(withDefaults())
				.rememberMe(configurer -> configurer.rememberMeServices(rememberMeServices))
				.sessionManagement(configurer -> configurer.maximumSessions(1).sessionRegistry(sessionRegistry))
				.build();
	}

}

package sample.httpsession;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration(proxyBeanMethods = false)
class SecurityConfiguration<S extends Session> {

	@Bean
	SpringSessionRememberMeServices rememberMeServices() {
		return new SpringSessionRememberMeServices();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, RememberMeServices rememberMeServices,
			ObjectProvider<FindByIndexNameSessionRepository<S>> sessionRepositoryProvider) throws Exception {
		httpSecurity.authorizeHttpRequests(requests -> requests.anyRequest().permitAll());
		httpSecurity.formLogin(withDefaults());
		httpSecurity.logout(configurer -> configurer.logoutSuccessUrl("/"));
		httpSecurity.rememberMe(configurer -> configurer.rememberMeServices(rememberMeServices));
		FindByIndexNameSessionRepository<S> sessionRepository = sessionRepositoryProvider.getIfAvailable();
		if (sessionRepository != null) {
			httpSecurity.sessionManagement(configurer -> configurer.maximumSessions(1)
					.sessionRegistry(new SpringSessionBackedSessionRegistry<>(sessionRepository)));
		}
		return httpSecurity.build();
	}

}

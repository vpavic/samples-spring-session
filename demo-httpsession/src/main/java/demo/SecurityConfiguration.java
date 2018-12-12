package demo;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final RememberMeServices rememberMeServices;

	private final SessionRegistry sessionRegistry;

	public SecurityConfiguration(ObjectProvider<RememberMeServices> rememberMeServices,
			 ObjectProvider<SessionRegistry> sessionRegistry) {
		this.rememberMeServices = rememberMeServices.getObject();
		this.sessionRegistry = sessionRegistry.getObject();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.rememberMe()
				.rememberMeServices(this.rememberMeServices)
				.and()
			.sessionManagement()
				.maximumSessions(1)
				.sessionRegistry(this.sessionRegistry);
		// @formatter:on
	}

	@Configuration
	@ConditionalOnBean(SessionRepository.class)
	static class SpringSessionAvailableConfig<S extends Session> {

		private final FindByIndexNameSessionRepository<S> sessionRepository;

		SpringSessionAvailableConfig(ObjectProvider<FindByIndexNameSessionRepository<S>> sessionRepository) {
			this.sessionRepository = sessionRepository.getObject();
		}

		@Bean
		public SessionRegistry sessionRegistry() {
			return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
		}

		@Bean
		public RememberMeServices rememberMeServices() {
			return new SpringSessionRememberMeServices();
		}

	}

	@Configuration
	@ConditionalOnMissingBean(type = "org.springframework.session.SessionRepository")
	static class NoSpringSessionAvailableConfig {

		private final UserDetailsService userDetailsService;

		NoSpringSessionAvailableConfig(ObjectProvider<UserDetailsService> userDetailsService) {
			this.userDetailsService = userDetailsService.getObject();
		}

		@Bean
		public SessionRegistry sessionRegistry() {
			return new SessionRegistryImpl();
		}

		@Bean
		public RememberMeServices rememberMeServices() {
			return new TokenBasedRememberMeServices("secret", this.userDetailsService);
		}

	}

}

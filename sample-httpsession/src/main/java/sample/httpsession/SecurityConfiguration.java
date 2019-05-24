package sample.httpsession;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration
public class SecurityConfiguration<S extends Session> extends WebSecurityConfigurerAdapter {

	private final FindByIndexNameSessionRepository<S> sessionRepository;

	SecurityConfiguration(ObjectProvider<FindByIndexNameSessionRepository<S>> sessionRepository) {
		this.sessionRepository = sessionRepository.getObject();
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
				.rememberMeServices(rememberMeServices())
				.and()
			.sessionManagement()
				.maximumSessions(1)
				.sessionRegistry(sessionRegistry());
		// @formatter:on
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

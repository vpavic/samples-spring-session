package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

@Configuration
public class SecurityConfiguration<S extends Session> extends WebSecurityConfigurerAdapter {

	private final FindByIndexNameSessionRepository<S> sessionRepository;

	public SecurityConfiguration(FindByIndexNameSessionRepository<S> sessionRepository) {
		this.sessionRepository = sessionRepository;
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
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.and()
			.sessionManagement()
				.maximumSessions(1)
				.sessionRegistry(sessionRegistry());
		// @formatter:on
	}

	@Bean
	public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
	}

	@Bean
	public SpringSessionRememberMeServices rememberMeServices() {
		return new SpringSessionRememberMeServices();
	}

}

package demo;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(99)
@Configuration
public class H2ConsoleSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void init(WebSecurity web) {
		web.ignoring().requestMatchers(PathRequest.toH2Console());
	}

}

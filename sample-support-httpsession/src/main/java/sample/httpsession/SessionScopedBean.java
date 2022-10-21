package sample.httpsession;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
class SessionScopedBean implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(SessionScopedBean.class);

	@PostConstruct
	void init() {
		logger.info("sessionScopedBean: init");
	}

	@PreDestroy
	void close() {
		logger.info("sessionScopedBean: close");
	}

	void invoke() {
		logger.info("sessionScopedBean: invoke");
	}

}

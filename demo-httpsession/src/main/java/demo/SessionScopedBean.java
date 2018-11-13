package demo;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(SessionScopedBean.class);

	@PostConstruct
	public void init() {
		logger.info("sessionScopedBean: init");
	}

	@PreDestroy
	public void close() {
		logger.info("sessionScopedBean: close");
	}

	public String random() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 7);
	}

}

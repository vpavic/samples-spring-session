package sample.httpsession;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingHttpSessionListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory.getLogger(LoggingHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("sessionCreated: sessionId = {}", se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("sessionDestroyed: sessionId = {}", se.getSession().getId());
	}

}

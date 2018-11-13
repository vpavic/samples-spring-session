package demo;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingHttpSessionBindingListener implements HttpSessionBindingListener, Serializable {

	private static final Logger logger = LoggerFactory.getLogger(LoggingHttpSessionBindingListener.class);

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		logger.info("valueBound: sessionId = {}, name = {}, value = {}", event.getSession().getId(), event.getName(),
				event.getValue());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		logger.info("valueUnbound: sessionId = {}, name = {}, value = {}", event.getSession().getId(), event.getName(),
				event.getValue());
	}

}

package com.bpedroso.challenge.config.logging.mdc;

import static com.bpedroso.challenge.config.logging.LogField.MESSAGE_ID;
import static com.bpedroso.challenge.config.logging.LogField.METHOD;
import static com.bpedroso.challenge.config.logging.LogField.USER_AGENT;
import static com.bpedroso.challenge.config.logging.mdc.MDCRepository.addAllLogData;
import static com.bpedroso.challenge.config.logging.mdc.MDCRepository.removeAllLogData;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bpedroso.challenge.config.logging.LogField;

public class MDCFilter implements Filter {
	
	private static final String HEADER_USER_AGENT = "User-Agent";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		insertLogData(request);
		try {
			chain.doFilter(request, response);
		} finally {
			clearLogData();
		}
	}

	private void insertLogData(final ServletRequest request) {
		if (request instanceof HttpServletRequest && !currentRequestIsAsyncDispatcher((HttpServletRequest) request)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			final Map<LogField, String> logData = new EnumMap<>(LogField.class);
			logData.put(METHOD, httpServletRequest.getMethod());
			logData.put(USER_AGENT, httpServletRequest.getHeader(HEADER_USER_AGENT));
			logData.put(MESSAGE_ID, extract(httpServletRequest, MESSAGE_ID));

			addAllLogData(logData);
		}
	}

	private boolean currentRequestIsAsyncDispatcher(final HttpServletRequest httpServletRequest) {
		return httpServletRequest.getDispatcherType().equals(DispatcherType.ASYNC);
	}

	private String extract(final HttpServletRequest httpServletRequest, final LogField field) {
		return httpServletRequest.getHeader(field.key());
	}

	private void clearLogData() {
		removeAllLogData();
	}

}

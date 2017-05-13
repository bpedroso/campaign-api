package com.bpedroso.challenge.config.logging.mdc;

import java.util.Map;

import org.slf4j.MDC;

import com.bpedroso.challenge.config.logging.LogField;

public class MDCRepository {

	private MDCRepository() {}

	public static void addAllLogData(final Map<LogField, String> logData) {
		logData.entrySet().forEach(entry -> MDC.put(entry.getKey().key(), entry.getValue()));
	}

	public static void removeAllLogData() {
		for (LogField logField : LogField.values()) {
			MDC.remove(logField.key());
		}
	}
}

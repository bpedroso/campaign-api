package com.bpedroso.challenge.config.logging;

public enum LogField {

	MESSAGE_ID("messageId"), USER_AGENT("userAgent"), METHOD("method");

	private String key;

	LogField(final String key) {
		this.key = key;
	}

	public String key() {
		return key;
	}
}

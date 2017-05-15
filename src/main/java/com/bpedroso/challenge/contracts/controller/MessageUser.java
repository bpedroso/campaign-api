package com.bpedroso.challenge.contracts.controller;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uuid;
	private Instant instant;
	private User user;
	private String responseError;

	public MessageUser() {
	}

	public MessageUser(String uuid, Instant instant) {
		super();
		this.uuid = uuid;
		this.instant = instant;
	}
	
	public MessageUser(String uuid, Instant instant, String responseError) {
		super();
		this.uuid = uuid;
		this.instant = instant;
		this.responseError = responseError;
	}

	public MessageUser(String uuid, Instant instant, User user) {
		super();
		this.uuid = uuid;
		this.instant = instant;
		this.user = user;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getError() {
		return responseError;
	}
	
	public void setError(String responseError) {
		this.responseError = responseError;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return String.format("MessageContext [uuid=%s, instant=%s, user=%s, responseError=%s]", uuid, 
				instant, user, responseError);
	}

}

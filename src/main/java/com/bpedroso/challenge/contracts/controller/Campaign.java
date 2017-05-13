package com.bpedroso.challenge.contracts.controller;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Region("TeamPartner")
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Campaign {

	@Id
	@JsonProperty(value = "code", required = true)
	private Integer code;

	@JsonProperty(value = "name", required = true)
	private String name;

	@JsonProperty(value = "idTeam", required = true)
	private Integer idTeam;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonProperty(value = "beginDate", required = true)
	private LocalDate beginDate;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonProperty(value = "endDate", required = true)
	private LocalDate endDate;

	public Campaign() {
	}

	public Campaign(Integer code) {
		this.code = code;
	}

	@JsonCreator
	private Campaign(@JsonProperty(value = "code", required = true) Integer code,
			@JsonProperty(value = "name", required = true) String name,
			@JsonProperty(value = "idTeam", required = true) Integer idTeam,
			@JsonProperty(value = "beginDate", required = true) LocalDate beginDate,
			@JsonProperty(value = "endDate", required = true) LocalDate endDate) {
		this.code = code;
		this.name = name;
		this.idTeam = idTeam;
		this.endDate = endDate;
		this.beginDate = beginDate;
	}

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return String.format("Campaign [code=%s, name=%s, idTeam=%s, beginDate=%s, endDate=%s]", code, name, idTeam,
				beginDate, endDate);
	}

}

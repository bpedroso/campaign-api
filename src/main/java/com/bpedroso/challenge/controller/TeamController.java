package com.bpedroso.challenge.controller;

import static com.bpedroso.challenge.controller.constants.ControllerConstants.TEAM;
import static com.bpedroso.challenge.controller.constants.ControllerConstants.V1;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpedroso.challenge.contracts.controller.Team;
import com.bpedroso.challenge.usecases.ManageTeam;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = V1 + TEAM)
public class TeamController {

	@Autowired
	private ManageTeam useCaseIncludeTeam;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "messageId", value = "Message ID para rastreamento", required = false, dataType = "String", paramType = "header") })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Failure") })
	@PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public void insert(@RequestHeader(value = "messageId", required = false) String messageId,
			@RequestBody Team payLoad) {
		this.useCaseIncludeTeam.update(payLoad);
	}

	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "messageId", value = "Message ID para rastreamento", required = false, dataType = "String", paramType = "header") })
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public List<Team> get(@RequestHeader(value = "messageId", required = false) String messageId) {
		return this.useCaseIncludeTeam.findAll();
	}
}

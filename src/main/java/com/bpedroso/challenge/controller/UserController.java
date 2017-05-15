package com.bpedroso.challenge.controller;

import static com.bpedroso.challenge.controller.constants.ControllerConstants.USER;
import static com.bpedroso.challenge.controller.constants.ControllerConstants.V1;
import static java.time.Instant.now;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpedroso.challenge.contracts.controller.MessageCampaign;
import com.bpedroso.challenge.contracts.controller.MessageUser;
import com.bpedroso.challenge.contracts.controller.User;
import com.bpedroso.challenge.exceptions.NoContentException;
import com.bpedroso.challenge.usecases.IncludeUser;
import com.bpedroso.challenge.usecases.ListUser;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = V1 + USER)
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IncludeUser useCaseIncludeUser;
	
	@Autowired
	private ListUser useCaseListUser;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "messageId", value = "Message ID para rastreamento", required = false, dataType = "String", paramType = "header") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MessageCampaign.class),
			@ApiResponse(code = 204, message = "Success", response = MessageCampaign.class),
			@ApiResponse(code = 500, message = "Failure", response = MessageCampaign.class) })
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MessageUser> insert(
			@RequestHeader(value = "messageId", required = false) String messageId, @RequestBody User payLoad) {
		ResponseEntity<MessageUser> responseEntity;

		try {
			this.useCaseIncludeUser.update(payLoad);

			responseEntity = new ResponseEntity<MessageUser>(new MessageUser(messageId, now()), OK);
		} catch (Exception e) {
			LOGGER.error("Fail to response " + e.getMessage());
			responseEntity = new ResponseEntity<MessageUser>(new MessageUser(messageId, now(), e.getMessage()),
					INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "messageId", value = "Message ID para rastreamento", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "email", value = "Email do usuário", required = false, dataType = "int", paramType = "query")
		})
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = MessageCampaign.class),
			@ApiResponse(code = 204, message = "Success", response = MessageCampaign.class),
			@ApiResponse(code = 500, message = "Failure", response = MessageCampaign.class) 
			})
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MessageUser> list(
			@RequestHeader(value="messageId", required = false) String messageId, 
			@RequestParam(value = "email", required = false) String email) {
		ResponseEntity<MessageUser> responseEntity;
		try {
			final User user = this.useCaseListUser.listByEmail(email);

			if(user == null) {
				throw new NoContentException(NO_CONTENT.getReasonPhrase());
			}
			responseEntity = new ResponseEntity<MessageUser>(new MessageUser(messageId, now(), user), OK);
		} catch (NoContentException e) {
			LOGGER.warn(NO_CONTENT.getReasonPhrase());
			responseEntity = new ResponseEntity<MessageUser>(new MessageUser(messageId, now()), NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Fail to response " + e.getMessage());
			responseEntity = new ResponseEntity<MessageUser>(new MessageUser(messageId, now(), e.getMessage()), INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}

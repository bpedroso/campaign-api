package com.bpedroso.challenge.controller;

import static com.bpedroso.challenge.controller.constants.ControllerConstants.CAMPAIGN;
import static com.bpedroso.challenge.controller.constants.ControllerConstants.V1;
import static java.time.Instant.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpedroso.challenge.contracts.controller.Campaign;
import com.bpedroso.challenge.contracts.controller.MessageContext;
import com.bpedroso.challenge.exceptions.NoContentException;
import com.bpedroso.challenge.usecases.DeleteCampaign;
import com.bpedroso.challenge.usecases.IncludeCampaign;
import com.bpedroso.challenge.usecases.ListCampaign;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = V1 + CAMPAIGN)
public class CampaignController {

	private final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

	@Autowired
	private ListCampaign useCaseListCampaign;

	@Autowired
	private IncludeCampaign useCaseIncludeCampaign;

	@Autowired
	private DeleteCampaign useCaseDeleteCampaign;

	@ApiImplicitParams({
		@ApiImplicitParam(name = "messageId", value = "Message ID para rastreamento", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "code", value = "Código da campanha", required = false, dataType = "int", paramType = "query")
		})
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = MessageContext.class),
			@ApiResponse(code = 204, message = "Success", response = MessageContext.class),
			@ApiResponse(code = 500, message = "Failure", response = MessageContext.class) 
			})
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MessageContext> list(
			@RequestHeader(value="messageId", required = false) String messageId, 
			@RequestParam(value = "code", required = false) Integer code,
			@RequestParam(value = "idTeam", required = false) Integer idTeam) {
		ResponseEntity<MessageContext> responseEntity;
		try {
			final List<Campaign> campaigns;
			
			if(code != null) {
				campaigns = this.useCaseListCampaign.listByCode(code);
			} else if(idTeam != null) {				
				campaigns = this.useCaseListCampaign.listByTeam(idTeam);
			} else {				
				campaigns = this.useCaseListCampaign.list();
			}

			if(isEmpty(campaigns)) {
				throw new NoContentException(NO_CONTENT.getReasonPhrase());
			}
			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now(), campaigns), OK);
		} catch (NoContentException e) {
			LOGGER.warn(NO_CONTENT.getReasonPhrase());
			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now()), NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Fail to response " + e.getMessage());
			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now(), e.getMessage()), INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "messageId", value = "Message ID para rastreamento", required = false, dataType = "String", paramType = "header"),
		@ApiImplicitParam(name = "code", value = "Código da campanha", required = true, dataType = "int", paramType = "query")
		})
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = MessageContext.class),
			@ApiResponse(code = 500, message = "Failure", response = MessageContext.class) 
			})
	@DeleteMapping
	public ResponseEntity<MessageContext> delete(@RequestHeader(value="messageId", required=false) String messageId, 
			@RequestParam(value = "code", required=true) Integer code) {
		ResponseEntity<MessageContext> responseEntity;
		try {
			this.useCaseDeleteCampaign.delete(code);
			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now()), OK);
		} catch (Exception e) {
			LOGGER.error("Fail to response " + e.getMessage());
			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now(), e.getMessage()), INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@ApiImplicitParams({
			@ApiImplicitParam(name = "messageId", value = "Message ID para rastreamento", required = false, dataType = "String", paramType = "header") })
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = MessageContext.class),
			@ApiResponse(code = 204, message = "Success", response = MessageContext.class),
			@ApiResponse(code = 500, message = "Failure", response = MessageContext.class) 
			})
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MessageContext> insert(@RequestHeader(value="messageId", required=false) String messageId, 
			@RequestBody String payLoad) {
		ResponseEntity<MessageContext> responseEntity;
		try {
			this.useCaseIncludeCampaign.updateCampaign(payLoad);

			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now()), OK);
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now()), BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error("Fail to response " + e.getMessage());
			responseEntity = new ResponseEntity<MessageContext>(new MessageContext(messageId, now(), e.getMessage()), INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
}

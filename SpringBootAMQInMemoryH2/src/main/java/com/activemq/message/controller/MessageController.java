package com.activemq.message.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activemq.message.model.MessageDetails;
import com.activemq.message.model.MessageVO;
import com.activemq.message.service.MessageService;

@RestController
public class MessageController {
	private static Logger log = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@RequestMapping("/msg/messages")
	public MessageDetails getAllMessage() {

		log.info("[GetAllMessage-Endpoint] - Total no of message list to be " + messageService.getAllMessage().size());
		MessageDetails message = new MessageDetails();
		message.setMessageList(messageService.getAllMessage());
		return message;
	}
}

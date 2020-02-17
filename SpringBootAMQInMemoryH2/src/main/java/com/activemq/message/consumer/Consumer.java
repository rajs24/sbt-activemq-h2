package com.activemq.message.consumer;

import java.util.concurrent.CountDownLatch;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.activemq.message.constants.IConstants;
import com.activemq.message.model.MessageVO;
import com.activemq.message.service.MessageService;

@Component
public class Consumer {

	private static Logger log = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private MessageService messageService;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@JmsListener(destination = IConstants.MESSAGE_QUEUE)
	public void receiveMessage(MessageVO msgVO) {
		log.info("Message Received: " + msgVO);
		addMessageToDB(msgVO);
		latch.countDown();
	}

	public void addMessageToDB(MessageVO msgVO) {
		messageService.addMessage(msgVO);
	}

	@PreDestroy
	public void destroy() {
		System.out.println("destroy");
	}

}

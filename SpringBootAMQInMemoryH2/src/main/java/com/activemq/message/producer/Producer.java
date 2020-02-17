package com.activemq.message.producer;

import javax.annotation.PreDestroy;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.activemq.message.model.MessageVO;

@RestController
@RequestMapping("/api/msg")
public class Producer {

	private static Logger log = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	@RequestMapping(value = "/publish", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String publishMessage(@RequestBody final MessageVO msgVO) {
		log.info("[Publish Message-Endpoint] - message sending to queue: " + msgVO);
		jmsTemplate.convertAndSend(queue, msgVO);
		return "Message Published Successfully";
	}

	@PreDestroy
	public void destroy() {
		System.out.println("destroy");
	}

}

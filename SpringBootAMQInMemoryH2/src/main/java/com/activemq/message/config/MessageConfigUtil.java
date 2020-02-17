package com.activemq.message.config;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.activemq.message.constants.IConstants;
import com.activemq.message.model.MessageVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableJms
@Configuration
public class MessageConfigUtil implements MessageConverter {

	private static final Logger log = LoggerFactory.getLogger(MessageConfigUtil.class);
	ObjectMapper objMapper;

	public MessageConfigUtil() {
		objMapper = new ObjectMapper();
	}

	@Bean
	public Queue queue() {

		return new ActiveMQQueue(IConstants.MESSAGE_QUEUE);
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		MessageVO msgVO = (MessageVO) object;
		String msgs = null;
		try {
			msgs = objMapper.writeValueAsString(msgVO);
		} catch (JsonProcessingException e) {
			log.error("[toMessage] - Error: ", e.getMessage());
		}
		TextMessage txtMsg = session.createTextMessage();
		txtMsg.setText(msgs);
		return txtMsg;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		TextMessage txtMsg = (TextMessage) message;
		String msgs = txtMsg.getText();
		MessageVO msgVO = null;
		try {
			msgVO = objMapper.readValue(msgs, MessageVO.class);
		} catch (Exception e) {
			log.error("[fromMessage] - Error: ", e.getMessage());
		}
		return msgVO;
	}
}

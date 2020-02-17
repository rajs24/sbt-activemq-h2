package com.activemq.message;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.activemq.message.constants.IConstants;
import com.activemq.message.consumer.Consumer;
import com.activemq.message.model.MessageVO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ActivemqInmemoryMesageApplicationTests {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Consumer consumer;

	@Test
	public void testMsgReceive() throws Exception {
		MessageVO msgVO = new MessageVO(IConstants.MESSAGE_QUEUE, "test message1", new Date());
		jmsTemplate.convertAndSend(IConstants.MESSAGE_QUEUE, msgVO);
		consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(consumer.getLatch().getCount()).isEqualTo(0);
	}

	@Test
	public void testConsumeMsgNotEquals() throws InterruptedException {
		MessageVO msgVO = new MessageVO(IConstants.MESSAGE_QUEUE, "Message from producer", new Date());
		jmsTemplate.convertAndSend(IConstants.MESSAGE_QUEUE, msgVO);
		consumer.getLatch().await(5000, TimeUnit.MILLISECONDS);
		assertThat(msgVO.getMessage()).isNotEqualTo("Msg from Producer");
	}

	@Test
	public void testConsumeMsgEquals() throws InterruptedException {
		MessageVO msgVO = new MessageVO(IConstants.MESSAGE_QUEUE, "Send test message", new Date());
		jmsTemplate.convertAndSend(IConstants.MESSAGE_QUEUE, msgVO);
		consumer.getLatch().await(5000, TimeUnit.MILLISECONDS);
		assertThat(msgVO.getMessage()).isEqualTo("Send test message");
	}

}

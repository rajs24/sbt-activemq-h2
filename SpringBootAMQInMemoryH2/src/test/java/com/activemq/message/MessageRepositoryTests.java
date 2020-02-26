package com.activemq.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.activemq.message.model.MessageVO;
import com.activemq.message.service.MessageRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MessageRepositoryTests {

	@Autowired
	private MessageRepository messageRepository;

	@Test
	public void testMsgEquals() {

		MessageVO msgVo1 = new MessageVO("msgrepo.queuename", "test-message-1", new Date());

		messageRepository.save(msgVo1);
		MessageVO msgVo2 = messageRepository.findByMessageName("test-message-1");
		assertEquals("test-message-1", msgVo2.getMessage());
	}

	@Test
	public void testSaveMessage() {

		MessageVO msgVo1 = new MessageVO("msgrepo.queuename", "test-message-2", new Date());
		messageRepository.save(msgVo1);
		MessageVO msgVo2 = messageRepository.findByMessageName("test-message-2");
		assertNotNull(msgVo2);
		assertNotNull(msgVo2.getId());
		assertEquals(msgVo2.getMessage(), msgVo1.getMessage());
		assertEquals(msgVo2.getMsgQueueName(), msgVo1.getMsgQueueName());
	}

	@Test
	public void findAllMessages() {

		MessageVO msgVo1 = new MessageVO("msgrepo.queuename", "test-message-3", new Date());
		messageRepository.save(msgVo1);
		List<MessageVO> msgList = messageRepository.findAll();
		assertNotNull(msgList);
		assertThat(msgList).isNotEmpty();
		assertThat(msgList).hasSizeGreaterThan(1);
	}

}

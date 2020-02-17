package com.activemq.message.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activemq.message.model.MessageVO;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public List<MessageVO> getAllMessage() {

		List<MessageVO> msgList = new ArrayList<MessageVO>();
		messageRepository.findAll().forEach(msgList::add);
		return msgList;
	}

	public void addMessage(MessageVO message) {
		messageRepository.save(message);
	}

}

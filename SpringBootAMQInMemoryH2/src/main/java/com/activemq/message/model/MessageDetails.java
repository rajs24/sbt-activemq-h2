package com.activemq.message.model;

import java.util.List;

public class MessageDetails {

	public MessageDetails() {

	}

	public MessageDetails(List<MessageVO> messageList) {
		super();
		this.messageList = messageList;
	}

	private List<MessageVO> messageList;

	public List<MessageVO> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageVO> messageList) {
		this.messageList = messageList;
	}

}

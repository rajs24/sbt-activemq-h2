package com.activemq.message.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MESSAGE_INFO")
public class MessageVO {

	@Id
	@GeneratedValue
	private Long id;
	private String msgQueueName;
	private String message;
	private Date timestamp;

	// @Transient
	// private List<MessageVO> messageList;

	public MessageVO() {

	}

	public MessageVO(String msgQueueName, String message, Date timestamp) {
		super();
		this.msgQueueName = msgQueueName;
		this.message = message;
		this.timestamp = timestamp;
	}

	public MessageVO(Long id, String msgQueueName, String message, Date timestamp) {
		super();
		this.id = id;
		this.msgQueueName = msgQueueName;
		this.message = message;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgQueueName() {
		return msgQueueName;
	}

	public void setMsgQueueName(String msgQueueName) {
		this.msgQueueName = msgQueueName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

//	public List<MessageVO> getMessageList() {
//		return messageList;
//	}
//
//	public void setMessageList(List<MessageVO> messageList) {
//		this.messageList = messageList;
//	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", msgQueueName=" + msgQueueName + ", message=" + message + ", timestamp="
				+ timestamp + "]";
	}

}

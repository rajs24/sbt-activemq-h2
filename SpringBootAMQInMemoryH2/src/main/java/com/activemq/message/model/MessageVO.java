package com.activemq.message.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MessageVO {

	@Id
	@GeneratedValue
	private Long id;
	private String msgQueueName;
	private String message;
	private Date timestamp;

	public MessageVO() {

	}

	public MessageVO(String msgQueueName, String message, Date timestamp) {
		super();
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

	@Override
	public String toString() {
		return "Message [id=" + id + ", msgQueueName=" + msgQueueName + ", message=" + message + ", timestamp="
				+ timestamp + "]";
	}

}

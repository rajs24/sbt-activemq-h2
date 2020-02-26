package com.activemq.message.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.activemq.message.model.MessageVO;

public interface MessageRepository extends JpaRepository<MessageVO, Long> {

	@Query("SELECT mi FROM MessageVO mi WHERE mi.message = :msgName")
	public MessageVO findByMessageName(String msgName);

	@Query("SELECT mi FROM MessageVO mi WHERE mi.msgQueueName = :queueName")
	public List<MessageVO> findByMessageQueueName(String queueName);
}

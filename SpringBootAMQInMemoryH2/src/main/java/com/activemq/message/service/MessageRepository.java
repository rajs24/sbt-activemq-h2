package com.activemq.message.service;

import org.springframework.data.repository.CrudRepository;

import com.activemq.message.model.MessageVO;

public interface MessageRepository extends CrudRepository<MessageVO, String> {

}

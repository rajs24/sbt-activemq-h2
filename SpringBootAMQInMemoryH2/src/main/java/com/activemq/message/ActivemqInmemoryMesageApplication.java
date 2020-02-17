package com.activemq.message;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.activemq.message.constants.IConstants;
import com.activemq.message.model.MessageVO;
import com.activemq.message.producer.Producer;

@SpringBootApplication
public class ActivemqInmemoryMesageApplication implements ApplicationRunner {
	private static Logger log = LoggerFactory.getLogger(ActivemqInmemoryMesageApplication.class);

	@Autowired
	Producer poducer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("[START] - Spring Boot Embedded ActiveMQ - Publish Messages to Queue");

		for (int i = 0; i < 5; i++) {
			poducer.publishMessage(new MessageVO(IConstants.MESSAGE_QUEUE,
					+i + " - Sending JMS Message using Embedded activeMQ", new Date()));
			TimeUnit.SECONDS.sleep(5);
		}

		log.info("[END] - Spring Boot Embedded ActiveMQ - Consumed Messages from Queue");

	}

	public static void main(String[] args) {
		SpringApplication.run(ActivemqInmemoryMesageApplication.class, args);
	}

}

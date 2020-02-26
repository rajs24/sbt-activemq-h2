package com.activemq.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.activemq.message.controller.MessageController;
import com.activemq.message.model.MessageDetails;
import com.activemq.message.model.MessageVO;
import com.activemq.message.service.MessageService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MessageControllerTests {

	@MockBean
	MessageService messageServiceMock;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		List<MessageVO> msgList = Arrays.asList(new MessageVO(1l, "msgcontest.queuename", "test-message-1", new Date()),
				new MessageVO(2l, "msgcontest.queuename", "test-message-2", new Date()),
				new MessageVO(3l, "msgcontest.queuename", "test-message-3", new Date()));
		when(messageServiceMock.getAllMessage()).thenReturn(msgList);
	}

	@Test
	public void testVerifygetAllMessageList() throws Exception {

		mockMvc.perform(get("/msg/messages").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.messageList[0].id").exists())
				.andExpect(jsonPath("$.messageList[0].msgQueueName").exists())
				.andExpect(jsonPath("$.messageList[0].message").exists())
				.andExpect(jsonPath("$.messageList[0].timestamp").exists())
				.andExpect(jsonPath("$.messageList[1].id").value(2))
				.andExpect(jsonPath("$.messageList[1].msgQueueName").value("msgcontest.queuename"))
				.andExpect(jsonPath("$.messageList[1].message").value("test-message-2")).andDo(print());
		verify(messageServiceMock, times(2)).getAllMessage();
	}

	@Test
	public void testVerifyGetAllMessageEndpoint() throws Exception {

		ResponseEntity<MessageDetails> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/msg/messages").toString(), MessageDetails.class);
		MessageDetails msgDetails = response.getBody();
		System.out.println("list : " + msgDetails.getMessageList().size());

		assertNotNull(msgDetails.getMessageList());
		assertThat(msgDetails.getMessageList()).isNotEmpty();
		assertThat(msgDetails.getMessageList()).hasSizeGreaterThan(1);
		assertThat(msgDetails.getMessageList()).hasSize(3);
		assertThat(msgDetails.getMessageList().get(0).getMessage()).isEqualTo("test-message-1");
		assertThat(msgDetails.getMessageList().get(1).getMessage()).isEqualTo("test-message-2");
	}
}

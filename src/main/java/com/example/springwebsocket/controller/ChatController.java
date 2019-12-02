package com.example.springwebsocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.springwebsocket.model.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat.register/{user}")
	@SendTo("/topic/public")
	public ChatMessage register(@DestinationVariable("user") String user,@Payload ChatMessage chatMessage,SimpMessageHeaderAccessor headerAccessor) {
		System.out.println(user);
		headerAccessor.getSessionAttributes().put("userName", chatMessage.getSender());
		return chatMessage;
	}

	
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage (@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
	
}

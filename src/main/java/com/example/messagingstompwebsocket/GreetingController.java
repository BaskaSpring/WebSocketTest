package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.math.BigInteger;
import java.security.Principal;
import java.util.*;

@Controller
public class GreetingController {

	static public HashMap<String, Integer> connectedUsers = new HashMap<>();

	static public HashMap<String, HashSet<BigInteger>> listPrime = new HashMap<>();

	@Autowired
	private NotificationService notificationService;

	@Autowired
	Generator generator;


	@MessageMapping("/hello")
	@SendToUser("/topic/greetings")
	public void greeting(HelloMessage message, Principal principal) {

		if (message.getName().equals("generate")) {
			if (connectedUsers.containsKey(principal.getName())){
				connectedUsers.remove(principal.getName());
			}
			List<BigInteger> nextPrime = generator.getNextPrimelist(principal.getName(), message.getRandom());
			notificationService.sendPrivateNotification(principal.getName(), nextPrime);
		} else{
			connectedUsers.put(principal.getName(),message.getRandom());
		}
	}

}

package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    public void sendPrivateNotification(final String userId, final List<BigInteger> integerList) {
        List<String> stringList = new ArrayList<>();
        for (BigInteger bigInteger : integerList) {
            stringList.add(bigInteger.toString());
        }
        Greeting message = new Greeting(stringList);
        messagingTemplate.convertAndSendToUser(userId,"/topic/greetings", message);
    }

    public void sendPrivateNotifications(final String userId, final BigInteger integer) {
        List<String> stringList = new ArrayList<>();
        stringList.add(integer.toString());
        Greeting message = new Greeting(stringList);
        messagingTemplate.convertAndSendToUser(userId,"/topic/greetings", message);
    }
}

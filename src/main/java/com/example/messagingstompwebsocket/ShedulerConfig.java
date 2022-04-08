package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.math.BigInteger;
import java.util.Map;

@EnableScheduling
@Configuration
public class ShedulerConfig {

    @Autowired
    Generator generator;

    @Autowired
    NotificationService notificationService;

    @Scheduled(fixedRate = 10000)
    public void sendMessages(){
        if (GreetingController.connectedUsers.size()>0) {
            for (Map.Entry entry : GreetingController.connectedUsers.entrySet()) {
                BigInteger bigInteger = generator.getNextPrime(entry.getKey().toString(), Integer.parseInt(entry.getValue().toString()));
                notificationService.sendPrivateNotifications(entry.getKey().toString(),bigInteger);
            }
        }
    }
}

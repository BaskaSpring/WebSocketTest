package com.example.messagingstompwebsocket;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class Generator {
    public List<BigInteger> getNextPrimelist(String userId, Integer range){
        List<BigInteger> resp = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1");
        for (int i = 0; i < range - 1; i++) {
            stringBuilder.append("0");
        }
        if (GreetingController.listPrime.containsKey(userId)) {
            HashSet<BigInteger> bigIntegerList = GreetingController.listPrime.get(userId);
            String s = stringBuilder.toString();
            int size = bigIntegerList.size();
            int count = 0 ;
            BigInteger bigInteger = new BigInteger(s);
            while (true) {
                bigInteger = bigInteger.nextProbablePrime();
                bigIntegerList.add(bigInteger);
                int sizeAfter =bigIntegerList.size();
                if (size<sizeAfter){
                    size=sizeAfter;
                    count++;
                    resp.add(bigInteger);
                }
                if (count>=5) {
                    break;
                }
            }
            GreetingController.listPrime.put(userId,bigIntegerList);
        } else {
            HashSet<BigInteger> primelist = new HashSet<>();
            String s = stringBuilder.toString();
            BigInteger bigInteger = new BigInteger(s);
            for (int i = 0; i < 5; i++) {
                bigInteger = bigInteger.nextProbablePrime();
                primelist.add(bigInteger);
                resp.add(bigInteger);
            }
            GreetingController.listPrime.put(userId,primelist);
        }
        return resp;
    }


    public BigInteger getNextPrime(String userId, Integer range){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1");
        for (int i = 0; i < range - 1; i++) {
            stringBuilder.append("0");
        }
        BigInteger bigInteger;
        if (GreetingController.listPrime.containsKey(userId)) {
            HashSet<BigInteger> bigIntegerList = GreetingController.listPrime.get(userId);
            String s = stringBuilder.toString();
            int size = bigIntegerList.size();
            bigInteger = new BigInteger(s);
            while (true) {
                bigInteger = bigInteger.nextProbablePrime();
                bigIntegerList.add(bigInteger);
                int sizeAfter =bigIntegerList.size();
                if (size<sizeAfter){
                    break;
                }
            }
            GreetingController.listPrime.put(userId,bigIntegerList);
        } else {
            HashSet<BigInteger> primelist = new HashSet<>();
            String s = stringBuilder.toString();
            bigInteger = new BigInteger(s).nextProbablePrime();
            primelist.add(bigInteger);
            GreetingController.listPrime.put(userId,primelist);
        }
        return bigInteger;
    }
}

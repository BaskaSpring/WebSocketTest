package com.example.messagingstompwebsocket;

public class HelloMessage {

	private String name;

	private Integer random;

	public HelloMessage(String name, Integer random) {
		this.name = name;
		this.random = random;
	}

	public HelloMessage() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRandom() {
		return random;
	}

	public void setRandom(Integer random) {
		this.random = random;
	}
}

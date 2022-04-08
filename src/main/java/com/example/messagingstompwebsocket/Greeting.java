package com.example.messagingstompwebsocket;

import java.util.List;

public class Greeting {
	private List<String> numbers;

	public Greeting(List<String> numbers) {
		this.numbers = numbers;
	}

	public Greeting() {
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
}

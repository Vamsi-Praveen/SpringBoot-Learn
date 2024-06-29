package com.learn.springboot.Learn.exceptions;

public class UserNotFound extends RuntimeException {
	public UserNotFound(String message) {
		super(message);
	}
}

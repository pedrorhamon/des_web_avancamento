package com.starking.prova.exception.handler;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
        super(msg);
    }
}

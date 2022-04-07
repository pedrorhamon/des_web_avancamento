package com.starking.prova.exception.handler;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessageException {

    private int statusCode;

    private Date time;

    private String message;

    private String description;
}

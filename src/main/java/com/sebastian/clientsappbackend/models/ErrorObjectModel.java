package com.sebastian.clientsappbackend.models;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorObjectModel {

    private Integer statusCode;
    private String message;
    private Date timeStamp;

}

package com.mediscreen.patient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@SuperBuilder
@JsonInclude(NON_NULL)
public class Response {
    protected LocalDateTime timeStamp;
    protected int           statusCode;
    protected HttpStatus    status;
    protected String        reason;
    protected String        message;
    protected Map<?,?> data;

    public LocalDateTime getTimeStamp() {

        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {

        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {

        return statusCode;
    }

    public void setStatusCode(int statusCode) {

        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {

        return status;
    }

    public void setStatus(HttpStatus status) {

        this.status = status;
    }

    public String getReason() {

        return reason;
    }

    public void setReason(String reason) {

        this.reason = reason;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public Map<?, ?> getData() {

        return data;
    }

    public void setData(Map<?, ?> data) {

        this.data = data;
    }
}

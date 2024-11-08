package com.ggpsgeorge.spring_user_gaming_list.Exceptions;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionResponse {
    
    private Date timestamp;
    private String details;
    private String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timestamp, String details, String message) {
        this.timestamp = timestamp;
        this.details = details;
        this.message = message;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionResponse timestamp(Date timestamp) {
        setTimestamp(timestamp);
        return this;
    }

    public ExceptionResponse details(String details) {
        setDetails(details);
        return this;
    }

    public ExceptionResponse message(String message) {
        setMessage(message);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ExceptionResponse)) {
            return false;
        }
        ExceptionResponse exceptionResponse = (ExceptionResponse) o;
        return Objects.equals(timestamp, exceptionResponse.timestamp) && Objects.equals(details, exceptionResponse.details) && Objects.equals(message, exceptionResponse.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, details, message);
    }

    @Override
    public String toString() {
        return "{" +
            " timestamp='" + getTimestamp() + "'" +
            ", details='" + getDetails() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }

}

package com.government.contracts.controller.dto;

public class ResponseDto {
    private String message;

    private ResponseCode code;

    private Object dto;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }
}

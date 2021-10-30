package com.admin.dashboard.be.dto;

import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ResponseDataGenerator {
    public <T> ResponseData<T> successResponse(T data, String message) {
        ResponseData responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessages(Collections.singletonList(message));
        responseData.setPayload(data);
        return responseData;
    }

    public <T> ResponseData<T> failedResponse(String message) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus(false);
        responseData.setMessages(Collections.singletonList(message));

        return responseData;
    }
}

package com.hybridchatbot.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T>{
    private Integer status_code;
    private String message;
    private T data;
    public BaseResponse<T> baseResponse() {
        return new BaseResponse<>(status_code, message, data);
    }
}

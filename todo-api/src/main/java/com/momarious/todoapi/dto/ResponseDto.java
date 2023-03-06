package com.momarious.todoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T> {

    private Boolean success;
    private String message;
    private T data;

    public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>(true, message, data);
    }

    public static <T> ResponseDto<T> error(String message) {
        return new ResponseDto<>(false, message, null);
    }

}

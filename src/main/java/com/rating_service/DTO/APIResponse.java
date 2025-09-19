package com.rating_service.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class APIResponse<T> {
    private final int statusCode;
    private final T data;
    private final String message;
}

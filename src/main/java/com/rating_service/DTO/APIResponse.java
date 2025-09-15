package com.rating_service.DTO;


import lombok.*;

public record APIResponse<T>(int statusCode, T data, String message) { }

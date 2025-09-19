package com.rating_service.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingWithUserDto<T> {
    private Long id;
    private Long giverId;
    private Long receiverId;
    private int score;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private T user;
}

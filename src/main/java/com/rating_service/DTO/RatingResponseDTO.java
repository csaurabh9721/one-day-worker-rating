package com.rating_service.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingResponseDTO {

    private Long id;
    private Long giverId;
    private Long receiverId;
    private int score;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

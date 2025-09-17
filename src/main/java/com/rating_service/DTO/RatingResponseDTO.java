package com.rating_service.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingResponseDTO {

    private String id;
    private Long giverId;
    private Long receiverId;
    private int score;
    private String comment;
    private boolean isDeleted;
    private String createdAt;
    private String updatedAt;
}

package com.rating_service.DTO;


import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingRequestDTO {

    @NotBlank(message = "Giver ID cannot be blank")
    private String giverId;

    @NotBlank(message = "Receiver ID cannot be blank")
    private String receiverId;

    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 5, message = "Score cannot be greater than 5")
    private int score;

    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;
}


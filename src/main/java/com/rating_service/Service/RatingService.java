package com.rating_service.Service;

import com.rating_service.DTO.*;
import com.rating_service.Entity.Rating;

import java.util.List;

public interface RatingService {

    RatingResponseDTO createRating(RatingRequestDTO requestDTO);

    RatingResponseDTO updateRating(Long ratingId, RatingRequestDTO requestDTO);

    RatingResponseDTO getRatingById(Long id);

    List<RatingWithUserDto<UserDto>> getRatingsByReceiverId(Long receiverId);

    List<RatingWithUserDto<WorkerDto>> getRatingsByGiverId(Long giverId);

    Boolean deleteRating(Long id);

    List<RatingResponseDTO> findRatingsForReceiverWithMinValue(Long receiverId, int minRating);

    List<RatingResponseDTO> findLatestRatingsForReceiver(Long receiverId);

}


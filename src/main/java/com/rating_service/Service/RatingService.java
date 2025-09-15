package com.rating_service.Service;

import com.rating_service.DTO.RatingRequestDTO;
import com.rating_service.DTO.RatingResponseDTO;
import com.rating_service.Entity.Rating;

import java.util.List;

public interface RatingService {

    RatingResponseDTO createRating(RatingRequestDTO requestDTO);

    RatingResponseDTO updateRating(String ratingId, RatingRequestDTO requestDTO);

    RatingResponseDTO getRatingById(String id);

    List<RatingResponseDTO> getRatingsByReceiverId(String receiverId);

    List<RatingResponseDTO> getRatingsByGiverId(String giverId);

    Boolean deleteRating(String id);

    List<RatingResponseDTO> findRatingsForReceiverWithMinValue(String receiverId, int minRating);

    List<RatingResponseDTO> findLatestRatingsForReceiver(String receiverId);

    List<RatingResponseDTO> searchRatings(String receiverId);
}


package com.rating_service.Service;

import com.rating_service.DTO.RatingRequestDTO;
import com.rating_service.DTO.RatingResponseDTO;
import com.rating_service.Entity.Rating;

import java.util.List;

public interface RatingService {

    RatingResponseDTO createRating(RatingRequestDTO requestDTO);

    RatingResponseDTO updateRating(String ratingId, RatingRequestDTO requestDTO);

    RatingResponseDTO getRatingById(String id);

    List<RatingResponseDTO> getRatingsByReceiverId(Long receiverId);

    List<RatingResponseDTO> getRatingsByGiverId(Long giverId);

    Boolean deleteRating(String id);

    List<RatingResponseDTO> findRatingsForReceiverWithMinValue(Long receiverId, int minRating);

    List<RatingResponseDTO> findLatestRatingsForReceiver(Long receiverId);

    List<RatingResponseDTO> searchRatings(Long receiverId);
}


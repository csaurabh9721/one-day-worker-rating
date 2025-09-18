package com.rating_service.Controller;


import com.rating_service.DTO.RatingRequestDTO;
import com.rating_service.DTO.RatingResponseDTO;
import com.rating_service.DTO.APIResponse;
import com.rating_service.Service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratingService/ratings")
public class RatingController {
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    private final RatingService ratingService;


    @PostMapping("/createRating")
    public ResponseEntity<APIResponse<RatingResponseDTO>> createRating(
            @Valid @RequestBody RatingRequestDTO ratingRequest) {
        RatingResponseDTO response = ratingService.createRating(ratingRequest);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Rating created successfully")
        );
    }

    @PostMapping("/updateRating/{id}")
    public ResponseEntity<APIResponse<RatingResponseDTO>> updateRating(@PathVariable String id,
                                                                       @Valid @RequestBody RatingRequestDTO ratingRequest) {
        Long ratingId = Long.valueOf(id);
        RatingResponseDTO response = ratingService.updateRating(ratingId, ratingRequest);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Rating updated successfully")
        );
    }

    @GetMapping("/getRatingById/{id}")
    public ResponseEntity<APIResponse<RatingResponseDTO>> getRatingById(@PathVariable String id) {
        Long ratingId = Long.valueOf(id);
        RatingResponseDTO response = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Rating fetched successfully")
        );
    }

    @GetMapping("/getRatingsByReceiverId/{receiverId}")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> getRatingsByReceiverId(@PathVariable String receiverId) {
        Long longId = Long.valueOf(receiverId);
        List<RatingResponseDTO> response = ratingService.getRatingsByReceiverId(longId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for receiver")
        );
    }

    @GetMapping("/getRatingsByGiverId/{giverId}")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> getRatingsByGiverId(@PathVariable String giverId) {
        Long longId = Long.valueOf(giverId);
        List<RatingResponseDTO> response = ratingService.getRatingsByGiverId(longId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for giver")
        );
    }

    @DeleteMapping("/deleteRatingByRatingId/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteRatingByRatingId(@PathVariable String id) {
        Long ratingId = Long.valueOf(id);
        Boolean res = ratingService.deleteRating(ratingId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), res, "Rating deleted successfully")
        );
    }

    @GetMapping("/findRatingsForReceiverWithMinValue")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> findRatingsForReceiverWithMinValue(
            @RequestParam() String giverId,
            @RequestParam(required = false, defaultValue = "0") int minScore) {
        Long longId = Long.valueOf(giverId);

        List<RatingResponseDTO> response = ratingService.findRatingsForReceiverWithMinValue(longId, minScore);

        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for giver")
        );
    }

    @GetMapping("/findLatestRatingsForReceiver/{giverId}")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> findLatestRatingsForReceiver(@PathVariable String giverId) {
        Long longId = Long.valueOf(giverId);
        List<RatingResponseDTO> response = ratingService.findLatestRatingsForReceiver(longId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for giver")
        );
    }
}


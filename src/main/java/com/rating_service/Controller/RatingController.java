package com.rating_service.Controller;


import com.rating_service.DTO.RatingRequestDTO;
import com.rating_service.DTO.RatingResponseDTO;
import com.rating_service.DTO.APIResponse;
import com.rating_service.Service.RatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
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
        RatingResponseDTO response = ratingService.updateRating(id, ratingRequest);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Rating updated successfully")
        );
    }

    @GetMapping("/getRatingById/{id}")
    public ResponseEntity<APIResponse<RatingResponseDTO>> getRatingById(@PathVariable String id) {
        RatingResponseDTO response = ratingService.getRatingById(id);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Rating fetched successfully")
        );
    }

    @GetMapping("/getRatingsByReceiverId/{receiverId}")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> getRatingsByReceiverId(@PathVariable String receiverId) {
        List<RatingResponseDTO> response = ratingService.getRatingsByReceiverId(receiverId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for receiver")
        );
    }

    @GetMapping("/getRatingsByGiverId/{giverId}")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> getRatingsByGiverId(@PathVariable String giverId) {
        List<RatingResponseDTO> response = ratingService.getRatingsByGiverId(giverId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for giver")
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteRating(@PathVariable String id) {
        Boolean res = ratingService.deleteRating(id);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), res, "Rating deleted successfully")
        );
    }

    @GetMapping("/findRatingsForReceiverWithMinValue")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> findRatingsForReceiverWithMinValue(
            @RequestParam() String giverId,
            @RequestParam(required = false, defaultValue = "0") int minScore) {
        List<RatingResponseDTO> response = ratingService.findRatingsForReceiverWithMinValue(giverId, minScore);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for giver")
        );
    }

    @GetMapping("/findLatestRatingsForReceiver/{giverId}")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> findLatestRatingsForReceiver(@PathVariable String giverId) {
        List<RatingResponseDTO> response = ratingService.findLatestRatingsForReceiver(giverId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for giver")
        );
    }

    @GetMapping("/searchRatings/{giverId}")
    public ResponseEntity<APIResponse<List<RatingResponseDTO>>> searchRatings(@PathVariable String giverId) {
        List<RatingResponseDTO> response = ratingService.searchRatings(giverId);
        return ResponseEntity.ok(
                new APIResponse<>(HttpStatus.OK.value(), response, "Ratings fetched for giver")
        );
    }
}


package com.rating_service.ServiceImpl;


import com.rating_service.DTO.*;
import com.rating_service.Entity.Rating;
import com.rating_service.GlobleException.ResourceNotFoundException;
import com.rating_service.Repository.RatingRepository;
import com.rating_service.Service.RatingService;
import com.rating_service.Service.UserFeignService;
import com.rating_service.Service.WorkerFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final UserFeignService userFeignService;
    private final WorkerFeignService workerFeignService;

    @Override
    public RatingResponseDTO createRating(RatingRequestDTO dto) {
        Rating rating = new Rating();
        rating.setGiverId(dto.getGiverId());
        rating.setReceiverId(dto.getReceiverId());
        rating.setScore(dto.getScore());
        rating.setComment(dto.getComment());
        rating.setDeleted(false);
        rating.setUpdatedAt(LocalDateTime.now());
        rating.setCreatedAt(LocalDateTime.now());
        Rating savedRating = ratingRepository.save(rating);
        RatingResponseDTO responseDto = new RatingResponseDTO();
        responseDto.setId(savedRating.getId());
        responseDto.setGiverId(savedRating.getGiverId());
        responseDto.setReceiverId(savedRating.getReceiverId());
        responseDto.setScore(savedRating.getScore());
        responseDto.setComment(savedRating.getComment());
        responseDto.setCreatedAt(savedRating.getCreatedAt());
        responseDto.setUpdatedAt(savedRating.getUpdatedAt());
        return responseDto;
    }

    @Override
    public RatingResponseDTO updateRating(Long ratingId, RatingRequestDTO requestDTO) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: " + ratingId));
        rating.setGiverId(requestDTO.getGiverId());
        rating.setReceiverId(requestDTO.getReceiverId());
        rating.setScore(requestDTO.getScore());
        rating.setComment(requestDTO.getComment());
        Rating saved = ratingRepository.save(rating);
        return toResponseDTO(saved);
    }

    @Override
    public RatingResponseDTO getRatingById(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: " + id));
        return toResponseDTO(rating);
    }

    @Override
    public List<RatingWithUserDto<UserDto>> getRatingsByReceiverId(Long receiverId) {

        return ratingRepository.findByReceiverId(receiverId)
                .stream()
                .map(rating -> {
                    RatingWithUserDto<UserDto> rt = toRatingWithUserDto(rating);
                    UserDto user = getUser(rt.getGiverId());
                    rt.setUser(user);
                    return rt;
                })
                .toList();
    }

    private UserDto getUser(Long workerId) {
        try {
            APIResponse<UserDto> user = userFeignService.getUserByUserId(workerId);
            return user.getData();
        } catch (Exception e) {
            log.info(e.toString());
            return null;
        }
    }

    @Override
    public List<RatingWithUserDto<WorkerDto>> getRatingsByGiverId(Long giverId) {
        return ratingRepository.findByGiverId(giverId)
                .stream()
                .map(rating -> {
                    RatingWithUserDto<WorkerDto> rt = toRatingWithWorkerDto(rating);
                    WorkerDto worker = getWorker(rt.getReceiverId());
                    rt.setUser(worker);
                    return rt;
                })
                .toList();
    }

    private WorkerDto getWorker(Long workerId) {
        try {
            APIResponse<WorkerDto> worker = workerFeignService.getWorkerByUserId(workerId);
            return worker.getData();
        } catch (Exception e) {
            log.info(e.toString());
            return null;
        }
    }

    @Override
    public Boolean deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: " + id));
        rating.setDeleted(true);
        ratingRepository.save(rating);
        return true;
    }

    @Override
    public List<RatingResponseDTO> findRatingsForReceiverWithMinValue(Long receiverId, int minRating) {
        return ratingRepository.findRatingsForReceiverWithMinValue(receiverId, minRating)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RatingResponseDTO> findLatestRatingsForReceiver(Long receiverId) {
        return ratingRepository.findLatestRatingsForReceiver(receiverId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }


    private RatingResponseDTO toResponseDTO(Rating rating) {
        return RatingResponseDTO.builder()
                .id(rating.getId())
                .giverId(rating.getGiverId())
                .receiverId(rating.getReceiverId())
                .score(rating.getScore())
                .comment(rating.getComment())
                .createdAt(rating.getCreatedAt())
                .updatedAt(rating.getUpdatedAt() != null ? rating.getUpdatedAt() : null)
                .build();
    }

    private RatingWithUserDto<UserDto> toRatingWithUserDto(Rating rating) {
        RatingWithUserDto<UserDto> dto = new RatingWithUserDto<>();
        dto.setId(rating.getId());
        dto.setGiverId(rating.getGiverId());
        dto.setReceiverId(rating.getReceiverId());
        dto.setScore(rating.getScore());
        dto.setComment(rating.getComment());
        dto.setCreatedAt(rating.getCreatedAt());
        dto.setUpdatedAt(rating.getUpdatedAt() != null ? rating.getUpdatedAt() : null);
        return dto;
    }

    private RatingWithUserDto<WorkerDto> toRatingWithWorkerDto(Rating rating) {
        RatingWithUserDto<WorkerDto> dto = new RatingWithUserDto<>();
        dto.setId(rating.getId());
        dto.setGiverId(rating.getGiverId());
        dto.setReceiverId(rating.getReceiverId());
        dto.setScore(rating.getScore());
        dto.setComment(rating.getComment());
        dto.setCreatedAt(rating.getCreatedAt());
        dto.setUpdatedAt(rating.getUpdatedAt() != null ? rating.getUpdatedAt() : null);
        return dto;
    }
}


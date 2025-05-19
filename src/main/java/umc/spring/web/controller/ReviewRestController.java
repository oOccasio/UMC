package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.repository.storeRepository.StoreRepository;
import umc.spring.service.reviewService.ReviewCommandService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.addReviewDTO> addReview(
            @RequestBody @Valid ReviewRequestDTO.addReviewDTO request,
            @RequestParam Long memberId) {

        Review review = reviewService.addReview(request, memberId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResponseDTO(review));

    }
}

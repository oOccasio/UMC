package umc.spring.service.reviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {

    public Review addReview(ReviewRequestDTO.addReviewDTO request, Long memberId);
}

package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Getter
    @Builder
    public static class addReviewDTO{

        Long reviewId;
        LocalDateTime createdAt;
    }
}

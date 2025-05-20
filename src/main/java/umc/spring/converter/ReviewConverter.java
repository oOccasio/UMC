package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.addReviewDTO request, Store store, Member member){
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();
    }


    public static ReviewResponseDTO.addReviewDTO toReviewResponseDTO(Review review){
        return ReviewResponseDTO.addReviewDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static ReviewResponseDTO.myReviewGetDTO toMyReviewGetDTO(Review review){
        return ReviewResponseDTO.myReviewGetDTO.builder()
                .name(review.getMember().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponseDTO.myReviewGetListDTO toMyReviewGetListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.myReviewGetDTO> myReviewGetDTOList = reviewList.stream()
                .map(ReviewConverter::toMyReviewGetDTO).toList();

        return ReviewResponseDTO.myReviewGetListDTO.builder()
                .reviewList(myReviewGetDTOList)
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .totalPage(reviewList.getTotalPages())
                .lastSize(myReviewGetDTOList.size())
                .build();

    }
}

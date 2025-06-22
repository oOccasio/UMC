package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.reviewService.ReviewCommandService;
import umc.spring.service.reviewService.ReviewQueryService;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("{memberId}/")
    public ApiResponse<ReviewResponseDTO.addReviewDTO> addReview(
            @RequestBody @Valid ReviewRequestDTO.addReviewDTO request,
            @RequestParam Long memberId) {

        Review review = reviewCommandService.addReview(request, memberId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResponseDTO(review));

    }

    @GetMapping("/{memberId}")
    @Operation(summary = "자신의 리뷰 목록 조회 API",
    description = "자신의 리뷰 목록을 조회하는 API며, 페이징을 포함, query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공!"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = io.swagger.v3.oas.annotations.responses.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = io.swagger.v3.oas.annotations.responses.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = io.swagger.v3.oas.annotations.responses.ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다.")
    })
    public ApiResponse<ReviewResponseDTO.myReviewGetListDTO> getMyReviewList(
            @PathVariable (name = "memberId") Long memberId,
            @RequestParam(name = "page") @ValidPage Integer page){

        Page<Review> reviewList = reviewQueryService.getMyReviewList(memberId, page);

        return ApiResponse.onSuccess(ReviewConverter.toMyReviewGetListDTO(reviewList));

    }


}

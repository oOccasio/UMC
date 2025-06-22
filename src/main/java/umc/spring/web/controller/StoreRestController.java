package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.service.storeService.StoreQueryService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reivews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),})

    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public umc.spring.apiPayload.ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") @ValidPage Integer page){
            Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
            return umc.spring.apiPayload.ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

}

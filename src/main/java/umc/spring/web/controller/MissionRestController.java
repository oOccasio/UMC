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
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.service.missionService.MissionQueryService;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionRestController {


    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.addMissionDTO> addMission(
            @RequestBody @Valid MissionRequestDTO.addMissionDTO request){

        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toMissionResponseDTO(mission));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "가게에 있는 미션 목록 조회 API",
    description = "가게에 있는 미션 목록을 조회하는 API며, 페이징을 포함, qeury String으로 Page번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공!"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "Store4001", description = "해당하는 가게가 없습니다.",
                    content = @Content(schema = @Schema(implementation = io.swagger.v3.oas.annotations.responses.ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, pathVariable입니다")
    })
    public ApiResponse<MissionResponseDTO.getStoreMissionDTOList> getStoreMission(
            @PathVariable (name = "storeId") Long storeId,
            @RequestParam (name = "page") Integer page){

        Page<Mission> storeReviewList = missionQueryService.getStoreMission(storeId, page);

        return ApiResponse.onSuccess(MissionConverter.toStoreMissionResponseDTOList(storeReviewList));
    }


}

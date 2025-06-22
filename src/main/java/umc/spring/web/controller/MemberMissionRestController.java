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
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.memberMissionService.MemberMissionCommandService;
import umc.spring.service.memberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberMission")
public class MemberMissionRestController {

    private final MemberMissionCommandService MemberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @PostMapping("/{memberId}")
    public ApiResponse<MemberMissionResponseDTO.addMemberMission> addMemberMission(
            @RequestBody @Valid MemberMissionRequestDTO.addMemberMission request,
            @RequestParam Long memberId){

        MemberMission memberMission = MemberMissionCommandService.addMemberMission(request,memberId);

        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionResponseDTO(memberMission));
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "진행중인 mission 조회",
            description = " 진행중인 미션을 조회하는 API며, 페이징을 포함, qeury String으로 Page번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공!"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "Member4001", description = "해당하는 사용자가 없습니다.",
                    content = @Content(schema = @Schema(implementation = io.swagger.v3.oas.annotations.responses.ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "member의 id, pathVariable입니다")
    })
    public ApiResponse<MemberMissionResponseDTO.getMemberChallengingMissionList> getMemberChallengingMission(
            @PathVariable (name = "memberId") Long memberId,
            @RequestParam (name = "page") @ValidPage Integer page){

        Page<MemberMission> mission = memberMissionQueryService.getMemberChallengingMission(memberId, page);

        return ApiResponse.onSuccess(MemberMissionConverter.getMemberChallengingMissionList(mission));

    }



}

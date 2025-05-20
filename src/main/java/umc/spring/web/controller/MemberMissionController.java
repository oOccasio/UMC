package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.memberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberMission")
public class MemberMissionController {

    private final MemberMissionCommandService MemberMissionService;

    @PostMapping("/")
    public ApiResponse<MemberMissionResponseDTO.addMemberMission> addMemberMission(
            @RequestBody @Valid MemberMissionRequestDTO.addMemberMission request,
            @RequestParam Long memberId){

        MemberMission memberMission = MemberMissionService.addMemberMission(request,memberId);

        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionResponseDTO(memberMission));
    }

}

package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionRestController {


    private final MissionCommandService missionCommandService;

    public ApiResponse<MissionResponseDTO.addMissionDTO> addMission(
            @RequestBody @Valid MissionRequestDTO.addMissionDTO request){

        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toMissionResponseDTO(mission));
    }
}

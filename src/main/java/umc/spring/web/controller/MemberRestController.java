package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.service.memberService.MemberCommandService;
import umc.spring.service.memberService.MemberQueryService;
import umc.spring.service.memberService.MemberQueryServiceImpl;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(
            @RequestBody @Valid MemberRequestDTO.JoinDto request){

        Member member = memberCommandService.joinMember(request);

        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));

    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API", description = "유저가 로그인하는 API 입니다.")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(
            @RequestBody @Valid MemberRequestDTO.LoginRequestDTO request){
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }
    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
               description = "유저가 내정보를 조회하는 API",
               security = {@SecurityRequirement(name = "JWT Token")}
    )
    public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request){
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }

}

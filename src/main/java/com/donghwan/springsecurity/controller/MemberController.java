package com.donghwan.springsecurity.controller;

import com.donghwan.springsecurity.dto.SignUpRequest;
import com.donghwan.springsecurity.entity.Member;
import com.donghwan.springsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/sign-up")
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        boolean isSuccess = this.memberService.createMember(
                signUpRequest.loginId(),
                signUpRequest.loginPassword()
        );

        if (isSuccess) {
            return "success";
        } else {
            return "fail";
        }
    }

    @GetMapping("/members/{memberId}")
    public Member findMemberById(@PathVariable("memberId") Long memberId) {
        return this.memberService.findById(memberId);
    }

}

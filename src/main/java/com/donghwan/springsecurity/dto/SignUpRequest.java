package com.donghwan.springsecurity.dto;

public record SignUpRequest(
        String loginId,
        String loginPassword
) {

}

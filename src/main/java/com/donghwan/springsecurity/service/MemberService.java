package com.donghwan.springsecurity.service;

import com.donghwan.springsecurity.entity.Member;
import com.donghwan.springsecurity.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public boolean createMember(
            String loginId,
            String loginPassword
    ) {
        try {
            if (this.memberRepository.findByUsername(loginId).isPresent()) {
                throw new IllegalArgumentException("member already exists");
            }

            log.info("[MemberService] member create");
            Member member = new Member(null, loginId, loginPassword, "default");
            this.memberRepository.save(member);
            return true;
        } catch (Exception e) {
            log.error("[MemberService] member create error : {}", e.getMessage());
            return false;
        }
    }

    @Transactional(readOnly = true)
    public Member findById(Long userId) {
        return this.memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("member not found"));
    }
}

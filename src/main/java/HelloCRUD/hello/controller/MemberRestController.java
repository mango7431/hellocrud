package HelloCRUD.hello.controller;

import HelloCRUD.hello.dto.MemberFormDto;
import HelloCRUD.hello.entity.Member;
import HelloCRUD.hello.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberRestController {

    private final MemberService memberService;

    private AuthenticationManager authenticationManager;


    @RequestMapping(value = "/emailCheck")
    @ResponseBody
    public Map<String,Integer> emailCheck(String email){
        log.info(email);
        Map<String,Integer> result = new HashMap<>();

        result = memberService.checkByEmail(email);

        log.info("{}",result);

        return  result;
    }

    @RequestMapping(value = "/checkPwdOK")
    @ResponseBody
    public boolean checkPwd(@RequestParam String checkPassword, Principal principal){
        log.info("비밀번호 결과 : {}",checkPassword);

        String email = principal.getName();

        return  memberService.checkPassword(email,checkPassword);
    }
    
    @PutMapping("/updateOK")
    @ResponseBody
    public Map<String,String> update(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        log.info("update 진입");
        log.info("데이터 정보 : {}",memberFormDto.toString());

        Map<String,String> map = new HashMap<>();

        if(bindingResult.hasErrors()){
            map.put("result","no");
        }else{
            memberService.updateMember(memberFormDto);
            map.put("result","yes");
        }

        /* 1. 새로운 UsernamePasswordAuthenticationToken 생성하여 AuthenticationManager를 이용하여 등록 *//*
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(memberFormDto.getEmail(), memberFormDto.getPassword())
        );

        *//* 2. SecurityContextHolder 안에 있는 Context를 호출해 변경된 Authentication으로 설정 *//*
        SecurityContextHolder.getContext().setAuthentication(authentication);*/

        return map;
    }

    @PutMapping("/deleteOK")
    @ResponseBody
    public int delete(String email, SessionStatus sessionStatus){
        log.info("delete 진입");
        log.info("email : {}",email);

        Member member = memberService.findByEmail(email);

        int result = memberService.deleteMember(member);

        if(result == 0){
            SecurityContextHolder.clearContext();
        }

        return result;
    }

}

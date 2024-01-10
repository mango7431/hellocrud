package HelloCRUD.hello.controller;

import HelloCRUD.hello.dto.MessageFormDto;
import HelloCRUD.hello.entity.Member;
import HelloCRUD.hello.service.MemberService;
import HelloCRUD.hello.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final SimpMessagingTemplate template;

    private final MessageService messageService;

    private final MemberService memberService;

    @MessageMapping(value = "/message/enter")
    public void enter(MessageFormDto messageFormDto, Principal principal) {
        Member member = memberService.findByEmail(principal.getName());
        messageFormDto.setSenderEmail(principal.getName());
        messageFormDto.setSender(member.getName());
        messageFormDto.setMessage(messageFormDto.getSender()+"님이 입장하였습니다.");
        messageFormDto.setSendDate(LocalDateTime.now());
        log.info("message : {}",messageFormDto);
        template.convertAndSend("/topic/chat/room/"+messageFormDto.getRoomId(),messageFormDto);
    }

    @MessageMapping(value = "/message/send")
    public void message(MessageFormDto messageFormDto, Principal principal){
        Member member = memberService.findByEmail(principal.getName());
        messageFormDto.setSenderEmail(principal.getName());
        messageFormDto.setSender(member.getName());
        messageFormDto.setSendDate(LocalDateTime.now());
        log.info("메세지 정보 : {}",messageFormDto);
        messageService.createMessage(messageFormDto);
        template.convertAndSend("/topic/chat/room/" + messageFormDto.getRoomId(), messageFormDto);
    }

}

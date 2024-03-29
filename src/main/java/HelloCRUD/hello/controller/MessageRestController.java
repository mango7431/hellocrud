package HelloCRUD.hello.controller;

import HelloCRUD.hello.dto.MessageListDto;
import HelloCRUD.hello.entity.ChatRoom;
import HelloCRUD.hello.service.ChatService;
import HelloCRUD.hello.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/message")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageRestController {

    private final MessageService messageService;

    private final ChatService chatService;

    @ResponseBody
    @RequestMapping(value = "/selectAll")
    public List<MessageListDto> MessageList(Long roomId){
        log.info("방 번호 : {}",roomId);
        ChatRoom room = chatService.findByRoomId(roomId);
        List<MessageListDto> messageList = messageService.findAllByRoomId(room);
        log.info("가져온 메세지 리스트 : {}",messageList);

        return messageList;
    }

}

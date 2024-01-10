package HelloCRUD.hello.controller;

import HelloCRUD.hello.dto.RoomFormDto;
import HelloCRUD.hello.entity.ChatRoom;
import HelloCRUD.hello.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        List<ChatRoom> chatRoomList = chatService.findAllRoom();
        model.addAttribute("chatRoomList",chatRoomList);
        return "chat/room";
    }
    // 채팅방 생성
    @PostMapping("/room")
    public String createRoom(RoomFormDto roomFormDto) {

        chatService.createRoom(roomFormDto);

        return "redirect:/chat/room";
    }
    /*// 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }*/
}

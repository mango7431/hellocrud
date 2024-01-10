package HelloCRUD.hello.service;

import HelloCRUD.hello.dto.RoomFormDto;
import HelloCRUD.hello.entity.ChatRoom;
import HelloCRUD.hello.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    public void createRoom(RoomFormDto roomFormDto) {

        ChatRoom chatRoom = ChatRoom.createRoom(roomFormDto);
        chatRoomRepository.save(chatRoom);

    }

    public ChatRoom findByRoomId(Long roomId) {

        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        ChatRoom chatRoom = new ChatRoom();
        if(optionalChatRoom.isPresent()){
            chatRoom = optionalChatRoom.get();
        }

        return chatRoom;
    }
}

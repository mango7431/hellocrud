package HelloCRUD.hello.service;

import HelloCRUD.hello.dto.RoomFormDto;
import HelloCRUD.hello.entity.ChatRoom;
import HelloCRUD.hello.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

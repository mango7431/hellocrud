package HelloCRUD.hello.service;

import HelloCRUD.hello.dto.MessageFormDto;
import HelloCRUD.hello.dto.MessageListDto;
import HelloCRUD.hello.entity.ChatRoom;
import HelloCRUD.hello.entity.Message;
import HelloCRUD.hello.repository.ChatRoomRepository;
import HelloCRUD.hello.repository.MessageRepository;
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
public class MessageService {

    private final MessageRepository messageRepository;

    private final ChatRoomRepository chatRoomRepository;

    public void createMessage(MessageFormDto messageFormDto) {

        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(messageFormDto.getRoomId());
        ChatRoom chatRoom = new ChatRoom();
        if(optionalChatRoom.isPresent()){
            chatRoom = optionalChatRoom.get();
        }

        Message message = Message.createMessage(messageFormDto,chatRoom);
        messageRepository.save(message);
    }

    public List<MessageListDto> findAllByRoomId(ChatRoom roomId) {

        List<MessageListDto> messageList = messageRepository.findAllByRoomId(roomId);

        return messageList;
    }
}

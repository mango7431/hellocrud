package HelloCRUD.hello.service;

import HelloCRUD.hello.dto.MessageFormDto;
import HelloCRUD.hello.dto.MessageListDto;
import HelloCRUD.hello.entity.Message;
import HelloCRUD.hello.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;

    public void createMessage(MessageFormDto messageFormDto) {
        Message message = Message.createMessage(messageFormDto);
        messageRepository.save(message);
    }

    public List<MessageListDto> findAllByRoomId(Long roomId) {

        List<MessageListDto> messageList = messageRepository.findAllByRoomId(roomId);

        return messageList;
    }
}

package HelloCRUD.hello.repository;

import HelloCRUD.hello.dto.MessageListDto;
import HelloCRUD.hello.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {



    @Query(value = "select new HelloCRUD.hello.dto.MessageListDto(id,roomId,sender,senderEmail,message,sendDate) " +
            "from Message " +
            "where roomId = :roomId")
    List<MessageListDto> findAllByRoomId(Long roomId);
}

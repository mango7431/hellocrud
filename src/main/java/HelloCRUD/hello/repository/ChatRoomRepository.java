package HelloCRUD.hello.repository;

import HelloCRUD.hello.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

}

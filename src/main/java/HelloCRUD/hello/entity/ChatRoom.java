package HelloCRUD.hello.entity;


import HelloCRUD.hello.dto.RoomFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chatroom")
@Getter
@Setter
@ToString
public class ChatRoom {

    @Id
    @Column(name="chatroom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
    private LocalDateTime createdDate;  //생성일시

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> message;

    public static ChatRoom createRoom(RoomFormDto roomFormDto) {
        ChatRoom room = new ChatRoom();
        room.roomName = roomFormDto.getRoomName();
        room.createdDate = LocalDateTime.now();
        return room;
    }
}

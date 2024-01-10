package HelloCRUD.hello.entity;

import HelloCRUD.hello.dto.MessageFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@Setter
@ToString
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //채팅방 ID
    private Long roomId;
    //보내는 사람
    private String sender;
    //보내는 사람의 이메일
    private String senderEmail;
    //내용
    private String message;
    //작성일
    private LocalDateTime sendDate;

    public static Message createMessage(MessageFormDto messageFormDto){
        Message message1 = new Message();
        message1.setRoomId(messageFormDto.getRoomId());
        message1.setSender(messageFormDto.getSender());
        message1.setSenderEmail(messageFormDto.getSenderEmail());
        message1.setMessage(messageFormDto.getMessage());
        message1.setSendDate(LocalDateTime.now());
        return message1;
    }


}

package HelloCRUD.hello.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MessageListDto {

    //메시지 id
    private Long id;
    //채팅방 ID
    private Long roomId;
    //보내는 사람
    private String sender;
    //보내는 사람의 이메일
    private String senderEmail;
    //내용
    private String message;
    //날짜
    private LocalDateTime sendDate;

    public MessageListDto(Long id, Long roomId, String sender, String senderEmail, String message, LocalDateTime sendDate){
        this.id = id;
        this.roomId = roomId;
        this.sender = sender;
        this.senderEmail = senderEmail;
        this.message = message;
        this.sendDate = sendDate;
    }
}

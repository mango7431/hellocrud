package HelloCRUD.hello.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardUpdateDto {

    private Long id;
    private String title;
    private int viewCnt;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String content;
}

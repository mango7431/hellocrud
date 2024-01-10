package HelloCRUD.hello.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardOneDto {

    private Long id;
    private String title;
    private int viewCnt;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String content;

    public BoardOneDto(Long id, String title, String content, int viewCnt, LocalDateTime createdDate, LocalDateTime modifiedDate, String email) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.email = email;
    }

}

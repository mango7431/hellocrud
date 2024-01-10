package HelloCRUD.hello.dto;

import HelloCRUD.hello.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardListDto {

    private Long id;
    private String title;
    private String content;
    private String email;
    private int viewCnt;
    private LocalDateTime createdDate;  //생성일시
    private LocalDateTime modifiedDate; //마지막 수정일시

    /*public BoardListDto(Long id, String title, String content, int viewCnt, LocalDateTime createdDate, LocalDateTime modifiedDate, String email) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.email = email;
    }*/

    public BoardListDto(Board boardPage) {
        this.id= boardPage.getId();
        this.title= boardPage.getTitle();
        this.content=boardPage.getContent();
        this.email=boardPage.getMember().getEmail();
        this.viewCnt=boardPage.getViewCnt();
        this.createdDate=boardPage.getCreatedDate();
        this.modifiedDate=boardPage.getModifiedDate();
    }
}
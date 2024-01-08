package HelloCRUD.hello.entity;

import HelloCRUD.hello.dto.BoardFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //pk
    private String title;   //제목
    private String content; //내용
    private String writer;  //작성자
    private int viewCnt;    //조회수
    private LocalDateTime createdDate;  //생성일시
    private LocalDateTime modifiedDate; //최종 수정일시

    public static Board createBoard(BoardFormDto boardFormDto){
        Board board = new Board();
        board.setTitle(boardFormDto.getTitle());
        board.setContent(boardFormDto.getContent());
        board.setWriter(boardFormDto.getWriter());
        board.setViewCnt(0);
        board.setCreatedDate(LocalDateTime.now());
        board.setModifiedDate(LocalDateTime.now());
        return board;
    }

}

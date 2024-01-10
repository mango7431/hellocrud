package HelloCRUD.hello.entity;

import HelloCRUD.hello.dto.BoardFormDto;
import HelloCRUD.hello.dto.BoardOneDto;
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
    private int viewCnt;    //조회수
    private LocalDateTime createdDate;  //생성일시
    private LocalDateTime modifiedDate; //최종 수정일시

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    public static Board createBoard(BoardFormDto boardFormDto, Member member){
        Board board = new Board();
        board.setTitle(boardFormDto.getTitle());
        board.setContent(boardFormDto.getContent());
        board.setMember(member);
        board.setViewCnt(0);
        board.setCreatedDate(LocalDateTime.now());
        board.setModifiedDate(LocalDateTime.now());
        return board;
    }

    public static Board updateBoard(BoardOneDto boardOneDto){
        Board board = new Board();
        board.setId(boardOneDto.getId());
        board.setTitle(boardOneDto.getTitle());
        board.setContent(boardOneDto.getContent());
        board.setModifiedDate(LocalDateTime.now());
        return board;
    }

}

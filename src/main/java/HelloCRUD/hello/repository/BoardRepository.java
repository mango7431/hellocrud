package HelloCRUD.hello.repository;

import HelloCRUD.hello.dto.BoardOneDto;
import HelloCRUD.hello.entity.Board;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface BoardRepository extends JpaRepository<Board,Long> {

    /*@Query(value = "select new HelloCRUD.hello.dto.BoardListDto(b.id, b.title, b.content,b.viewCnt , b.createdDate, b.modifiedDate, m.email) " +
            "from Board b " +
            "join b.member m " +
            "order by b.createdDate desc")
    List<BoardListDto> findAllBoardWithMemberEmail();*/

    @Query(value = "select new HelloCRUD.hello.dto.BoardOneDto(b.id, b.title, b.content, b.viewCnt, b.createdDate, b.modifiedDate , m.email) " +
            "from Board b " +
            "join b.member m " +
            "where b.id = :id")
    BoardOneDto findOneBoardWithMemberEmail(Long id);


    @Query(value = "update Board set viewCnt = viewCnt+1 where id = :id")
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    void viewCntUp(Long id);

    @Query(value = "update Board set title = :title, content = :content, modifiedDate = :modifiedDate where id = :id")
    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    void updateBoard(Long id,String title,String content,LocalDateTime modifiedDate);

    Page<Board> findAllByMemberEmail(PageRequest id,String email);
}

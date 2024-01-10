package HelloCRUD.hello.service;

import HelloCRUD.hello.dto.BoardFormDto;
import HelloCRUD.hello.dto.BoardListDto;
import HelloCRUD.hello.dto.BoardOneDto;
import HelloCRUD.hello.entity.Board;
import HelloCRUD.hello.entity.Member;
import HelloCRUD.hello.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(BoardFormDto boardFormDto, Member member) {

        Board board = Board.createBoard(boardFormDto, member);
        boardRepository.save(board);

    }

   /* public List<BoardListDto> selectListAll() {
        List<BoardListDto> boardList = boardRepository.findAllBoardWithMemberEmail();

        log.info(boardList.toString());

        return boardList;
    }*/

    public BoardOneDto selectOne(Long id) {

        BoardOneDto boardOneDto = boardRepository.findOneBoardWithMemberEmail(id);
        log.info(boardOneDto.toString());

        return boardOneDto;
    }

    public void viewCntUp(Long id) {
        boardRepository.viewCntUp(id);
    }

    public Page<BoardListDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<Board> boardsPages = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록 : id, title, content, author
        Page<BoardListDto> boardListDtoPage = boardsPages.map(
                boardPage -> new BoardListDto(boardPage));

        return boardListDtoPage;
    }

    public Page<BoardListDto> paging2(Pageable pageable, String email) {

        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; // 한페이지에 보여줄 글 개수

        // 한 페이지당 3개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<Board> boardsPages = boardRepository.findAllByMemberEmail(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")),email);

        // 목록 : id, title, content, author
        Page<BoardListDto> boardListDtoPage = boardsPages.map(
                boardPage -> new BoardListDto(boardPage));

        return boardListDtoPage;
    }

    public void updateBoard(BoardOneDto boardOneDto) {

        Board newboard = Board.updateBoard(boardOneDto);
        boardRepository.updateBoard(newboard.getId(),newboard.getTitle(),newboard.getContent(),newboard.getModifiedDate());
    }

    public int deleteBoard(Board board) {

        boardRepository.delete(board);

        Optional<Board> boardOptional = findById(board.getId());

        if (boardOptional.isPresent()) {
            return 1;
        } else {
            // Optional에 값이 없는 경우 처리할 내용
            return 0; // 또는 다른 적절한 값 또는 예외 처리
        }

    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

}

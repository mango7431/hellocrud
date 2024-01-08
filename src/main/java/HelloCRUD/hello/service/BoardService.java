package HelloCRUD.hello.service;

import HelloCRUD.hello.dto.BoardFormDto;
import HelloCRUD.hello.entity.Board;
import HelloCRUD.hello.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(BoardFormDto boardFormDto) {

        Board board = Board.createBoard(boardFormDto);
        boardRepository.save(board);

    }

    public List<Board> selectListAll() {
        List<Board> boardList = boardRepository.findAllByOrderByCreatedDateDesc();

        return boardList;
    }
}

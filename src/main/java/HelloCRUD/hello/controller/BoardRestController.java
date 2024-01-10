package HelloCRUD.hello.controller;

import HelloCRUD.hello.entity.Board;
import HelloCRUD.hello.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardRestController {

    private final BoardService boardService;

    @PutMapping("/deleteOK")
    @ResponseBody
    public int delete(Long id){
        log.info("delete 진입");
        log.info("게시글 번호 : {}",id);

        Optional<Board> boardOptional = boardService.findById(id);

        if(boardOptional.isPresent()){
            Board board = boardOptional.get();
            int result = boardService.deleteBoard(board);
            return result;
        }else {
            return 0;
        }
    }

}

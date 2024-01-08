package HelloCRUD.hello.controller;

import HelloCRUD.hello.dto.BoardFormDto;
import HelloCRUD.hello.entity.Board;
import HelloCRUD.hello.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/insert")
    public String boardInsert(Model model, Principal principal){
        model.addAttribute("email",principal.getName());
        return "board/boardInsert";
    }

    @PostMapping(value = "/insertBoard")
    public String boardFormInsert(BoardFormDto boardFormDto){
        log.info("정보 : {}",boardFormDto.toString());

        boardService.createBoard(boardFormDto);

        return "redirect:/";
    }

    @GetMapping(value = "/selectList")
    public String boardList(Model model){
        log.info("게시글 목록 보기");

        List<Board> boardList = boardService.selectListAll();

        model.addAttribute("boardList",boardList);

        return "board/boardList";
    }

}

package HelloCRUD.hello.controller;

import HelloCRUD.hello.dto.BoardFormDto;
import HelloCRUD.hello.dto.BoardListDto;
import HelloCRUD.hello.dto.BoardOneDto;
import HelloCRUD.hello.entity.Member;
import HelloCRUD.hello.service.BoardService;
import HelloCRUD.hello.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    private final MemberService memberService;

    @GetMapping(value = "/insert")
    public String boardInsert(Model model, Principal principal){
        model.addAttribute("email",principal.getName());
        return "board/boardInsert";
    }

    @PostMapping(value = "/insertBoard")
    public String boardFormInsert(BoardFormDto boardFormDto, Principal principal){
        log.info("정보 : {}",boardFormDto.toString());
        log.info("이메일 정보 : {}",principal.getName());

        Member member = memberService.findByEmail(principal.getName());

        boardService.createBoard(boardFormDto,member);

        return "redirect:/";
    }

    /*@GetMapping(value = "/selectList")
    public String boardList(Model model){
        log.info("게시글 목록 보기");

        List<BoardListDto> boardList = boardService.selectListAll();

        model.addAttribute("boardList",boardList);

        return "board/boardList";
    }*/

    @GetMapping(value = "/boardOne")
    public String boardOne(Long id,Model model){
        log.info("게시글 하나 보기 id : {}",id);

        // 방문 횟수 증가
        boardService.viewCntUp(id);

        BoardOneDto board = boardService.selectOne(id);

        model.addAttribute("board",board);

        return "board/boardOne";
    }

    @GetMapping("/selectList")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardListDto> boardList = boardService.paging(pageable);

        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 5;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), boardList.getTotalPages());

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/boardList";
    }

    @GetMapping(value = "/update")
    public String update(Model model, Principal principal, Long id){

        BoardOneDto board = boardService.selectOne(id);
        model.addAttribute("board",board);
        return "board/boardUpdate";
    }

    @PostMapping(value = "/updateBoard")
    public String updateBoard(BoardOneDto boardOneDto){
        log.info("정보 : {}",boardOneDto.toString());

        boardService.updateBoard(boardOneDto);

        return "redirect:/board/boardOne?id="+boardOneDto.getId();
    }

    @GetMapping("/myBoard")
    public String myBoard(@PageableDefault(page = 1) Pageable pageable, Model model,Principal principal) {
        Page<BoardListDto> boardList = boardService.paging2(pageable,principal.getName());

        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 5;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), boardList.getTotalPages());

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/myBoard";
    }

}

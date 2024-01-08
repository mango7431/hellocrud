package HelloCRUD.hello.controller;

import HelloCRUD.hello.dto.MemberFormDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class MainController {

    @GetMapping(value = "/")
    public String main(){

        return "Main";
    }

}

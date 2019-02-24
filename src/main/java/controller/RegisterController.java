package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView registerView() {
        return new ModelAndView("register");
    }

    @PostMapping
    public void register(@RequestParam String login,
                         @RequestParam String pass,
                         @RequestParam String cpass,
                         HttpServletResponse resp) throws IOException {
        if (userService.isUserExists(login) && userService.isPassConfirm(pass, cpass)) {
            userService.save(login, pass);
            resp.sendRedirect("/fs/main/");

        } else {
            resp.sendRedirect("/fs/");
        }
    }
}

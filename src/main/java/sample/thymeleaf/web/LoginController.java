package sample.thymeleaf.web;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.common.dao.entity.Login;
import sample.common.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute Login login,
                          HttpSession session,
                          Model model) {

        Login loginUser = loginService.login(login);

        if (loginUser == null) {
            model.addAttribute("errorMessage",
                    "ユーザー名またはパスワードが違います");
            return "login";
        }

        session.setAttribute("loginUser",
                loginUser.getUsername());

        return "redirect:/tasks";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}
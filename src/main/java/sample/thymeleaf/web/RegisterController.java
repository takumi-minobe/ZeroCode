package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.common.dao.entity.Login;
import sample.common.service.LoginService;

@Controller
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute Login login,
                             Model model) {

        Login existsUser = loginService.findByUsername(login.getUsername());

        if (existsUser != null) {
            model.addAttribute("errorMessage", "このユーザー名はすでに登録されています");
            return "register";
        }

        loginService.insert(login);

        return "redirect:/login";
    }
}
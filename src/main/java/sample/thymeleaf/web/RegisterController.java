package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        String password = login.getPassword();

        // パスワードチェック
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")) {
            model.addAttribute("errorMessage",
                    "パスワードは8文字以上で、英字と数字を含めてください");
            return "register";
        }

        // ユーザー名重複チェック
        Login existsUser = loginService.findByUsername(login.getUsername());

        if (existsUser != null) {
            model.addAttribute("errorMessage",
                    "このユーザー名はすでに登録されています");
            return "register";
        }

        // DB登録
        loginService.insert(login);

        return "redirect:/login";
    }
}
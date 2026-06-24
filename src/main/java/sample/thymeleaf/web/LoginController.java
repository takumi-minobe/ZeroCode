package sample.thymeleaf.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.common.dao.entity.Login;
import sample.common.dao.entity.LoginHistory;
import sample.common.service.LoginHistoryService;
import sample.common.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute Login login,
                          HttpSession session,
                          Model model,
                          HttpServletRequest request) {

        Login loginUser = loginService.login(login);

        Login dbUser = loginService.findByUsername(login.getUsername());

        if (dbUser != null && Boolean.TRUE.equals(dbUser.getAccountLocked())) {

            LoginHistory history = new LoginHistory();
            history.setUsername(login.getUsername());
            history.setLoginResult("失敗");
            history.setIpAddress(request.getRemoteAddr());
            loginHistoryService.insert(history);

            model.addAttribute("errorMessage",
                    "アカウントがロックされています。管理者へお問い合わせください。");
            return "login";
        }

        if (loginUser == null) {

            LoginHistory history = new LoginHistory();
            history.setUsername(login.getUsername());
            history.setLoginResult("失敗");
            history.setIpAddress(request.getRemoteAddr());
            loginHistoryService.insert(history);

            model.addAttribute("errorMessage",
                    "ユーザー名またはパスワードが違います");
            return "login";
        }

        LoginHistory history = new LoginHistory();
        history.setUsername(loginUser.getUsername());
        history.setLoginResult("成功");
        history.setIpAddress(request.getRemoteAddr());
        loginHistoryService.insert(history);

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
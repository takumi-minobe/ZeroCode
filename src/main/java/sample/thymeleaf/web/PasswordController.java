package sample.thymeleaf.web;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sample.common.service.LoginService;

@Controller
public class PasswordController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/password/change")
    public String showChangePassword(HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        return "password_change";
    }

    @PostMapping("/password/change")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 HttpSession session,
                                 Model model) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("loginUser");

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "新しいパスワードが一致しません");
            return "password_change";
        }

        boolean result = loginService.changePassword(username, currentPassword, newPassword);

        if (!result) {
            model.addAttribute("error", "現在のパスワードが違います");
            return "password_change";
        }

        model.addAttribute("message", "パスワードを変更しました");
        return "password_change";
    }
}
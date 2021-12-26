package com.app.controller;

import com.app.entity.User;
import com.app.service.AttendanceService;
import com.app.service.EventService;
import com.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class LoginController {
    private final UserService userService;
    private final EventService eventService;
    private final AttendanceService attendanceService;

    @RequestMapping("/login")
    public String index() {
        return "/login";
    }

    @GetMapping("/login-error")
    public String loginErrorPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            errorMessage = "無效的電子郵件或密碼 ! (Incorrect email or password!)";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "/register";
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String department,
                               @RequestParam String dateOfBirth, @RequestParam int startOfStudies, @RequestParam String instagramUsername,
                               @RequestParam String linkedinUsername) throws Exception {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setDescription("");
        user.setUsername(username);
        user.setDepartment(department);
        user.setDateOfBirth(Long.parseLong(dateOfBirth.replace("-", "")));
        user.setStartOfStudies(startOfStudies);
        user.setInstagramUsername(instagramUsername);
        user.setLinkedinUsername(linkedinUsername);
        userService.save(user);
        return "login";
    }


}

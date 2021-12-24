package com.app.controller;

import com.app.service.AttendanceService;
import com.app.service.EventService;
import com.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class LoginController {
    private final UserService userService;
    private final EventService eventService;
    private final AttendanceService attendanceService;

    @RequestMapping("/")
    public String index() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, Model model) {
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        System.out.println(email + " , " + password);
        if (userService.validateCredentials(email, password))
            return "/clubs/list";
        else {
            model.addAttribute("error", "Invalid Credentials");
            return "/";
        }
    } //login bozuk
    

}

package com.app.controller;

import com.app.service.AttendanceService;
import com.app.service.EventService;
import com.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class LoginController {
    private final UserService userService;
    private final EventService eventService;
    private final AttendanceService attendanceService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}

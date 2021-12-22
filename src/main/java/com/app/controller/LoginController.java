package com.app.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class LoginController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}

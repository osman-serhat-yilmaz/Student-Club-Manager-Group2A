package com.app.controller;

import com.app.entity.Application;
import com.app.service.ApplicationService;
import com.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    public EmailController() {

    }


    @RequestMapping(method = RequestMethod.POST)
    public String sendMail(String message) {
        emailService.sendMail("ozgurabi123@gmail.com", "Bruh", "bruh");
        return "bruh";
    }

}
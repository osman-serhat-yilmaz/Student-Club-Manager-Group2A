package com.app.controller;

import com.app.entity.Event;
import com.app.entity.User;
import com.app.service.EventService;
import com.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createUser(@RequestBody User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addAttribute("id", user.getId());
        return "redirect:/users/{id}";
    }

    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showUser(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("user", userService.findOneById(id));
        return "users/show";
    }

    @PutMapping("/{id}")
    public String updateUser(@RequestBody User user, RedirectAttributes redirectAttributes) {
        User savedUser = userService.save(user);
        redirectAttributes.addAttribute("id", savedUser.getId());
        return "redirect:/users/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id, Model model) {
        userService.delete(id);
        model.asMap().clear();
        return "redirect:/users";
    }

    @RequestMapping(value = "/create-form", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "clubs/create";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("user", userService.findOneById(id));
        return "users/edit";
    }
}
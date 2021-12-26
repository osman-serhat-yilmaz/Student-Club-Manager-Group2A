package com.app.controller;

import com.app.entity.Attendance;
import com.app.entity.Event;
import com.app.entity.MyUserDetails;
import com.app.entity.User;
import com.app.service.AttendanceService;
import com.app.service.EventService;
import com.app.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class UserController {
    private final UserService userService;
    private final EventService eventService;
    private final AttendanceService attendanceService;

    @GetMapping("/user-profile")
    public String goUserProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", ((MyUserDetails)authentication.getPrincipal()));
        System.out.println(((MyUserDetails)authentication.getPrincipal()).getUUID());
        model.addAttribute("attendedevents", attendanceService.findAttendancesByUserIDAndAttended(((MyUserDetails) authentication.getPrincipal()).getUUID(), true));
        model.addAttribute("canEdit", true);

        return "users/show";
    }

 /*   @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createUser(@RequestBody User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addAttribute("id", user.getId());
        return "redirect:/users/{id}";
    }*/

    //single item


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showAnotherUser(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("user", userService.findOneById(id));
        model.addAttribute("attendedevents", attendanceService.findAttendancesByUserIDAndAttended(id, true));
        model.addAttribute("canEdit", false);
        return "users/show";
    }

    @PutMapping()
    public String updateUser(@RequestBody User user) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        userService.findOneById(((MyUserDetails)authentication.getPrincipal()).getUUID()).setDepartment(user.getDepartment());
        userService.findOneById(((MyUserDetails)authentication.getPrincipal()).getUUID()).setDescription(user.getDescription());
        userService.findOneById(((MyUserDetails)authentication.getPrincipal()).getUUID()).setUsername(user.getUsername());
        userService.findOneById(((MyUserDetails)authentication.getPrincipal()).getUUID()).setLinkedinUsername(user.getLinkedinUsername());
        userService.findOneById(((MyUserDetails)authentication.getPrincipal()).getUUID()).setPassword(user.getPassword());
        userService.findOneById(((MyUserDetails)authentication.getPrincipal()).getUUID()).setInstagramUsername(user.getInstagramUsername());
        userService.findOneById(((MyUserDetails)authentication.getPrincipal()).getUUID()).setDateOfBirth(user.getDateOfBirth());

        return "/user-profile";
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
        return "users/create";
    }

    @GetMapping("/edit-profile-page")
    public String editForm( Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", ((MyUserDetails)authentication.getPrincipal()));
        return "users/edit";
    }

    @RequestMapping(value = "/{id}/attendances", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String userAttendancesPage(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("attendances", attendanceService.findAttendancesByUserID(id));
        return "/users/attendances";
    }

   /* @RequestMapping("/schedule/{id}")
    public String schedule(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("user", userService.findOneById(id));
        model.addAttribute("events", attendanceService.findAttendancesByUserIDAndAttended(id, null));
        return "/users/schedule";
    }

    @RequestMapping("/schedule")
    public String showSchedule() { //fıx thıs
        return
    }*/
}
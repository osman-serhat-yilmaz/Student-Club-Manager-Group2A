package com.app.controller;

import com.app.entity.Attendance;
import com.app.entity.Club;
import com.app.entity.ClubRole;
import com.app.entity.User;
import com.app.helpers.Role;
import com.app.service.AttendanceService;
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

import java.util.UUID;

@Controller
@RequestMapping("/attendances")
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final UserService userService;
    private final EventService eventService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createAttendance(@RequestBody Attendance attendance, RedirectAttributes redirectAttributes) {
        attendanceService.save(attendance);
        redirectAttributes.addAttribute("id", attendance.getId());
        return "redirect:/attendances/{id}";
    }

    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showAttendance(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("attendance", attendanceService.findOneById(id));
        return "attendances/show";
    }

    @PutMapping("/{id}")
    public String updateAttendance(@RequestBody Attendance attendance, RedirectAttributes redirectAttributes) {
        Attendance savedAttendance = attendanceService.save(attendance);
        redirectAttributes.addAttribute("id",savedAttendance.getId());
        return "redirect:/attendances/{id}";
    }

    @GetMapping(value = "/create")
    public String createForm() {
        return "/attendances/create";
    }

    @PostMapping(path = "/create")
    public String createAtt(@RequestParam String eventname, @RequestParam String usermail, @RequestParam String attended) {
        Attendance att = new Attendance();

        att.setAttended(attended.equals("1"));
        System.out.println("1");
        att.setUserID(userService.findUserByEmail(usermail).getId());
        att.setEventID(eventService.findEventByName(eventname).get(0).getId());

        attendanceService.save(att);

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable UUID id, Model model) {
        attendanceService.delete(id);
        model.asMap().clear();
        return "redirect:/attendances";
    }

    @RequestMapping(value = "/create-form", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String createForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "attendances/create";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("attendance", attendanceService.findOneById(id));
        return "attendances/edit";
    }

    @PostMapping("/attendances/enteratt")
    public String takeAttendance() {
        return "index";
    }
}

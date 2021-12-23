package com.app.controller;

import com.app.entity.Attendance;
import com.app.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/attendances")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

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

}

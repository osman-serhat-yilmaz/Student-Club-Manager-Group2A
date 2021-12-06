package com.app.controller;

import com.app.entity.Attendance;
import com.app.service.AttendanceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping
    public String create(@ModelAttribute Attendance attendance, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            populateForm(model, new Attendance());
            return "attendance/create";
        }

        Attendance newAttendance = attendanceService.save(attendance);
        redirectAttrs.addAttribute("id", newAttendance.getId());
        return "redirect:/attendances/{id}";
    }

    @GetMapping
    public String createForm(Model model) {
        populateForm(model, new Attendance());
        return "attendances/create";
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable("id") UUID id, Model model) {
        populateForm(model, attendanceService.findOneById(id));
        return "attendances/edit";
    }

    @PutMapping()
    public String update( @ModelAttribute Attendance attendance, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            populateForm(model, attendance);
            return "attendances/edit";
        }

        Attendance savedAttendance = attendanceService.save(attendance);
        redirectAttrs.addAttribute("id", savedAttendance.getId());
        return "redirect:/attendances/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id, Model model) {
        attendanceService.delete(id);
        model.asMap().clear();
        return "redirect:/attendances";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("attendance", attendanceService.findOneById(id));
        return "attendances/show";
    }

    void populateForm(Model model, Attendance attendance) {
        model.addAttribute("attendance", attendance);
    }
}

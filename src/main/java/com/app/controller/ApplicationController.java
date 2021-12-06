package com.app.controller;

import com.app.entity.Application;
import com.app.service.ApplicationService;
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
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public String create(@ModelAttribute Application application, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            populateForm(model, new Application());
            return "application/create";
        }

        Application newApplication = applicationService.save(application);
        redirectAttrs.addAttribute("id", newApplication.getId());
        return "redirect:/applications/{id}";
    }

    @GetMapping
    public String createForm(Model model) {
        populateForm(model, new Application());
        return "applications/create";
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable("id") UUID id, Model model) {
        populateForm(model, applicationService.findOneById(id));
        return "applications/edit";
    }

    @PutMapping()
    public String update( @ModelAttribute Application application, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            populateForm(model, application);
            return "application/edit";
        }

        Application savedApplication = applicationService.save(application);
        redirectAttrs.addAttribute("id", savedApplication.getId());
        return "redirect:/applications/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id, Model model) {
        applicationService.delete(id);
        model.asMap().clear();
        return "redirect:/applications";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("application", applicationService.findOneById(id));
        return "applications/show";
    }

    void populateForm(Model model, Application application) {
        model.addAttribute("application", application);
    }
}

//A test class, not sure whether it works or not
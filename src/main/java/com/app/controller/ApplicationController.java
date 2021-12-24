package com.app.controller;

import com.app.entity.Application;
import com.app.entity.Club;
import com.app.service.ApplicationService;
import com.app.service.ClubService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/applications")
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class ApplicationController {
    private final ApplicationService applicationService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createApplication(@RequestBody Application application, RedirectAttributes redirectAttributes) {
        applicationService.save(application);
        redirectAttributes.addAttribute("id",application.getId());
        return "redirect:/applications/{id}";
    }

    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showApplication(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("application",applicationService.findOneById(id));
        return "applications/show";
    }

    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable UUID id, Model model) {
        applicationService.delete(id);
        model.asMap().clear();
        return "redirect:/applications";
    }

    @PutMapping("/{id}")
    public String updateApplication(@RequestBody Application application, RedirectAttributes redirectAttributes) {
        Application savedApplication = applicationService.save(application);
        redirectAttributes.addAttribute("id", savedApplication.getId());
        return "redirect:/applications/{id}";
    }

    @RequestMapping(value = "/create-form", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String createForm(Model model) {
        model.addAttribute("application", new Application());
        return "applications/create";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("application", applicationService.findOneById(id));
        return "applications/edit";
    }
}
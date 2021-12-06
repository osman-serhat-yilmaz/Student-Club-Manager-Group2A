package com.app.controller;

import com.app.entity.Application;
import com.app.entity.Club;
import com.app.service.ApplicationService;
import com.app.service.ClubService;
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
@RequestMapping("club")
public class ClubController {
    private final ClubService clubService;

    @PostMapping
    public String create(@ModelAttribute Club club, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            populateForm(model, new Club());
            return "club/create";
        }

        Club newClub = clubService.save(club);
        redirectAttrs.addAttribute("id", newClub.getId());
        return "redirect:/clubs/{id}";
    }

    @GetMapping
    public String createForm(Model model) {
        populateForm(model, new Club());
        return "clubs/create";
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable("id") UUID id, Model model) {
        populateForm(model, clubService.findOneById(id));
        return "clubs/edit";
    }

    @PutMapping()
    public String update( @ModelAttribute Club club, BindingResult result, RedirectAttributes redirectAttrs, Model model) {
        if (result.hasErrors()) {
            populateForm(model, club);
            return "club/edit";
        }

        Club savedClub = clubService.save(club);
        redirectAttrs.addAttribute("id", savedClub.getId());
        return "redirect:/clubs/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") UUID id, Model model) {
        clubService.delete(id);
        model.asMap().clear();
        return "redirect:/clubs";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("club", clubService.findOneById(id));
        return "clubs/show";
    }

    void populateForm(Model model, Club club) {
        model.addAttribute("club", club);
    }
}

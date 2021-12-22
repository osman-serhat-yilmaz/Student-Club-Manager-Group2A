package com.app.controller;

import com.app.entity.Application;
import com.app.entity.Club;
import com.app.service.ApplicationService;
import com.app.service.ClubService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "clubs")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String listClubs() {
        return "clubs/list";
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Club> getClubs() {
        return clubService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createClub(@RequestBody Club club, RedirectAttributes redirectAttributes) {
        clubService.save(club);
        redirectAttributes.addAttribute("id", club.getId());
        return "redirect:/clubs/{id}";
    }

    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showClub(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("club", clubService.findOneById(id));
        return "clubs/show";
    }

    @PutMapping("/{id}")
    public String updateClub(@RequestBody Club club, RedirectAttributes redirectAttributes) {
        Club savedClub = clubService.save(club);
        redirectAttributes.addAttribute("id", savedClub.getId());
        return "redirect:/clubs/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteClub(@PathVariable UUID id, Model model) {
        clubService.delete(id);
        model.asMap().clear();
        return "redirect:/clubs";
    }

    @RequestMapping(value = "/create-form", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String createForm(Model model) {
        model.addAttribute("club", new Club());
        return "clubs/create";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("club", clubService.findOneById(id));
        return "clubs/edit";
    }

}

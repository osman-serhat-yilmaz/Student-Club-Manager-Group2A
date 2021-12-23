package com.app.controller;

import com.app.entity.ClubRole;
import com.app.entity.User;
import com.app.service.ClubRoleService;
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
@RequestMapping("/club-roles")
public class ClubRoleController {
    private final ClubRoleService clubRoleService;

    @Autowired
    public ClubRoleController(ClubRoleService clubRoleService) {
        this.clubRoleService = clubRoleService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getClubRoles(Model model) {
        model.addAttribute("club-roles", clubRoleService.findAll());
        return "club-roles/list";
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createClubRole(@RequestBody ClubRole clubRole, RedirectAttributes redirectAttributes) {
        clubRoleService.save(clubRole);
        redirectAttributes.addAttribute("id", clubRole.getId());
        return "redirect:/club-roles/{id}";
    }

    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showClubRole(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("club-role", clubRoleService.findOneById(id));
        return "club-roles/show";
    }

    @PutMapping("/{id}")
    public String updateClubRole(@RequestBody ClubRole clubRole, RedirectAttributes redirectAttributes) {
        ClubRole savedClubRole = clubRoleService.save(clubRole);
        redirectAttributes.addAttribute("id",savedClubRole.getId());
        return "redirect:/club-roles/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteClubRole(@PathVariable UUID id, Model model) {
        clubRoleService.delete(id);
        model.asMap().clear();
        return "redirect:/club-roles";
    }

    @RequestMapping(value = "/create-form", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String createForm(Model model) {
        model.addAttribute("club-role", new ClubRole());
        return "club-roles/create";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("club-role", clubRoleService.findOneById(id));
        return "club-roles/edit";
    }
}
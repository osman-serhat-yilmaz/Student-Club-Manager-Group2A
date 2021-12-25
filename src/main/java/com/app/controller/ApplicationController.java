package com.app.controller;

import com.app.entity.Application;
import com.app.entity.Club;
import com.app.entity.MyUserDetails;
import com.app.service.ApplicationService;
import com.app.service.ClubService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class ApplicationController {
    private final ApplicationService applicationService;

    @RequestMapping(value = "clubs/{id}/applications",method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createApplication(@PathVariable("id") UUID clubid, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Application application = new Application(((MyUserDetails)authentication.getPrincipal()).getUUID(),"pending", clubid);
        applicationService.save(application);
        redirectAttributes.addAttribute("id", clubid);
        return "redirect:/clubs/{id}";
    }

    /*@DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable UUID id, Model model) {
        applicationService.delete(id);
        model.asMap().clear();
        return "redirect:/clubs/applications";
    }*/

}
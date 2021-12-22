package com.app.controller;

import com.app.entity.Club;
import com.app.entity.Event;
import com.app.service.ClubService;
import com.app.service.EventService;
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
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String listEvents() {
        return "events/list";
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Event> getEvents() {
        return eventService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createEvent(@RequestBody Event event, RedirectAttributes redirectAttributes) {
        eventService.save(event);
        redirectAttributes.addAttribute("id", event.getId());
        return "redirect:/events/{id}";
    }

    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showEvent(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("event", eventService.findOneById(id));
        return "events/show";
    }

    @PutMapping("/{id}")
    public String updateEvent(@RequestBody Event event, RedirectAttributes redirectAttributes) {
        Event savedEvent = eventService.save(event);
        redirectAttributes.addAttribute("id", savedEvent.getId());
        return "redirect:/events/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable UUID id, Model model) {
        eventService.delete(id);
        model.asMap().clear();
        return "redirect:/events";
    }

    @RequestMapping(value = "/create-form", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String createForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("event", eventService.findOneById(id));
        return "events/edit";
    }

}

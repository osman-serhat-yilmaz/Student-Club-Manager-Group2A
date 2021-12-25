package com.app.controller;

import com.app.entity.Club;
import com.app.entity.Event;
import com.app.service.AttendanceService;
import com.app.service.EventService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class EventController {
    private final EventService eventService;
    private final AttendanceService attendanceService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getEvents(Model model) {
        List<Event> events = eventService.findAll();
        List<String> dates = new ArrayList<String>();
        for (Event event: events)
        {
            if(event.getDate() != null) {
                String date = Long.toString(event.getDate());
                date = date.substring(6) + "." + date.substring(4, 6) + "." + date.substring(0, 4);
                dates.add(date);
            }
            else
                dates.add("TBA");
        }

        model.addAttribute("dates", dates);
        model.addAttribute("events", eventService.findAll());  //put all the event objects in model as a List<Event>
        return "events/list";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createEvent(@RequestParam String name, @RequestParam String description,
                              @RequestParam String location, @RequestParam String eventdatemin,
                              RedirectAttributes redirectAttributes) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setLocation(location);
        event.setDate( Long.parseLong(eventdatemin.replace("-", "")) );
        eventService.save(event);
        redirectAttributes.addAttribute("id", event.getId());
        return "redirect:/events/{id}";
    }
    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showEvent(@PathVariable("id") UUID id, Model model) {
        Event event = eventService.findOneById(id);
        if(event.getDate() != null) {
            String date = Long.toString(event.getDate());
            date = date.substring(6) + "." + date.substring(4, 6) + "." + date.substring(0, 4);
            model.addAttribute("date", date);
        }
        else
            model.addAttribute("date", "TBA");
        model.addAttribute("event", event);
        return "/events/show";
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

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String createForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/create";
    }

    @RequestMapping(value = "/edit-form/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String editForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("event", eventService.findOneById(id));
        return "events/edit";
    }

    @RequestMapping(value = "/{id}/attendances", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String eventAttendancesPage(@PathVariable("id") UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("attendances", attendanceService.findAttendancesByEventID(id));
        return "/users/attendances";
    }

}

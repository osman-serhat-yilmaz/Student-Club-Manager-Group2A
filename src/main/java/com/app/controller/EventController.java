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
import java.util.*;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@NonNull}))
public class EventController {
    private final EventService eventService;
    private final AttendanceService attendanceService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getEvents(Model model) {
        List<Event> pastEvents = eventService.findEventsByStartDateBefore();
        List<Event> upcomingEvents = eventService.findEventsByStartDateAfter();

        model.addAttribute("pastDates", getDates(pastEvents));
        model.addAttribute("upcomingDates", getDates(upcomingEvents));
        model.addAttribute("pastEvents", pastEvents);
        model.addAttribute("upcomingEvents", upcomingEvents);
        return "events/list";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String createEvent(@RequestParam String name, @RequestParam String description,
                              @RequestParam String location, @RequestParam String startDate,
                              @RequestParam String endDate, @RequestParam String applicationDeadline,
                              @RequestParam int maxParticipants, RedirectAttributes redirectAttributes) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setLocation(location);
        event.setStartDate( Long.parseLong(startDate.replace("-", "")) );
        event.setEndDate( Long.parseLong(endDate.replace("-", "")) );
        event.setApplicationDeadline( Long.parseLong(applicationDeadline.replace("-", "")) );
        event.setMaxParticipants(maxParticipants);
        eventService.save(event);
        redirectAttributes.addAttribute("id", event.getId());
        return "redirect:/events/{id}";
    }
    //single item

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String showEvent(@PathVariable("id") UUID id, Model model) {
        Event event = eventService.findOneById(id);
        String date;
        if(event.getStartDate() != null && event.getEndDate() != null) {
            if(Objects.equals(event.getStartDate(), event.getEndDate())) {
                date = dateString(event.getStartDate());
            }
            else {
                date = dateString(event.getStartDate()) + " - " + dateString(event.getEndDate());
            }
        }
        else
            date = "TBA";

        model.addAttribute("date", date);
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

    //|||||||||||||||||||||||||||||||||||||||||||||||||

    public String dateString(Long longDate) {
        String date = Long.toString(longDate);
        return date.substring(6) + "." + date.substring(4, 6) + "." + date.substring(0, 4);
    }

    public List<String> getDates(List<Event> events){
        List<String> dates = new ArrayList<String>();
        for (Event event: events)
        {
            if(event.getStartDate() != null && event.getEndDate() != null) {
                if(event.getStartDate() == event.getEndDate()) {
                    dates.add(dateString(event.getStartDate()));
                }
                else {
                    dates.add( dateString(event.getStartDate()) + " - " + dateString(event.getEndDate()) );
                }
            }
            else
                dates.add("TBA");
        }
        return dates;
    }

}

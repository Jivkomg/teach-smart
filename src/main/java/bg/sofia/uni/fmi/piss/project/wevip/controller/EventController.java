package bg.sofia.uni.fmi.piss.project.wevip.controller;

import bg.sofia.uni.fmi.piss.project.wevip.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/events", produces = "application/json", consumes = "application/json")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/all")
    public ResponseEntity getEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/top30")
    public ResponseEntity getTop30Events() {
        return eventService.getTop30Events();
    }

    @PostMapping("/{eventId}")
    public ResponseEntity getEventById(@PathVariable long eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping("/organizers/{eventId}")
    public ResponseEntity getEventOrganizersById(@PathVariable long eventId) {
        return eventService.getEventOrganizersById(eventId);
    }

    @PostMapping("/performers/{eventId}")
    public ResponseEntity getEventPerformersById(@PathVariable long eventId) {
        return eventService.getEventPerformersById(eventId);
    }

    @PostMapping("/poster/{eventId}")
    public ResponseEntity getPoster(@PathVariable long eventId) {
        return eventService.getPoster(eventId);
    }
}

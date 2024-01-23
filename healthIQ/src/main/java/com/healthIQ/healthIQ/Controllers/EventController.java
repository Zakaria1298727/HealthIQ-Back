package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Models.Event;
import com.healthIQ.healthIQ.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService EventService;
    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody Event ev){

        return EventService.add(ev);
    }
    @GetMapping("/deleteEvent/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") String id){
       return this.EventService.deleteEvent(id);

    }
    @GetMapping("/AllEvent")
    public List<Event> getAllEvent(){
        return EventService.getAllEvents();
    }

    @GetMapping("/getOne/{id}")
    public Optional<Event> getOne(@PathVariable("id") String id){
        return this.EventService.getOne(id);
    }

}

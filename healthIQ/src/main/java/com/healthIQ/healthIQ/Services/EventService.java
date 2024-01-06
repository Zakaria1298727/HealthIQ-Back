package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Models.Event;
import com.healthIQ.healthIQ.Repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepo EventRepo;

    public Event add(Event ev){
        return (Event) this.EventRepo.save(ev);
    }
    public ResponseEntity<?> deleteEvent(String id){
     try{
          this.EventRepo.deleteById(id);
          return new ResponseEntity<>("Deleted Successfuly with id "+id, HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>("Failed to delet ", HttpStatus.NOT_FOUND);
    }
    }



    public List<Event> getAllEvents(){
        return (List<Event>) this.EventRepo.findAll();
    }

}

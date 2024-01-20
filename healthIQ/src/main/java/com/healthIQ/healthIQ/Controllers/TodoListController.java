package com.healthIQ.healthIQ.Controllers;

import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Models.Ordonnance;
import com.healthIQ.healthIQ.Models.Todolist;
import com.healthIQ.healthIQ.Services.TodoListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/ToDoList")
public class TodoListController {
    @Autowired
    private TodoListServices TodoListServices;
    @GetMapping("/getAll")
    public List<Todolist> getAll(){
        return this.TodoListServices.getAllToDoList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToDo(@RequestBody Todolist Or){
        try{
            this.TodoListServices.addToDo(Or);
            String successMessage = "to do bien envoyé à notre patient";
            return ResponseEntity.ok("{\"message\": \"" + successMessage + "\"}");



        }catch (Exception e){
            return new ResponseEntity<>("Failed ro add to do", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public Todolist checked(@PathVariable String id, @RequestBody String bool){
        return TodoListServices.Cheked(id,bool);
    }
    @PutMapping("/falseCheck/{id}")
    public Todolist chekedFalse(@PathVariable String id, @RequestBody String bool){
        return TodoListServices.NOCheked(id,bool);
    }
}

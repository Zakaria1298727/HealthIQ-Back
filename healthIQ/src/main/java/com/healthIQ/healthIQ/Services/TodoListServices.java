package com.healthIQ.healthIQ.Services;

import com.healthIQ.healthIQ.Models.Doctor;
import com.healthIQ.healthIQ.Models.Ordonnance;
import com.healthIQ.healthIQ.Models.Todolist;
import com.healthIQ.healthIQ.Repositories.TodoListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListServices {
    @Autowired
    private TodoListRepo TodoListRepo;

    public List<Todolist> getAllToDoList(){

        return  this.TodoListRepo.findAll();
    }
    public Todolist addToDo(Todolist Or){
        return this.TodoListRepo.save(Or);

    }
    public Todolist Cheked(String id, String bool){
        Todolist updated = TodoListRepo.findById(id).get();
        updated.setCheked(true);

        return TodoListRepo.save(updated);
    }
    public Todolist NOCheked(String id, String bool){
        Todolist updated = TodoListRepo.findById(id).get();
        updated.setCheked(false);

        return TodoListRepo.save(updated);
    }


}

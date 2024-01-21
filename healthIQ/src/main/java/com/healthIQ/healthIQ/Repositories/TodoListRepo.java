package com.healthIQ.healthIQ.Repositories;

import com.healthIQ.healthIQ.Models.Event;
import com.healthIQ.healthIQ.Models.Todolist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepo extends MongoRepository<Todolist, String> {

}

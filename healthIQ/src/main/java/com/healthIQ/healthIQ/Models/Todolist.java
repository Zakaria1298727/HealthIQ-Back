package com.healthIQ.healthIQ.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ToDoList")
public class Todolist {
    @Id
    private String id;

    private String Event;
    private Doctor id_Doc;
    private boolean cheked;

}

package com.tutorhub.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("modules")
@Getter
@Setter
public class Module {

    @Id
    private ObjectId id;

    private String name;
    private Content content;
    private List<Module> submodules;
    private Course course;

}

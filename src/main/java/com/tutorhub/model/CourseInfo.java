package com.tutorhub.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("courses_info")
@Getter
@Setter
public class CourseInfo {

    @Id
    private ObjectId id;

    private Student user;
    private Course course;
    private Progress progress;

}

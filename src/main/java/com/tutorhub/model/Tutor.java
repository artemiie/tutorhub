package com.tutorhub.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tutor extends User {

    private List<Course> courses;

    public Tutor() {
        this.role = "ROLE_TUTOR";
        this.courses = new ArrayList<>();
    }

}

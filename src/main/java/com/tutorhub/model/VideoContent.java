package com.tutorhub.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("video_contents")
@Getter
@Setter
public class VideoContent extends Content {

    private String videoURL;

}

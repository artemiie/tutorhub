package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoContentDTO extends ContentDTO {

    @JsonProperty(
            access = JsonProperty.Access.READ_ONLY
    )
    private String videoURL;

}

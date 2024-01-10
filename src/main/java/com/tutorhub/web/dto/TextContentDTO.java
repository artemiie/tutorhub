package com.tutorhub.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TextContentDTO extends ContentDTO {

    @NotNull(
            message = "Text must be not null."
    )
    @Size(
            max = 16384,
            message = "Text length must be less than {max}."
    )
    private String text;

}

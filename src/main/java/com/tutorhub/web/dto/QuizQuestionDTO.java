package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class QuizQuestionDTO {

  @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
  @Null(message = "Id must be null.", groups = OnCreate.class)
  private ObjectId id;

  @NotNull(message = "Title must be not null.")
  @Size(max = 1024, message = "Title length must be less than {max}.")
  private String title;

  @NotEmpty(message = "Options list must be not empty.")
  @NotNull(message = "Options list must be not null.")
  private List<@Size(max = 1024, message = "Option length must be less than {max}.") String>
      options;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<
          @PositiveOrZero(message = "Answer indexes list must not contain negative values.")
          Integer>
      answerIndexes;
}

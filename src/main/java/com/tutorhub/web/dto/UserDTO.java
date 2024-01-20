package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public abstract class UserDTO {

    @NotNull(
            message = "Id must be not null.",
            groups = OnUpdate.class
    )
    @Null(
            message = "Id must be null.",
            groups = OnCreate.class
    )
    @JsonSerialize(using = ToStringSerializer.class)
    protected ObjectId id;

    @NotNull(
            message = "Full name must be not null."
    )
    @Size(
            max = 100,
            message = "Full name length must be less than {max}."
    )
    protected String fullName;

    @Email(
            message = "Username must be a valid email."
    )
    @NotNull(
            message = "Username must be not null."
    )
    @Size(
            max = 100,
            message = "Username length must be less than {max}."
    )
    protected String username;

    @NotNull(
            message = "Password must be not null.",
            groups = OnCreate.class
    )
    @JsonProperty(
            access = JsonProperty.Access.WRITE_ONLY
    )
    protected String password;

}

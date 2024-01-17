package com.tutorhub.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Hidden
    @Schema(description = "User id", example = """
            {
                "timestamp": 0,
                "date": "2024-01-17T19:33:32.159Z"
            }""")
    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    @Null(message = "Id must be null.", groups = OnCreate.class)
    protected ObjectId id;

    @Schema(description = "User full name", example = "john Doe")
    @NotNull(message = "Full name must be not null.")
    @Size(max = 100, message = "Full name length must be less than {max}.")
    protected String fullName;

    @Schema(description = "User username(email)", example = "johndoe@gmail.com")
    @Email(message = "Username must be a valid email.")
    @NotNull(message = "Username must be not null.")
    @Size(max = 100, message = "Username length must be less than {max}.")
    protected String username;

    @Schema(description = "User crypted password",
            example = "$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m")
    @NotNull(message = "Password must be not null.", groups = OnCreate.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;

}

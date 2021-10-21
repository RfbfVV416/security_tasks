package org.university.security_tasks.dto;

import lombok.Data;
import org.university.security_tasks.annotations.ValidPassword;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    @NotEmpty(message="Please enter an email")
    @Email(message="Email is not valid")
    private String email;

    @ValidPassword
    private String password;
}

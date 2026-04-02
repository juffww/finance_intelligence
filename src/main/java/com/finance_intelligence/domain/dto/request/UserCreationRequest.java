package com.finance_intelligence.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationRequest {
    @NotBlank(message = "Email is not blank")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Full name is not blank")
    @Size(min = 2, max = 255, message = "Full name must have size from 2 to 255")
    private String fullName;

    @NotBlank(message = "Password is not blank")
    @Size(min = 6, message = "Password have min size 6")
    private String password;
}

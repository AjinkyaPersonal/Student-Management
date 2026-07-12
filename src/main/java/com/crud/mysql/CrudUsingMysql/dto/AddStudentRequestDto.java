package com.crud.mysql.CrudUsingMysql.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDto {

    @NotBlank(message = "Name can not be null")
    @Size(min = 3, max = 30, message = "Name must be of min 3 character and max 30 character")
    private String name;

    @Email
    @NotBlank(message = "Email can not be null")
    private String email;
}

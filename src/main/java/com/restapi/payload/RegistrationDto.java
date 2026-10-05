package com.restapi.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RegistrationDto {
    private Long id;
    private String name;
    private String emailId;
    private String mobile;
}

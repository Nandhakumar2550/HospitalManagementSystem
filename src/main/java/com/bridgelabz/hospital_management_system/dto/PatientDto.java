package com.bridgelabz.hospital_management_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Disease is required")
    private String disease;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;
}
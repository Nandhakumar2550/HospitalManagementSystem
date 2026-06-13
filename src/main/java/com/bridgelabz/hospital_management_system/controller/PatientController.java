package com.bridgelabz.hospital_management_system.controller;

import com.bridgelabz.hospital_management_system.dto.PatientDto;
import com.bridgelabz.hospital_management_system.entity.Patient;
import com.bridgelabz.hospital_management_system.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public Patient addPatient(
            @RequestBody PatientDto dto) {

        return patientService.createPatient(dto);
    }

    @GetMapping
    public List<Patient> getAllPatients() {

        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(
            @PathVariable Long id) {

        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody PatientDto dto) {

        return patientService.updatePatient(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return "Patient deleted successfully";
    }
}
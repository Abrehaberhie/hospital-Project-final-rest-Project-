package com.binary.hospital.service;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientService {
    public List<Patient> getAllPatient();
    public Patient  createPatient(Patient patient);
    public Patient updatePatient(int id, Patient updatedPatient);
    public void deletPatient(int id);
    public Patient getPatientById(int id);
}

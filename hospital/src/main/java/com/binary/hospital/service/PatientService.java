package com.binary.hospital.service;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientService {
    List<Patient> getAllPatient();
    Patient createPatient(Patient patient);
    Patient updatePatient(int id, Patient updatedPatient);
    void deletePatient(int id);
    Patient getPatientById(int id);

}

package com.binary.hospital.service;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Patient;
import com.binary.hospital.repository.DoctorRepository;
import com.binary.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createPatient(Patient patient) {
        if (patient.getDoctor() != null && patient.getDoctor().getId() == null) {
            Doctor savedDoctor = doctorRepository.save(patient.getDoctor());
            patient.setDoctor(savedDoctor);
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(int id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient == null) {
            return null;
        }
        existingPatient.setFirstName(updatedPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName());
        existingPatient.setType(updatedPatient.getType());
        existingPatient.setSeverityOfPain(updatedPatient.getSeverityOfPain());
        existingPatient.setAge(updatedPatient.getAge());

        if (updatedPatient.getDoctor() != null && updatedPatient.getDoctor().getId() == null) {
            Doctor savedDoctor = doctorRepository.save(updatedPatient.getDoctor());
            existingPatient.setDoctor(savedDoctor);
        } else {
            existingPatient.setDoctor(updatedPatient.getDoctor());
        }

        return patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getPatientById(int id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.orElse(null);
    }

}

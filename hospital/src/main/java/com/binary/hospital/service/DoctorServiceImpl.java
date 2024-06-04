package com.binary.hospital.service;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Patient;
import com.binary.hospital.repository.DoctorRepository;
import com.binary.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        List<Patient> patients = doctor.getPatient();
        doctor.setPatient(null);

        Doctor savedDoctor = doctorRepository.save(doctor);

        if (patients != null) {
            for (Patient patient : patients) {
                patient.setDoctor(savedDoctor);
                patientRepository.save(patient);
            }
            savedDoctor.setPatient(patients);
        }

        return doctorRepository.save(savedDoctor);
    }

    @Override
    public Doctor updateDoctor(int id, Doctor updatedDoctor) {
        Doctor existingDoc = doctorRepository.findById(id).orElse(null);
        if (existingDoc == null) {
            return null;
        }

        existingDoc.setFirstname(updatedDoctor.getFirstname());
        existingDoc.setLastclass(updatedDoctor.getLastclass());
        existingDoc.setHospitalcenter(updatedDoctor.getHospitalcenter());
        existingDoc.setSpeciality(updatedDoctor.getSpeciality());
        existingDoc.setAge(updatedDoctor.getAge());

        List<Patient> patients = updatedDoctor.getPatient();
        if (patients != null) {
            for (Patient patient : patients) {
                patient.setDoctor(existingDoc);
                patientRepository.save(patient);
            }
            existingDoc.setPatient(patients);
        }

        return doctorRepository.save(existingDoc);
    }

    @Override
    public void deletDoctor(int id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }

}

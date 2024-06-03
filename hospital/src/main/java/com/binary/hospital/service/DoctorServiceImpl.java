package com.binary.hospital.service;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Patient;
import com.binary.hospital.repository.DoctorRepository;
import com.binary.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

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

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(int id, Doctor updatedDoctor) {
        Doctor existingDoc=doctorRepository.findById(updatedDoctor.getId()).orElse(null);
        existingDoc.setId(updatedDoctor.getId());
        existingDoc.setFirstname(updatedDoctor.getFirstname());
        existingDoc.setSpeciality(updatedDoctor.getSpeciality());
        existingDoc.setAge(updatedDoctor.getAge());
        existingDoc.setHospitalcenter(updatedDoctor.getHospitalcenter());
        existingDoc.setPatient((List<Patient>) updatedDoctor.getPatient());


        return doctorRepository.save(existingDoc);
    }


    public void deletDoctor(int id)
    {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor getDoctorById(int id) {
        Optional<Doctor> optionalDoctor=doctorRepository.findById(id);
        if(optionalDoctor.isPresent())
        {
           return optionalDoctor.get();
        }
        return null;
    }
}

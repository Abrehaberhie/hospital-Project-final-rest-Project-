package com.binary.hospital.service;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Enrolement;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DoctorService {

    public List<Doctor> getAllDoctors();
    public Doctor  createDoctor(Doctor doctor);
    public Doctor updateDoctor(int id, Doctor updatedDoctor);
    public void deletDoctor(int id);
    public Doctor getDoctorById(int id);



}

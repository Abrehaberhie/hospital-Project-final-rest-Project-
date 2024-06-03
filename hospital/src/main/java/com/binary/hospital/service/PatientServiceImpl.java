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

       if(patient.getDoctor()!=null&&patient.getDoctor().getId()==null)
       {
            Doctor savedDoctor=doctorRepository.save(patient.getDoctor());
            patient.setDoctor(savedDoctor);
        }
        return patientRepository.save(patient);
   }




    @Override
    public Patient updatePatient(int id, Patient updatedPatient) {
        Patient existintPatient=patientRepository.findById(updatedPatient.getId()).orElse(null);
       existintPatient.setDoctor(updatedPatient.getDoctor());
        existintPatient.setId(updatedPatient.getId());
        existintPatient.setAge(updatedPatient.getAge());
        existintPatient.setFirstName(updatedPatient.getFirstName());
        existintPatient.setLastName(updatedPatient.getLastName());
        existintPatient.setType(updatedPatient.getType());
        existintPatient.setSeverityOfPain(updatedPatient.getSeverityOfPain());
        if(updatedPatient.getDoctor()!=null&&updatedPatient.getDoctor().getId()==null)
        {
           Doctor savedDoctor = doctorRepository.save(updatedPatient.getDoctor());
            existintPatient.setDoctor(savedDoctor);
        }
        return patientRepository.save(existintPatient);


    }

    @Override
    public void deletPatient(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient getPatientById(int id) {
        Optional<Patient> optionalPatient=patientRepository.findById(id);
        if(optionalPatient.isPresent())
        {
            return optionalPatient.get();
        }
        return null;
    }

}

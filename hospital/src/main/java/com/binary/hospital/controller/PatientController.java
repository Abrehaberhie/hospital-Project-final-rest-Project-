package com.binary.hospital.controller;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Patient;
import com.binary.hospital.service.PatientServiceImpl;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")

public class PatientController {
    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Patient> savePatient(@RequestBody @Valid Patient patient) {
        return new ResponseEntity<>(patientServiceImpl.createPatient(patient), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(patientServiceImpl.getPatientById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient() {
        List<Patient> list = new ArrayList<>();
        patientServiceImpl.getAllPatient().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient updatedPatient, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(patientServiceImpl.updatePatient(id, updatedPatient), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable("id") Integer id) {
        patientServiceImpl.deletePatient(id);
        return new ResponseEntity<>("Data has been deleted", HttpStatus.NO_CONTENT);

}
    /*
    @Autowired
    private PatientServiceImpl patientServiceImpl;
    @PostMapping("/create")
    public ResponseEntity<Patient> savePatient(@RequestBody @Valid Patient patient)
    {
        return new ResponseEntity<>(patientServiceImpl.createPatient(patient), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(patientServiceImpl.getPatientById(id),HttpStatus.FOUND);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient()
    {
        List<Patient> list= new ArrayList<Patient>();
        patientServiceImpl.getAllPatient().forEach(list::add);


        return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient>  updatePatient(@RequestBody Patient updatedPatient, @PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(patientServiceImpl.updatePatient(id,updatedPatient) ,HttpStatus.OK);


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletPatientById(@PathVariable("id") Integer id)
    {
        patientServiceImpl.deletPatient(id);

        return new ResponseEntity<String>("Data has been deleted",HttpStatus.NO_CONTENT);
    }

     */
}

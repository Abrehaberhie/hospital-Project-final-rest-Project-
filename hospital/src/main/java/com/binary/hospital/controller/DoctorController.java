package com.binary.hospital.controller;

import com.binary.hospital.model.Doctor;
import com.binary.hospital.service.DoctorServiceImpl;
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
@RequestMapping("/api/v1/doctor")

public class DoctorController {
    @Autowired
    private DoctorServiceImpl doctorServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<?> saveDoctor(@RequestBody @Valid Doctor doctor)
    {
        return new ResponseEntity<>(doctorServiceImpl.createDoctor(doctor), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(doctorServiceImpl.getDoctorById(id),HttpStatus.FOUND);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors()
    {
        List<Doctor> list= new ArrayList<Doctor>();
        doctorServiceImpl.getAllDoctors().forEach(list::add);


        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Doctor>  updateDoctor(@RequestBody Doctor updatedDoctor, @PathVariable("id") Integer id)
    {
        return new ResponseEntity<>( doctorServiceImpl.updateDoctor(id,updatedDoctor),HttpStatus.OK);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletDoctorById(@PathVariable("id") Integer id)
    {
        doctorServiceImpl.deletDoctor(id);

        return new ResponseEntity<String>("Data has been deleted",HttpStatus.NO_CONTENT);
    }

}
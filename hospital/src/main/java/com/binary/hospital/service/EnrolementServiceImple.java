package com.binary.hospital.service;

import com.binary.hospital.model.Enrolement;
import com.binary.hospital.repository.EnrolementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolementServiceImple implements EnrolementService{
    @Autowired
   private EnrolementRepository enrolementRepository;
    @Override
    public List<Enrolement> getAllMembers() {
        return enrolementRepository.findAll();
    }

    @Override
    public Enrolement createMember(Enrolement enrolement) {
        return enrolementRepository.save(enrolement);
    }

    @Override
    public Enrolement updateMember(int id, Enrolement updatedEnrolement) {
        return null;
    }

    @Override
    public Integer deleteMember(int id) {
        return null;
    }

    @Override
    public Enrolement getMemberById(int id) {
        Optional<Enrolement> optionalEnrolement=enrolementRepository.findById(id);
        if(optionalEnrolement.isPresent())
        {
            return optionalEnrolement.get();
        }
        return null;
    }

    @Override
    public Enrolement getMembersByEmail(String email) {
        Optional<Enrolement> optionalEnrolement=enrolementRepository.findByEmail(email);
        if(optionalEnrolement.isPresent())
        {
          return   optionalEnrolement.get();
        }
        return null;
    }
}

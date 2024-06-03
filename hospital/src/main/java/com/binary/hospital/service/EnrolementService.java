package com.binary.hospital.service;

import com.binary.hospital.model.Enrolement;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnrolementService {
    public List<Enrolement> getAllMembers();
    public Enrolement  createMember(Enrolement enrolement);
    public Enrolement updateMember(int id, Enrolement updatedEnrolement);
    public Integer deleteMember(int id);
    public Enrolement getMemberById(int id);
    public Enrolement getMembersByEmail(String email);
}

package com.binary.hospital.repository;

import com.binary.hospital.model.Enrolement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EnrolementRepository extends JpaRepository<Enrolement,Integer> {

    public Optional<Enrolement> findByEmail(String Email);
}

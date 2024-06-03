package com.binary.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "fname Not to be empity")
    private String firstname;
    @NotBlank
    private String lastclass;
    @NotBlank
    private String hospitalcenter;
    @NotBlank
    private String speciality;
    @Min(10)
    @Max(60)
    private int age;

  // @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor",fetch = FetchType.EAGER)
   // @JsonIgnore

  // @JsonManagedReference
   @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Patient> patient;
}

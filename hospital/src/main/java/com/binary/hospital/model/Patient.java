package com.binary.hospital.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "fname Not to be empity")
    private String firstName;
    @NotBlank
    private String lastName;
    private String type;
    private  String severityOfPain;
    @Min(6)
    @Max(45)
    private int age;
//   @ManyToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name = "patient")
 //  private Doctor doctor;

}

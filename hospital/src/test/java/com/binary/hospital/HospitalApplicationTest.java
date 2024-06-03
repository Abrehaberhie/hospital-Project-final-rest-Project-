package com.binary.hospital;

import com.binary.hospital.controller.DoctorController;
import com.binary.hospital.controller.PatientController;
import com.binary.hospital.model.Doctor;
import com.binary.hospital.model.Patient;
import com.binary.hospital.service.DoctorServiceImpl;
import com.binary.hospital.service.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HospitalApplicationTest {

	@InjectMocks
	private DoctorController doctorController;
	@Mock
	private DoctorServiceImpl doctorServiceImple;

	@InjectMocks
	private PatientController patientController;
	@Mock
	private PatientServiceImpl patientServiceImpl;



	@Test
	@DisplayName("Doctor controller get all doctor success test case")
	public void doctorontroller_getAllDoctor()
	{
		List<Doctor> expectedDoctors = new ArrayList<Doctor>();
		Doctor doctor = new Doctor();
		doctor.setId(1);
		doctor.setFirstname("jimi");
		doctor.setSpeciality("brain");
		expectedDoctors.add(doctor);
		expectedDoctors.add(new Doctor());


		Mockito.when(doctorServiceImple.getAllDoctors()).thenReturn(expectedDoctors);
		ResponseEntity<List<Doctor>> result= doctorController.getAllDoctors();

		//Assertions.assertEquals(2,result.getBody().size());
		//Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals(2,result.getBody().size());
		Assertions.assertEquals(HttpStatus.OK,result.getStatusCode());

	}
	@Test
	@DisplayName("Patient controller get all patient")
	public void patientController_getAllPatient()
	{
		List<Patient> expectedPatients = new ArrayList<Patient>();

		Patient patient=new Patient();
		patient.setFirstName("alex");
		patient.setLastName("robe");
		patient.setSeverityOfPain("sever");
		expectedPatients.add(patient);
		expectedPatients.add(new Patient());


		Mockito.when(patientServiceImpl.getAllPatient()).thenReturn(expectedPatients);
		ResponseEntity<List<Patient>> result= patientController.getAllPatient();

		//Assertions.assertEquals(2,result.getBody().size());
		//Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		Assertions.assertEquals(2,result.getBody().size());
		Assertions.assertEquals(HttpStatus.OK,result.getStatusCode());

	}
	@Test
	@DisplayName("Doctor controller get doctor by ID test case")
	public void doctorController_getDoctoeById()

	{
       Doctor doctor = new Doctor();
	   doctor.setId(1);

		Integer x= doctor.getId();

		Mockito.when(doctorServiceImple.getDoctorById(x)).thenReturn(doctor);

		ResponseEntity<Doctor> result = (ResponseEntity<Doctor>) doctorController.getDoctorById(x);


		Assertions.assertEquals(x, result.getBody().getId());
		Assertions.assertEquals(HttpStatus.FOUND, result.getStatusCode());

	}
	@Test
	@DisplayName("Doctor controller get doctor by ID test case")
	public void patientController_getPatientById()

	{

		Patient patient = new Patient();
		patient.setId(1);

		Integer x= patient.getId();

		Mockito.when(patientServiceImpl.getPatientById(x)).thenReturn(patient);

		ResponseEntity<Patient> result = (ResponseEntity<Patient>) patientController.getPatientById(x);


		Assertions.assertEquals(x, result.getBody().getId());
		Assertions.assertEquals(HttpStatus.FOUND, result.getStatusCode());

	}

	@Test
	@DisplayName("Doctor controller createDoctor  test case")
	public void doctorController_createDoctor() {

		Doctor doctorBeforeCreated = new Doctor();
		doctorBeforeCreated.setFirstname("alex");


		Doctor createdDoctor = new Doctor();
		createdDoctor.setId(1);
		createdDoctor.setFirstname(doctorBeforeCreated.getFirstname());


		Mockito.when(doctorServiceImple.createDoctor(Mockito.any())).thenReturn(createdDoctor);

		ResponseEntity<Doctor> result = (ResponseEntity<Doctor>) doctorController.saveDoctor(doctorBeforeCreated);

		Assertions.assertEquals(doctorBeforeCreated.getFirstname(), result.getBody().getFirstname());
		Assertions.assertNotNull(result.getBody().getId());
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());

	}
	@Test
	@DisplayName("patient controller createPatient  test case")
	public void patientController_createPatient() {

		Patient patientBeforeCreated = new Patient();
		patientBeforeCreated.setFirstName("bruk");


		Patient createdPatient = new Patient();
		createdPatient.setId(1);
		createdPatient.setFirstName(patientBeforeCreated.getFirstName());


		Mockito.when(patientServiceImpl.createPatient(Mockito.any())).thenReturn(createdPatient);

		ResponseEntity<Patient> result = (ResponseEntity<Patient>) patientController.savePatient(patientBeforeCreated);

		Assertions.assertEquals( patientBeforeCreated.getFirstName(), result.getBody().getFirstName());
		Assertions.assertNotNull(result.getBody().getId());
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());

	}
	@Test
	@DisplayName("Car controller delete Car success test case")
	public void doctorController_deleteDoctor(){
		String deletedPatient="Data has been deleted";
		Integer docIdNeedToBeDeleted=2;

		//Mockito.when(carServiceImpl.deleteCar(carIdThatNeedToBeDeleted)).thenReturn(carIdThatNeedToBeDeleted);
		//Mockito.when(doctorServiceImple.deletDoctor(docIdNeedToBeDeleted)).thenReturn(docIdNeedToBeDeleted);

		ResponseEntity<String> result = doctorController.deletDoctorById(docIdNeedToBeDeleted);

		Assertions.assertEquals(deletedPatient, result.getBody());
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());


	}
	@Test
	@DisplayName("Car controller delete Car success test case")
	public void patientController_deletePatient(){
		String deletedPatient="Data has been deleted";
		Integer patientIdNeedToBeDeleted=2;

		//Mockito.when(carServiceImpl.deleteCar(carIdThatNeedToBeDeleted)).thenReturn(carIdThatNeedToBeDeleted);
		//Mockito.when(doctorServiceImple.deletDoctor(docIdNeedToBeDeleted)).thenReturn(docIdNeedToBeDeleted);

		ResponseEntity<String> result = patientController.deletPatientById(patientIdNeedToBeDeleted);

		Assertions.assertEquals(deletedPatient, result.getBody());
		Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());


	}

	@Test
	@DisplayName("Doctor controller update doctor  test case")
	public void doctorController_updateDoctor() {

		Doctor updates = new Doctor();
		updates.setFirstname("Alex");
		updates.setAge(34);

		Integer doctorIdThatNeedToBeUpdated = 4;

		Doctor updatedDoctor = new Doctor();
		updatedDoctor.setId(doctorIdThatNeedToBeUpdated);
		updatedDoctor.setAge(updates.getAge());
		updatedDoctor.setFirstname(updates.getFirstname());


		Mockito.when(doctorServiceImple.updateDoctor(doctorIdThatNeedToBeUpdated, updates )).thenReturn(updatedDoctor);
		ResponseEntity<Doctor> result = doctorController.updateDoctor(updates, doctorIdThatNeedToBeUpdated);

		Assertions.assertEquals(updatedDoctor.getFirstname(), result.getBody().getFirstname());
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		//Assertions.assertEquals(carIdThatNeedToBeUpdated, result.getBody().getId());
	}

	@Test
	@DisplayName("Patient controller update patient  test case")
	public void patientController_updatePatient() {

		Patient updates = new Patient();
		updates.setFirstName("alex");
		updates.setAge(34);

		Integer patienIdThatNeedToBeUpdated = 4;

		Patient updatedPatient = new Patient();
		updatedPatient.setId(patienIdThatNeedToBeUpdated);
		updatedPatient.setAge(updates.getAge());
		updatedPatient.setFirstName(updates.getFirstName());


		Mockito.when(patientServiceImpl.updatePatient(patienIdThatNeedToBeUpdated, updates )).thenReturn(updatedPatient);
		ResponseEntity<Patient> result =  patientController.updatePatient(updates, patienIdThatNeedToBeUpdated);

		Assertions.assertEquals(updatedPatient.getFirstName(), result.getBody().getFirstName());
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		//Assertions.assertEquals(carIdThatNeedToBeUpdated, result.getBody().getId());
	}



}

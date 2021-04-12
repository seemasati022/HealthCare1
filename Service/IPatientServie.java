package com.slokam.healthcare.Service;

import com.slokam.healthcare.Entity.Patient;
import com.slokam.healthcare.Pojo.PatientSearchPojo;

import java.util.List;

public interface IPatientServie {
    public void patientRegistration(Patient patient);
    //public List<Patient> criteriaTest(String name , String email);
    public List<Patient> patientSearchPojo(PatientSearchPojo patientSearchPojo);
    public List<Patient> getAllPatients();
    public List<Patient> getPatientBetweenAge();
    public List<Patient> getAllPatientNames();
    public List<Patient> getPatientByGender();
    public List<Patient> getPatientByAge();

    public Patient getPatientById(Integer id);

}

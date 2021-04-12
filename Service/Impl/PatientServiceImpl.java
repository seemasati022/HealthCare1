package com.slokam.healthcare.Service.Impl;

import com.slokam.healthcare.Entity.Patient;
import com.slokam.healthcare.Pojo.PatientSearchPojo;
import com.slokam.healthcare.Repo.IPatientRepo;
import com.slokam.healthcare.Repo.PatientCriteriaRepo;
import com.slokam.healthcare.Service.IPatientServie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements IPatientServie {
    Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
    @Autowired
    private IPatientRepo patientRepo;

    @Autowired
    private PatientCriteriaRepo patientCriteria;

    @Override
    public void patientRegistration(Patient patient) {
        patient.setRegData(new Date());
        patientRepo.save(patient);
    }

    @Override
    public List<Patient> patientSearchPojo(PatientSearchPojo patientSearchPojo) {
        LOGGER.trace("enterd into patientsearchpojo");
        LOGGER.debug("search data"+patientSearchPojo);
        List<Patient> patientList = patientCriteria.patientFullSearch(patientSearchPojo);
        LOGGER.debug("search result:"+patientList);
        LOGGER.trace("exit from patient search");
        return patientList;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    @Override
    public List<Patient> getPatientBetweenAge() {
        return patientRepo.findAll();
    }

    @Override
    public List<Patient> getAllPatientNames() {
        return patientRepo.findAll();
    }

    @Override
    public List<Patient> getPatientByGender() {
        return patientRepo.findAll();
    }

    @Override
    public List<Patient> getPatientByAge() {
        return patientRepo.findAll();
    }


    @Override
    public Patient getPatientById(Integer id) {
        Optional<Patient> patientOptional =patientRepo.findById(id);
        if (patientOptional.isPresent()){
            return patientOptional.get();
        }
        return null;
    }

}

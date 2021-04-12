package com.slokam.healthcare.Controller;

import com.slokam.healthcare.Entity.Patient;
import com.slokam.healthcare.Pojo.HealthCareException;
import com.slokam.healthcare.Pojo.PatientSearchPojo;
import com.slokam.healthcare.Service.IPatientServie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("patient")
public class PatientController {
    Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private IPatientServie patientServie;
    @Value("${app.file.upload.location}")
    private String uploadLocation;


    @PostMapping("/Images")
    public ResponseEntity<String> savePatientImage(MultipartFile patientImage) throws HealthCareException{
        LOGGER.trace("this is trace statemnet");
        LOGGER.debug("this is debug information");
        LOGGER.info("this is info statemnet");
        LOGGER.warn("this is warn statemnet");
        LOGGER.error("this is error statemnet");
        String name = System.currentTimeMillis()+"-"+patientImage.getOriginalFilename();
       try {
           patientImage.transferTo(new File(uploadLocation+name));
       }catch (IllegalStateException e){
           e.printStackTrace();
       }catch (IOException e){
           LOGGER.error("Exception while uploading image file");
           throw new HealthCareException("Specified file upload is not available",e,8000);
       }
        return new ResponseEntity<String>(name, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient){
        patientServie.patientRegistration(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
    }
   /* @GetMapping("/criteriaTest/{name}/{email}")
    public ResponseEntity<List<Patient>> criteriaTest(@PathVariable String name,String email){
       List<Patient> list = patientServie.criteriaTest(name, email);
       return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);
    }*/
   @PostMapping("/search")
   public ResponseEntity<List<Patient>> patientSearch(@RequestBody PatientSearchPojo patientSearchPojo) {
       LOGGER.trace("entered into patientsearch");
       LOGGER.debug("search data"+ patientSearchPojo);
       List<Patient> list=  patientServie.patientSearchPojo(patientSearchPojo);
       LOGGER.debug("search result:"+list);
       LOGGER.trace("exit from patientsearchpojo");
       return ResponseEntity.ok(list);
   }
   @GetMapping("/all")
   public ResponseEntity<List<Patient>> getAllPatient(){
       List<Patient> patientList = patientServie.getAllPatients();
       return ResponseEntity.ok(patientList);
   }
    @GetMapping("/getevenpatients")
    public ResponseEntity<List<Patient>> getAllPatient1(){
        List<Patient> patientlist = patientServie.getAllPatients().stream()
                .filter(patient ->  nullCheckForPatient(patient))
               .collect(Collectors.toList());
        return ResponseEntity.ok(patientlist);
        /*return ResponseEntity.ok(patientServie.getAllPatients().stream()
                .filter(patient ->  patient.getId()%2==0)
                .collect(Collectors.toList()));*/
    }
    @GetMapping("/getPatientByAge")
    public ResponseEntity<List<Patient>> getPatientByAge(){
        List<Patient> patientlist = patientServie.getAllPatients().stream()
                .filter(patient ->  nullCheckForPatient(patient))
                .collect(Collectors.toList());
        return ResponseEntity.ok(patientlist);
    }

    @GetMapping("/getPatientByGender")
    public ResponseEntity<List<Patient>> getPatientByGender(){
        List<Patient> patientlist = patientServie.getAllPatients().stream()
                .filter(patient ->  nullCheckForPatient(patient))
                .collect(Collectors.toList());
        return ResponseEntity.ok(patientlist);
    }

    @GetMapping("/getPatientByAgeBetween")
    public ResponseEntity<List<Patient>> getPatientBetweenAge(){
        return ResponseEntity.ok(patientServie.getPatientBetweenAge().stream()
                .filter(patient -> patient.getAge()>=40&&patient.getAge()<=60).collect(Collectors.toList()));
    }

    Function<Patient,Patient> fun = patient ->
    {patient.getName().compareTo(patient.getName());
    return patient;
    };
    @GetMapping("/getAllNames")
    public ResponseEntity<List<Patient>> getAllPatientNames(){
        return ResponseEntity.ok(patientServie.getAllPatientNames().stream()
        .map(fun).collect(Collectors.toList()));
    }

    @GetMapping("/getAllNames1")
    public ResponseEntity<List<String>> getAllPatientNames1(){
        return ResponseEntity.ok(patientServie.getAllPatientNames().stream()
                .map(func->func.getName()).collect(Collectors.toList()));
    }

   @GetMapping("/byId/{id}")
   public ResponseEntity<Patient> getPatientById(@PathVariable Integer id){
       Patient patient=patientServie.getPatientById(id);
       return ResponseEntity.ok(patient);
   }

   public boolean nullCheckForPatient(Patient patient){
        boolean result=false;
        if (Objects.nonNull(patient) && patient.getId() != null
                && patient.getId()%2==0 && patient.getAge()!= null
                && patient.getGender()!= null){
            result=true;
        }
        return result;
   }

}


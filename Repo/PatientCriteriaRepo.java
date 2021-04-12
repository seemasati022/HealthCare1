package com.slokam.healthcare.Repo;

import com.slokam.healthcare.Entity.Patient;
import com.slokam.healthcare.Pojo.PatientSearchPojo;
import com.slokam.healthcare.Util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PatientCriteriaRepo {
    Logger LOGGER = LoggerFactory.getLogger(PatientCriteriaRepo.class);
    @Autowired
    private EntityManager entitymanaer;

    public List<Patient> patientFullSearch(PatientSearchPojo patientSearchPojo){
        List<Predicate> predicateList = new ArrayList<>();

        LOGGER.trace("entered into patientFullSearch");
        LOGGER.debug("search data : "+patientSearchPojo);
        CriteriaBuilder criteriaBuilder = entitymanaer.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> root = criteriaQuery.from(Patient.class);

        if (patientSearchPojo != null){
            if (StringUtils.blankCheck(patientSearchPojo.getName())){
                LOGGER.trace("entered into name Predicate");
                predicateList.add(criteriaBuilder.like(root.get("name"),patientSearchPojo.getName()+"%"));
            }
            if (patientSearchPojo.getPhone() != null){
                LOGGER.trace("entered into phone Predicate");
                predicateList.add(criteriaBuilder.equal(root.get("phone"),patientSearchPojo.getPhone()));
            }
            if (patientSearchPojo.getFromdate() != null){
                LOGGER.trace("entered into fromDate Predicate");
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("regDate"),patientSearchPojo.getFromdate()));
            }if (patientSearchPojo.getTodate() != null){
                LOGGER.trace("entered into Todate Predicate");
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("regDate"),patientSearchPojo.getTodate()));
            }
            if (patientSearchPojo.getStartingAge() != null){
                LOGGER.trace("entered into Start age Predicate");
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"),patientSearchPojo.getStartingAge()));
            }
            if (patientSearchPojo.getEndingAge() != null){
                LOGGER.trace("entered into End age Predicate");
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"),patientSearchPojo.getEndingAge()));
            }
        }
        criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
        TypedQuery<Patient> patientTypedQuery = entitymanaer.createQuery(criteriaQuery);
        List<Patient> patientList = patientTypedQuery.getResultList();
        LOGGER.debug("search result :"+ patientList);
        LOGGER.trace("exit from patient full search");
        return patientList;
        }
    }




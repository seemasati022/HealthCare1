package com.slokam.healthcare.Service.Impl;

import com.slokam.healthcare.Repo.IVisitingRepo;
import com.slokam.healthcare.Service.IVisitngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VisitingServiceImpl implements IVisitngService {

    @Autowired
    private IVisitingRepo visitingRepo;

    @Override
    public List<Object[]> getVisitingsByPatientId(Integer id) {
        return visitingRepo.getVisitingsByPatientId(id);
    }
}

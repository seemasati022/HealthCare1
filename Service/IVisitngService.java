package com.slokam.healthcare.Service;

import java.util.List;

public interface IVisitngService {
    public List<Object[]> getVisitingsByPatientId(Integer id);
}

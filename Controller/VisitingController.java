package com.slokam.healthcare.Controller;

import com.slokam.healthcare.Service.IVisitngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/visiting")
public class VisitingController {
    @Autowired
    private IVisitngService visitngService;
    @GetMapping("/byPatientId/{id}")
    public ResponseEntity<List<Object[]>> getVisitingsByPatientId(@PathVariable Integer id){
        return ResponseEntity.ok(visitngService.getVisitingsByPatientId(id));
    }
}

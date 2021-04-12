package com.slokam.healthcare.Repo;

import com.slokam.healthcare.Entity.Visiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IVisitingRepo extends JpaRepository<Visiting,Integer> {

    @Query("select p.name,v.dateAndTime from Visiting v join v.appointment a join a.patient p where p.id=?1")
    public List<Object[]> getVisitingsByPatientId(Integer id);
}

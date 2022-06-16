package com.SelectionCommittee.SelectionCommittee.repositories;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacultiesRepository extends CrudRepository<FacultiesEntity, Long> {
    List<FacultiesEntity> findAllByOrderByFacultyNameAsc();
    List<FacultiesEntity> findAllByOrderByFacultyNameDesc();
    List<FacultiesEntity> findAllByOrderByBudgetSeatsDesc();
    List<FacultiesEntity> findAllByOrderByTotalSeatsDesc();
}

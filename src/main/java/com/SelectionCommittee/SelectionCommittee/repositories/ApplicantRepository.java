package com.SelectionCommittee.SelectionCommittee.repositories;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import org.springframework.data.repository.CrudRepository;

public interface ApplicantRepository extends CrudRepository<ApplicantEntity, Long> {
    ApplicantEntity findByLastNameAndNameAndSurname(String lastname, String name, String surname);

    int countAllBy();
}

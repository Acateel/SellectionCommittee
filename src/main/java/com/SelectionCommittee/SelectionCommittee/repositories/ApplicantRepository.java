package com.SelectionCommittee.SelectionCommittee.repositories;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicantRepository extends PagingAndSortingRepository<ApplicantEntity, Long> {
    ApplicantEntity findByLastNameAndNameAndSurname(String lastname, String name, String surname);

    int countAllBy();
}

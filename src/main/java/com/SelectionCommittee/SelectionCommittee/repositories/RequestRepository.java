package com.SelectionCommittee.SelectionCommittee.repositories;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RequestRepository extends PagingAndSortingRepository<RequestEntity, Long> {
    List<RequestEntity> findAllByFacultiesIdOrderByRatingScoreDesc(int facultiesId, Pageable pageable);
    RequestEntity findByFacultiesIdAndApplicantId(int facultyId, long applicantId);
    int countAllByFacultiesId(int facultiesId);
}

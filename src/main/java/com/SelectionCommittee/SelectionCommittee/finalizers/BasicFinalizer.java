package com.SelectionCommittee.SelectionCommittee.finalizers;

import com.SelectionCommittee.SelectionCommittee.models.FacultiesEntity;
import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Basic Finalizer - basic request finalizer
 */
@Service
@Log4j2
public class BasicFinalizer extends Finalizer {


    protected static final String NOT_PROCESSED = "not processed";
    protected static final String BUDGET = "budget";
    protected static final String RECOMMEND_BUDGET = "recommend budget";
    protected static final String CONTRACT = "contract";
    protected static final String RECOMMEND_CONTRACT = "recommend contract";
    protected static final String ALLOWED = "allowed";

    /**
     * Finalize requests
     */
    @Override
    public void finalizeRequests() {
        log.info("Finalize requests");
        var faculties = facultiesRepository.findAll();
        precessed(faculties);
        setBudget(faculties);
        seContract(faculties);
    }

    /**
     * Set budget and recommend budget status
     *
     * @param faculties list of Faculties from DB
     */
    private void setBudget(Iterable<FacultiesEntity> faculties) {
        log.info("Set budgets");
        for (FacultiesEntity faculty : faculties) {
            var requests = requestRepository.findAllByFacultiesIdAndStatusOrderByRatingScoreDesc(Math.toIntExact(faculty.getId()), ALLOWED);
            int count = 0;
            int seats = faculty.getBudgetSeats() - requestRepository.findAllByFacultiesIdAndStatusOrderByRatingScoreDesc(Math.toIntExact(faculty.getId()), BUDGET).size();
            for (RequestEntity request : requests) {
                if (count < seats) {
                    request.setStatus(BUDGET);
                    requestRepository.save(request);
                    setStatusToOtherRequests(request, RECOMMEND_BUDGET);
                } else {
                    break;
                }
                count++;
            }
        }
    }

    /**
     * Set contract and recommend contract status
     *
     * @param faculties list of Faculties from DB
     */
    private void seContract(Iterable<FacultiesEntity> faculties) {
        log.info("Set contracts");
        for (FacultiesEntity faculty : faculties) {
            var requests = requestRepository.findAllByFacultiesIdAndStatusOrderByRatingScoreDesc(Math.toIntExact(faculty.getId()), ALLOWED);
            int count = 0;
            int seats = faculty.getTotalSeats() - faculty.getBudgetSeats() - requestRepository.findAllByFacultiesIdAndStatusOrderByRatingScoreDesc(Math.toIntExact(faculty.getId()), CONTRACT).size();
            for (RequestEntity request : requests) {
                if (count < seats) {
                    request.setStatus(CONTRACT);
                    requestRepository.save(request);
                    setStatusToOtherRequests(request, RECOMMEND_CONTRACT);
                } else {
                    break;
                }
                count++;
            }
        }
    }

    /**
     * Set status to other applicant requests
     *
     * @param request current request from DB
     * @param status  setting status
     */
    private void setStatusToOtherRequests(RequestEntity request, String status) {
        var otherRequests = requestRepository.findAllByApplicantIdAndStatus(request.getApplicantId(), ALLOWED);
        for (RequestEntity otherRequest : otherRequests) {
            otherRequest.setStatus(status);
            requestRepository.save(otherRequest);
        }
    }

    /**
     * Set ratting score and passing score
     *
     * @param faculties list of Faculties from DB
     */
    private void precessed(Iterable<FacultiesEntity> faculties) {
        log.info("Set ratting score");
        for (FacultiesEntity faculty : faculties) {
            var requests = requestRepository.findAllByFacultiesIdOrderByRatingScoreDesc(Math.toIntExact(faculty.getId()), Pageable.unpaged());
            for (RequestEntity request : requests) {
                if (Objects.equals(request.getStatus(), NOT_PROCESSED)) {
                    int ratting = ratingScoreMath.setRattingScore(request);
                    request.setRatingScore(ratting);
                    ratingScoreMath.setRattingScore(request);
                    passingScoreMath.setPassing(request);
                    requestRepository.save(request);
                }
            }
        }
    }

}

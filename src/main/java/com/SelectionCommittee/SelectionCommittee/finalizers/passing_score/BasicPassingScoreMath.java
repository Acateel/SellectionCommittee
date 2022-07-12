package com.SelectionCommittee.SelectionCommittee.finalizers.passing_score;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;

/**
 * BasicPassingScoreMath basic set passing score for request
 */
public class BasicPassingScoreMath implements PassingScoreMathable {

    /**
     * Set passing (status allowed if ratting score more than 100)
     *
     * @param request applicant request
     */
    @Override
    public void setPassing(RequestEntity request) {
        if (request.getRatingScore() < 100) {
            request.setStatus("not allowed");
        } else {
            request.setStatus("allowed");
        }
    }
}

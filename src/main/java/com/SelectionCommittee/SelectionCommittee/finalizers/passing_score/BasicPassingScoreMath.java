package com.SelectionCommittee.SelectionCommittee.finalizers.passing_score;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;

public class BasicPassingScoreMath implements PassingScoreMathable {

    @Override
    public void setPassing(RequestEntity request) {
        if (request.getRatingScore() < 100) {
            request.setStatus("not allowed");
        } else {
            request.setStatus("allowed");
        }
    }
}

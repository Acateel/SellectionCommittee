package com.SelectionCommittee.SelectionCommittee.finalizers;


import com.SelectionCommittee.SelectionCommittee.finalizers.passing_score.BasicPassingScoreMath;
import com.SelectionCommittee.SelectionCommittee.finalizers.passing_score.PassingScoreMathable;
import com.SelectionCommittee.SelectionCommittee.finalizers.rating_score.BasicRattingScoreMath;
import com.SelectionCommittee.SelectionCommittee.finalizers.rating_score.RatingScoreMathable;
import com.SelectionCommittee.SelectionCommittee.repositories.FacultiesRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Finalizer - abstract request finalizer
 */
@Service
public abstract class Finalizer {
    @Autowired
    FacultiesRepository facultiesRepository;
    @Autowired
    RequestRepository requestRepository;
    RatingScoreMathable ratingScoreMath;
    PassingScoreMathable passingScoreMath;

    /**
     * Set default ratting and passing score
     */
    protected Finalizer() {
        ratingScoreMath = new BasicRattingScoreMath();
        passingScoreMath = new BasicPassingScoreMath();
    }

    public abstract void finalizeRequests();

    /**
     * set ratting score class
     *
     * @param ratingScoreMath implementation of RatingScoreMathable
     */
    public void setRatingScoreMath(RatingScoreMathable ratingScoreMath) {
        this.ratingScoreMath = ratingScoreMath;
    }

    /**
     * set passing score class
     *
     * @param passingScoreMath implementation of PassingScoreMathable
     */
    public void setPassingScoreMath(PassingScoreMathable passingScoreMath) {
        this.passingScoreMath = passingScoreMath;
    }
}

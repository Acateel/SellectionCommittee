package com.SelectionCommittee.SelectionCommittee.finalizers.rating_score;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;


public class BasicRattingScoreMath implements  RatingScoreMathable{

    @Override
    public int setRattingScore(RequestEntity request) {
        double main = request.getMainSubject();
        double second = request.getSecondSubject();
        double sub = request.getSubSubject();
        double average = (main+second+sub)/3.0;
        return (int) Math.round(average);
    }
}

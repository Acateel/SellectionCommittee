package com.SelectionCommittee.SelectionCommittee.validators;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import org.springframework.ui.Model;

public class RequestValidator {
    private RequestValidator() {
    }

    public static boolean checkRequest(RequestEntity request, Model model) {
        boolean checkedMain = checkMainSubject(request, model);
        boolean checkedSecond = checkSecondSubject(request, model);
        boolean checkedSub = checkSubSubject(request, model);
        boolean checkedAttestation = checkAttestationScore(request, model);
        return checkedMain && checkedSecond && checkedSub && checkedAttestation;
    }

    public static boolean checkMainSubject(RequestEntity request, Model model) {
        if (!Validator.checkScoreSubject(request.getMainSubject())) {
            model.addAttribute("main_subject_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkSecondSubject(RequestEntity request, Model model) {
        if (!Validator.checkScoreSubject(request.getSecondSubject())) {
            model.addAttribute("second_subject_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkSubSubject(RequestEntity request, Model model) {
        if (!Validator.checkScoreSubject(request.getSubSubject())) {
            model.addAttribute("sub_subject_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkAttestationScore(RequestEntity request, Model model) {
        if (!Validator.checkScoreAttestation(request.getAverageAttestationScore())) {
            model.addAttribute("attestation_score_error", true);
            return false;
        }
        return true;
    }
}

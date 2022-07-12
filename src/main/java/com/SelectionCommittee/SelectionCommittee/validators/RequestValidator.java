package com.SelectionCommittee.SelectionCommittee.validators;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import org.springframework.ui.Model;

/**
 * RequestValidator validate request entity and add error message into model if validate is false
 */
public class RequestValidator {
    private RequestValidator() {
    }

    /**
     * Validate request entity
     *
     * @param request request
     * @param model   model for add attribute for message
     * @return true if all validate complete
     */
    public static boolean checkRequest(RequestEntity request, Model model) {
        boolean checkedMain = checkMainSubject(request, model);
        boolean checkedSecond = checkSecondSubject(request, model);
        boolean checkedSub = checkSubSubject(request, model);
        boolean checkedAttestation = checkAttestationScore(request, model);
        return checkedMain && checkedSecond && checkedSub && checkedAttestation;
    }

    /**
     * Validate main subject score
     *
     * @param request request entity
     * @param model   model for add attribute for message
     * @return true and false
     */
    public static boolean checkMainSubject(RequestEntity request, Model model) {
        if (!Validator.checkScoreSubject(request.getMainSubject())) {
            model.addAttribute("main_subject_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate second subject score
     *
     * @param request request entity
     * @param model   model for add attribute for message
     * @return true and false
     */
    public static boolean checkSecondSubject(RequestEntity request, Model model) {
        if (!Validator.checkScoreSubject(request.getSecondSubject())) {
            model.addAttribute("second_subject_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate sub subject score
     *
     * @param request request entity
     * @param model   model for add attribute for message
     * @return true and false
     */
    public static boolean checkSubSubject(RequestEntity request, Model model) {
        if (!Validator.checkScoreSubject(request.getSubSubject())) {
            model.addAttribute("sub_subject_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate attestation score
     *
     * @param request request entity
     * @param model   model for add attribute for message
     * @return true and false
     */
    public static boolean checkAttestationScore(RequestEntity request, Model model) {
        if (!Validator.checkScoreAttestation(request.getAverageAttestationScore())) {
            model.addAttribute("attestation_score_error", true);
            return false;
        }
        return true;
    }
}

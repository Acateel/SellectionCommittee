package com.SelectionCommittee.SelectionCommittee.validators;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import org.springframework.ui.Model;

/**
 * ApplicantValidator validate applicant entity and add error message into model if validate is false
 */
public class ApplicantValidator {
    private ApplicantValidator() {
    }

    /**
     * Validate applicant entity
     *
     * @param applicant applicant entity
     * @param model     model for add attribute for message
     * @return true if all validate complete
     */
    public static boolean checkApplicant(ApplicantEntity applicant, Model model) {
        boolean lastnameChecked = checkLastName(applicant, model);
        boolean firstnameChecked = checkFirstName(applicant, model);
        boolean surnameChecked = checkSurName(applicant, model);
        boolean cityChecked = checkCity(applicant, model);
        boolean regionChecked = checkRegion(applicant, model);
        boolean educationChecked = checkEducation(applicant, model);

        return lastnameChecked && firstnameChecked && surnameChecked && cityChecked && regionChecked && educationChecked;
    }

    /**
     * Validate last name of applicant
     *
     * @param applicant applicant entity
     * @param model     model for add attribute for message
     * @return true or false
     */
    public static boolean checkLastName(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getLastName())) {
            model.addAttribute("lastname_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate first name of applicant
     *
     * @param applicant applicant entity
     * @param model     model for add attribute for message
     * @return true or false
     */
    public static boolean checkFirstName(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getName())) {
            model.addAttribute("firstname_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate surname of applicant
     *
     * @param applicant applicant entity
     * @param model     model for add attribute for message
     * @return true or false
     */
    public static boolean checkSurName(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getSurname())) {
            model.addAttribute("surname_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate city of applicant
     *
     * @param applicant applicant entity
     * @param model     model for add attribute for message
     * @return true or false
     */
    public static boolean checkCity(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getCity())) {
            model.addAttribute("city_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate region of applicant
     *
     * @param applicant applicant entity
     * @param model     model for add attribute for message
     * @return true or false
     */
    public static boolean checkRegion(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getRegion())) {
            model.addAttribute("region_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate education institution name of applicant
     *
     * @param applicant applicant entity
     * @param model     model for add attribute for message
     * @return true or false
     */
    public static boolean checkEducation(ApplicantEntity applicant, Model model) {
        if (!Validator.checkInstitutionName(applicant.getNameEducationalInstitution())) {
            model.addAttribute("education_error", true);
            return false;
        }
        return true;
    }
}

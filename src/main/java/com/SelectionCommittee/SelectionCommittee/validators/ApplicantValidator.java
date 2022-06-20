package com.SelectionCommittee.SelectionCommittee.validators;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import org.springframework.ui.Model;

public class ApplicantValidator {
    private ApplicantValidator() {
    }

    public static boolean checkApplicant(ApplicantEntity applicant, Model model) {
        boolean lastnameChecked = checkLastName(applicant, model);
        boolean firstnameChecked = checkFirstName(applicant, model);
        boolean surnameChecked = checkSurName(applicant, model);
        boolean cityChecked = checkCity(applicant, model);
        boolean regionChecked = checkRegion(applicant, model);
        boolean educationChecked = checkEducation(applicant, model);

        return lastnameChecked && firstnameChecked && surnameChecked && cityChecked && regionChecked && educationChecked;
    }

    public static boolean checkLastName(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getLastName())) {
            model.addAttribute("lastname_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkFirstName(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getName())) {
            model.addAttribute("firstname_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkSurName(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getSurname())) {
            model.addAttribute("surname_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkCity(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getCity())) {
            model.addAttribute("city_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkRegion(ApplicantEntity applicant, Model model) {
        if (!Validator.checkName(applicant.getRegion())) {
            model.addAttribute("region_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkEducation(ApplicantEntity applicant, Model model) {
        if (!Validator.checkInstitutionName(applicant.getNameEducationalInstitution())) {
            model.addAttribute("education_error", true);
            return false;
        }
        return true;
    }
}

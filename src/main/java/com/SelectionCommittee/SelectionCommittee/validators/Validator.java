package com.SelectionCommittee.SelectionCommittee.validators;

/**
 * Validator
 */
public class Validator {
    private Validator() {
    }

    private static final String REGEX_EMAIL = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    private static final String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    private static final String REGEX_NAME = "^[^\\(\\)\\{\\}\\[\\]\\=\\+\\*\\/0-9]+$";
    private static final String REGEX_NAME_INSTITUTION = "^[^\\(\\)\\{\\}\\[\\]\\=\\+\\*\\/]+$";

    /**
     * Validate email
     *
     * @param email email
     * @return true or false
     */
    public static boolean checkEmail(String email) {
        return email.matches(REGEX_EMAIL);
    }

    /**
     * Validate password
     *
     * @param password password
     * @return true or false
     */
    public static boolean checkPassword(String password) {
        return password.matches(REGEX_PASSWORD);
    }

    /**
     * Validate name
     *
     * @param name name
     * @return true or false
     */
    public static boolean checkName(String name) {
        return name.matches(REGEX_NAME);
    }

    /**
     * Validate Institution name
     *
     * @param name name of education institution
     * @return true or false
     */
    public static boolean checkInstitutionName(String name) {
        return name.matches(REGEX_NAME_INSTITUTION);
    }

    /**
     * Validate Subject score
     *
     * @param score score
     * @return true or false
     */
    public static boolean checkScoreSubject(int score) {
        return 100 <= score && score <= 200;
    }

    /**
     * Validate attestation score
     *
     * @param score attestation score
     * @return true or false
     */
    public static boolean checkScoreAttestation(double score) {
        return score >= 2.0f && score <= 12.0f;
    }
}

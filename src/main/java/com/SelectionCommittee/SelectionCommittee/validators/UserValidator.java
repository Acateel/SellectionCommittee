package com.SelectionCommittee.SelectionCommittee.validators;

import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import org.springframework.ui.Model;

import java.util.Objects;

/**
 * UserValidator validate user entity and add error message into model if validate is false
 */
public class UserValidator {
    private UserValidator() {
    }

    /**
     * validate user entity
     *
     * @param user           user entity
     * @param passwordRepeat password repeat
     * @param model          model for add attribute for message
     * @return true if all validate complete
     */
    public static boolean checkUser(UserEntity user, String passwordRepeat, Model model) {
        boolean emailChecked = checkEmail(user, model);
        boolean passwordChecked = checkPassword(user, model);
        boolean passwordRepeatChecked = checkPasswordRepeat(user, passwordRepeat, model);
        return emailChecked && passwordChecked && passwordRepeatChecked;
    }

    /**
     * Validate user email
     *
     * @param user  user entity
     * @param model for add attribute with error message if validate is false
     * @return true or false
     */
    public static boolean checkEmail(UserEntity user, Model model) {
        if (!Validator.checkEmail(user.getLogin())) {
            model.addAttribute("email_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate user password
     *
     * @param user  user entity
     * @param model for add attribute with error message if validate is false
     * @return true or false
     */
    public static boolean checkPassword(UserEntity user, Model model) {
        if (!Validator.checkPassword(user.getPassword())) {
            model.addAttribute("password_error", true);
            return false;
        }
        return true;
    }

    /**
     * Validate user repeat password
     *
     * @param user           user entity
     * @param passwordRepeat password repeat
     * @param model          for add attribute with error message if validate is false
     * @return true or false
     */
    public static boolean checkPasswordRepeat(UserEntity user, String passwordRepeat, Model model) {
        if (!Objects.equals(user.getPassword(), passwordRepeat)) {
            model.addAttribute("password_repeat_error", true);
            return false;
        }
        return true;
    }
}

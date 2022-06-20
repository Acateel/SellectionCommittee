package com.SelectionCommittee.SelectionCommittee.validators;

import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import org.springframework.ui.Model;

import java.util.Objects;

public class UserValidator {
    private UserValidator() {
    }

    public static boolean checkUser(UserEntity user, String passwordRepeat, Model model) {
        boolean emailChecked = checkEmail(user, model);
        boolean passwordChecked = checkPassword(user, model);
        boolean passwordRepeatChecked = checkPasswordRepeat(user, passwordRepeat, model);
        return emailChecked && passwordChecked && passwordRepeatChecked;
    }

    public static boolean checkEmail(UserEntity user, Model model) {
        if (!Validator.checkEmail(user.getLogin())) {
            model.addAttribute("email_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkPassword(UserEntity user, Model model) {
        if (!Validator.checkPassword(user.getPassword())) {
            model.addAttribute("password_error", true);
            return false;
        }
        return true;
    }

    public static boolean checkPasswordRepeat(UserEntity user, String passwordRepeat, Model model) {
        if (!Objects.equals(user.getPassword(), passwordRepeat)) {
            model.addAttribute("password_repeat_error", true);
            return false;
        }
        return true;
    }
}

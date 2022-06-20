package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import com.SelectionCommittee.SelectionCommittee.auth.MyUserDetailsService;
import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import com.SelectionCommittee.SelectionCommittee.validators.Validator;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("applicant", new ApplicantEntity());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registration(@RequestParam(name = "email") String email,
                               @RequestParam(name = "psw") String password,
                               @RequestParam(name = "psw-repeat") String passwordRepeat,
                               @RequestParam(name = "lastname") String lastname,
                               @RequestParam(name = "firstname") String firstname,
                               @RequestParam(name = "surname") String surname,
                               @RequestParam(name = "city") String city,
                               @RequestParam(name = "region") String region,
                               @RequestParam(name = "education") String education,
                               Model model) {

        UserEntity user = getUserEntity(email, password);
        model.addAttribute("user", user);
        model.addAttribute("psw_repeat", passwordRepeat);



        ApplicantEntity applicant = getApplicantEntity(email, lastname, firstname, surname, city, region, education);
        model.addAttribute("applicant", applicant);

        boolean userChecked = checkUser(user, passwordRepeat, model);
        if(userChecked){
            return "auth/login";
        }
        return "auth/register";
    }

    private ApplicantEntity getApplicantEntity(String email, String lastname, String firstname, String surname, String city, String region, String education) {
        ApplicantEntity applicant = new ApplicantEntity();
        applicant.setEmail(email);
        applicant.setLastName(lastname);
        applicant.setName(firstname);
        applicant.setSurname(surname);
        applicant.setCity(city);
        applicant.setRegion(region);
        applicant.setNameEducationalInstitution(education);
        return applicant;
    }

    private UserEntity getUserEntity(String email, String password) {
        UserEntity user = new UserEntity();
        user.setLogin(email);
        user.setPassword(password);
        user.setRole("applicant");
        return user;
    }

    private boolean checkUser(UserEntity user, String passwordRepeat, Model model){
        boolean emailChecked = checkEmail(user, model);
        boolean passwordChecked = checkPassword(user, model);
        boolean passwordRepeatChecked = checkPasswordRepeat(user, passwordRepeat, model);
        return emailChecked && passwordChecked && passwordRepeatChecked;
    }

    private boolean checkEmail(UserEntity user, Model model) {
        if(!Validator.checkEmail(user.getLogin())){
            model.addAttribute("email_error", true);
            return false;
        }
        return true;
    }

    private boolean checkPassword(UserEntity user, Model model){
        if(!Validator.checkPassword(user.getPassword())){
            model.addAttribute("password_error", true);
            return false;
        }
        return true;
    }

    private boolean checkPasswordRepeat(UserEntity user, String passwordRepeat, Model model){
        if(!Objects.equals(user.getPassword(), passwordRepeat)){
            model.addAttribute("password_repeat_error", true);
            return false;
        }
        return true;
    }

}

package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import com.SelectionCommittee.SelectionCommittee.validators.ApplicantValidator;
import com.SelectionCommittee.SelectionCommittee.validators.UserValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicantRepository applicantRepository;

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        log.info("Show registration form");
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
        log.info("Registration");
        UserEntity user = getUserEntity(email, password);
        model.addAttribute("user", user);
        model.addAttribute("psw_repeat", passwordRepeat);

        ApplicantEntity applicant = getApplicantEntity(email, lastname, firstname, surname, city, region, education);
        model.addAttribute("applicant", applicant);

        boolean userChecked = UserValidator.checkUser(user, passwordRepeat, model);
        boolean applicantChecked = ApplicantValidator.checkApplicant(applicant, model);
        if(userChecked && applicantChecked){
            // add user and applicant to DB
            if(!addToDB(user, applicant)){
                model.addAttribute("user_exist_error", true);
                return "auth/register";
            }
            else{
                model.addAttribute("registration_complete", true);
                model.addAttribute("login", user.getLogin());
                model.addAttribute("password", user.getPassword());
                return "auth/login";
            }
        }
        log.warn("Validator did not pass");
        return "auth/register";
    }

    private ApplicantEntity getApplicantEntity(String email, String lastname, String firstname, String surname, String city, String region, String education) {
        ApplicantEntity applicant = new ApplicantEntity();
        applicant.setId(0L);
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
        user.setId(0L);
        user.setLogin(email);
        user.setPassword(password);
        user.setRole("applicant");
        return user;
    }

    private boolean addToDB(UserEntity user, ApplicantEntity applicant){
        var userInDB = userRepository.findByLogin(user.getLogin());
        if(userInDB.isPresent()){
            log.warn("User exist, userId={}", userInDB.get().getId());
            return false;
        }
        applicantRepository.save(applicant);
        ApplicantEntity applicantInDB = applicantRepository.findByLastNameAndNameAndSurname(applicant.getLastName(), applicant.getName(), applicant.getSurname());
        user.setApplicantId(applicantInDB.getId());
        userRepository.save(user);
        log.info("Save user in DB");
        return true;
    }
}

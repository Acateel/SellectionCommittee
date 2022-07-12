package com.SelectionCommittee.SelectionCommittee.controllers.auth_controlers;

import com.SelectionCommittee.SelectionCommittee.models.ApplicantEntity;
import com.SelectionCommittee.SelectionCommittee.models.UserEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.ApplicantRepository;
import com.SelectionCommittee.SelectionCommittee.repositories.UserRepository;
import com.SelectionCommittee.SelectionCommittee.validators.ApplicantValidator;
import com.SelectionCommittee.SelectionCommittee.validators.UserValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Registration controller - displaying registration form, get login information and after validation added to DB
 */
@Controller
@Log4j2
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String REGISTER_PAGE = "auth/register";
    private static final String APPLICANT_ATTRIBUTE_KEY = "applicant";
    private static final String USER_ATTRIBUTE_KEY = "user";

    /**
     * Show register form page
     *
     * @param model for add attributes empty entity
     * @return model name of register page
     */
    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        log.info("Show registration form");
        model.addAttribute(USER_ATTRIBUTE_KEY, new UserEntity());
        model.addAttribute(APPLICANT_ATTRIBUTE_KEY, new ApplicantEntity());
        return REGISTER_PAGE;
    }

    /**
     * Create applicant and user entity and add into DB
     *
     * @param email          user login and applicant email
     * @param password       from parameter
     * @param passwordRepeat from parameter
     * @param lastname       applicant lastname
     * @param firstname      applicant firstname
     * @param surname        applicant surname
     * @param city           applicant city
     * @param region         applicant region
     * @param education      applicant name educational institution
     * @param model          for add attributes
     * @return login page if registration access or back to registration page is did not
     */
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
        model.addAttribute(USER_ATTRIBUTE_KEY, user);
        model.addAttribute("psw_repeat", passwordRepeat);

        ApplicantEntity applicant = getApplicantEntity(email, lastname, firstname, surname, city, region, education);
        model.addAttribute(APPLICANT_ATTRIBUTE_KEY, applicant);

        boolean userChecked = UserValidator.checkUser(user, passwordRepeat, model);
        boolean applicantChecked = ApplicantValidator.checkApplicant(applicant, model);
        if (userChecked && applicantChecked) {
            // add user and applicant to DB
            if (!addToDB(user, applicant)) {
                model.addAttribute("user_exist_error", true);
                return REGISTER_PAGE;
            } else {
                model.addAttribute("registration_complete", true);
                model.addAttribute("login", user.getLogin());
                return "auth/login";
            }
        }
        log.warn("Validator did not pass");
        return REGISTER_PAGE;
    }

    /**
     * create applicant entity by parameters
     *
     * @param email     applicant email
     * @param lastname  applicant lastname
     * @param firstname applicant firstname
     * @param surname   applicant surname
     * @param city      applicant city
     * @param region    applicant region
     * @param education applicant name educational institution
     * @return applicant entity
     */
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

    /**
     * Create user entity
     *
     * @param email    user login
     * @param password user password without encrypt
     * @return user entity
     */
    private UserEntity getUserEntity(String email, String password) {
        UserEntity user = new UserEntity();
        user.setId(0L);
        user.setLogin(email);
        user.setPassword(password);
        user.setRole(APPLICANT_ATTRIBUTE_KEY);
        return user;
    }

    /**
     * Add user and applicant entities into DB
     *
     * @param user      user entity
     * @param applicant user entity
     * @return true if entities added into DB or false if did not
     */
    private boolean addToDB(UserEntity user, ApplicantEntity applicant) {
        var userInDB = userRepository.findByLogin(user.getLogin());
        if (userInDB.isPresent()) {
            log.warn("User exist, userId={}", userInDB.get().getId());
            return false;
        }
        applicantRepository.save(applicant);
        ApplicantEntity applicantInDB = applicantRepository.findByLastNameAndNameAndSurname(applicant.getLastName(), applicant.getName(), applicant.getSurname());
        user.setApplicantId(applicantInDB.getId());

        // password encryption
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        log.info("Save user in DB");
        return true;
    }
}

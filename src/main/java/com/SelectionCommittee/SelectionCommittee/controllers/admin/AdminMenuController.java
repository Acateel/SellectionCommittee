package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Admin menu controller is responsible for displaying the admin menu page
 */
@Controller
@Log4j2
public class AdminMenuController {
    /**
     * Show admin menu
     * @return model name of admin menu
     */
    @GetMapping("/admin_menu")
    public String getAdminMenu(){
        log.info("Show admin menu");
        return "admin/menu";
    }
}

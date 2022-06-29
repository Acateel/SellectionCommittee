package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Admin menu controller - show admin menu with admin function
 */
@Controller
@Log4j2
public class AdminMenuController {
    @GetMapping("/admin_menu")
    public String getAdminMenu(Model model){
        log.info("Show admin menu");
        return "admin/menu";
    }
}

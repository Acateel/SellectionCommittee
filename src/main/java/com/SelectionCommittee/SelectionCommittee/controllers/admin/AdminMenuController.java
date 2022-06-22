package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMenuController {
    @GetMapping("/admin_menu")
    public String getAdminMenu(Model model){
        return "admin/menu";
    }
}

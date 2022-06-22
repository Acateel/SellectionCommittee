package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class RequestsModerationController {
    @Autowired
    protected RequestRepository requestRepository;
    @GetMapping("/add_to_realize")
    public String addToRealize(@RequestParam int facultyId, @RequestParam int requestId, Model model){
        RequestEntity request = requestRepository.findById(Long.valueOf(requestId)).get();
        setStatus(request);
        requestRepository.save(request);
        return "redirect:/request?facultyId="+facultyId;
    }

    private void setStatus(RequestEntity request) {
        String status = request.getStatus();
        if(Objects.equals(status, "not processed")){
            request.setStatus("budget");
        }
        else{
            request.setStatus("not processed");
        }
    }
}

package com.SelectionCommittee.SelectionCommittee.controllers.admin;

import com.SelectionCommittee.SelectionCommittee.models.RequestEntity;
import com.SelectionCommittee.SelectionCommittee.repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Request moderation controller - added request into finalize (set request status)
 */
@Controller
@Log4j2
public class RequestsModerationController {
    @Autowired
    protected RequestRepository requestRepository;

    @GetMapping("/add_to_realize")
    public String addToRealize(@RequestParam int facultyId, @RequestParam int requestId) {
        log.info("Add to realize request, faculty id={} , applicant id={}", facultyId, requestId);
        var optional = requestRepository.findById((long) requestId);
        if (optional.isEmpty()) {
            log.error("Request not found, Id={}", requestId);
            throw new NoSuchElementException("Request not found, Id=" + requestId);
        }
        RequestEntity request = optional.get();
        setStatus(request);
        requestRepository.save(request);
        return "redirect:/request?facultyId=" + facultyId;
    }

    private void setStatus(RequestEntity request) {
        String status = request.getStatus();
        if (Objects.equals(status, "not processed")) {
            request.setStatus("budget");
        } else {
            request.setStatus("not processed");
        }
    }
}

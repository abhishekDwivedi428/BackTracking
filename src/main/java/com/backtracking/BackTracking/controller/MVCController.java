package com.backtracking.BackTracking.controller;

import com.backtracking.BackTracking.entity.Application;
import com.backtracking.BackTracking.exception.ResourceNotFoundException;
import com.backtracking.BackTracking.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MVCController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/applications")
    private String getApplications(Model model) throws ResourceNotFoundException {
             System.out.println(" MVC Controller called ");
             model.addAttribute("applications", applicationService.findAllApplication());
             return "applications";
    }

    @GetMapping("/applications/{id}")
    private RedirectView deleteApplication(@PathVariable("id") long id)
    {
            System.out.println("Delete method called with id "+id);
            applicationService.deleteApplication(id);
            RedirectView redirectView = new RedirectView();
            redirectView.setContextRelative(true);
            redirectView.setUrl("/applications");
            return redirectView;
    }

    @GetMapping("/applicationnew")
    public String applicationNew(Model model) {
        Application application = new Application();
        model.addAttribute("application", application);
        return "applicationnew";
    }

    @PostMapping("/applicationsave")
    public RedirectView submitForm(@ModelAttribute("application") Application application) {

        System.out.println(" Save Application called "+application);
        applicationService.saveApplication(application);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/applications");
        return redirectView;
    }

    @GetMapping("/applicationedit/{id}")
    public String editApplication(@PathVariable("id") long id, Model model) {

        System.out.println(" find application by id called ");
        Application application = applicationService.findApplicationById(id);
        model.addAttribute("application", application);
        return "applicationedit";
    }
}

package com.backtracking.BackTracking.controller;

import com.backtracking.BackTracking.entity.Application;
import com.backtracking.BackTracking.exception.ResourceNotFoundException;
import com.backtracking.BackTracking.service.ApplicationService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@RestController
@EnableOAuth2Sso
//@RequestMapping("/api/v2/applications")
public class ApplicationController {

//    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/")
    public String message(Principal principal) {
        return "Hi"+principal.getName();
    }

    @GetMapping
    private ResponseEntity<List<Application>> getApplications() throws ResourceNotFoundException {
        List<Application> applicationList = applicationService.findAllApplication();
        if (applicationList.size() != 0) {
            return new ResponseEntity<List<Application>>(applicationList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    private ResponseEntity<String> saveApplication(@RequestBody Application application)
    {
        try{
            applicationService.saveApplication(application);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Application has been saved", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Application> getApplication(@PathVariable("id") long id)
    {
        try{
            Application application = applicationService.findApplicationById(id);
            return new ResponseEntity<Application>(application, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byName/{name}")
    private ResponseEntity<List<Application>> getApplication(@PathVariable("name") String name)
    {
        try{
            List<Application> application = applicationService.findApplicationByName(name);
            return new ResponseEntity<List<Application>>(application, HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Application> deleteApplication(@PathVariable("id") long id)
    {
        try{
             applicationService.deleteApplication(id);
            return new ResponseEntity<Application>(HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Application> updateApplication(@RequestBody Application application)
    {
        try{
            applicationService.updateApplication(application);
            return new ResponseEntity<Application>(HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

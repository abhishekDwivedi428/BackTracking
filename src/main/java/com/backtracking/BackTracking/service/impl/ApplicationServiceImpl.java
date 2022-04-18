package com.backtracking.BackTracking.service.impl;

import com.backtracking.BackTracking.entity.Application;
import com.backtracking.BackTracking.exception.ResourceNotFoundException;
import com.backtracking.BackTracking.repository.ApplicationRepository;
import com.backtracking.BackTracking.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> findAllApplication() throws ResourceNotFoundException {

        List<Application> application = applicationRepository.findAll();
        if (application.size() == 0)
        {
            throw new ResourceNotFoundException(" No Record Found");
        }

        return application;
    }

    @Override
    public Application findApplicationById(long id) {
        Optional<Application> application  = applicationRepository.findById(id);
        if (application.isPresent())
        {
            return application.get();
        }
        return null;
    }

    @Override
    public void saveApplication(Application application) {
       applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public void updateApplication(Application application) {
        Optional<Application> app =  applicationRepository.findById(application.getId());

        if(app.isPresent())
        {
            Application application1 = app.get();
            application1.setName(application.getName());
            application1.setOwner(application.getOwner());
            application1.setDescription(application.getDescription());
            applicationRepository.save(application1);
        }
    }

    @Override
    public  List<Application> findApplicationByName(String appName) {
        List<Application> applications = applicationRepository.findAllAppByName(appName);

        if (applications.size() !=0 )
        {
            return applications;
        }
        return null;
    }

    @Override
    public List<Application> findApplications(Sort sort) {
        return null;
    }


}

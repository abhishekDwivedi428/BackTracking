package com.backtracking.BackTracking.service;

import com.backtracking.BackTracking.entity.Application;
import com.backtracking.BackTracking.exception.ResourceNotFoundException;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {

    List<Application> findAllApplication() throws ResourceNotFoundException;

    Application findApplicationById(long id);

    void saveApplication(Application application);

    void deleteApplication(long id);

    void updateApplication(Application application);

    List<Application> findApplicationByName(String appName);

    List<Application> findApplications(Sort sort);
}

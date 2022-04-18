package com.backtracking.BackTracking.service;

import com.backtracking.BackTracking.entity.Application;
import com.backtracking.BackTracking.repository.ApplicationRepository;
import com.backtracking.BackTracking.service.impl.ApplicationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ApplicationServiceTest {

    @InjectMocks
    private ApplicationService applicationService = new ApplicationServiceImpl();

    @Mock
    private ApplicationRepository repository;

    private List<Application> applicationList = new ArrayList<>();

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        Application application = new Application();
        application.setName(" Name");
        application.setDescription(" Description");
        applicationList.add(application);

        application.setName(" Name_2");
        application.setDescription(" Description_1");
        applicationList.add(application);
    }

    @Test
    public void testFindApplication()
    {
        when(repository.findAll()).thenReturn(applicationList);

        Assert.assertEquals(2, applicationService.findAllApplication().size());
    }

    @Test
    public void testFindApplicationById()
    {
        when(repository.findById(1L)).thenReturn(java.util.Optional.ofNullable(applicationList.get(0)));

        Assert.assertEquals(applicationList.get(0), applicationService.findApplicationById(1L));
    }

    @Test
    public void testDeleteApplication()
    {

    }

    @Test
    public void testFindApplicationByName()
    {
        Application application = new Application();
        List<Application> applicationList = new ArrayList<>();

        application.setName(" Name");
        application.setDescription(" Description");
        applicationList.add(application);

        application.setName(" Name");
        application.setDescription(" Description_1");
        applicationList.add(application);

        when(repository.findAllAppByName("Name")).thenReturn(applicationList);

        Assert.assertEquals(2, applicationService.findApplicationByName("Name").size());

    }
}

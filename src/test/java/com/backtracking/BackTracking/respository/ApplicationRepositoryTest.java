package com.backtracking.BackTracking.respository;


import com.backtracking.BackTracking.entity.Application;
import com.backtracking.BackTracking.repository.ApplicationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void testSaveApplication()
    {
       Application application = applicationRepository.save(new Application("nnn", "nnn", "hhhh"));
       Application application1 = applicationRepository.findById(application.getId()).get();
        Assert.assertEquals("nnn", application1.getName());

    }
}

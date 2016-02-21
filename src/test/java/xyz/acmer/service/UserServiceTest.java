package xyz.acmer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by hypo on 16-2-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void addNew(){

        userService.addNewUser("test3", "test4", "test5@dss.com", "123");
    }
}

package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.dao.impl.UserDaoImpl;
import xyz.acmer.entity.user.User;

/**
 * Created by hypo on 16-2-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring-hibernate.xml"})
public class UserDaoTest {

    private User user;

    @Autowired
    private IUserDao dao;

    @Before
    public void getUser(){
        user = new User("123", "123", "i@123.net", "123");
    }

    @After
    public void print(){
        System.out.println(user.getUserId());
    }

    @Test
    public void save(){
        dao.save(user);
    }

}

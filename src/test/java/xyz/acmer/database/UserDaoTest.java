package xyz.acmer.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.entity.user.User;

import java.util.List;

/**
 * Created by hypo on 16-2-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class UserDaoTest {

    @Autowired
    private IUserDao dao;

    @Test
    public void save(){
        User user = new User("hypo", "hypo", "i@ihypo.net", "123");
        dao.save(user);
        System.out.println(user.getUserId());
    }

    @Test
    public void getUserById(){

        User user = dao.get(3L);

        System.out.println(user.getNickName());
    }

    @Test
    public void getUserByUsername(){

        User user = dao.getUserByUsername("hypo");

        System.out.println(user.getNickName());
    }

    @Test
    public void getUserByUserEmail(){

        User user = dao.getUserByEmail("i@ihypo.net");

        System.out.println(user.getNickName());
    }

    @Test
    public void getAllUsers(){

        List<User> list = dao.getAllUser();

        for(User user: list){
            System.out.println(user.getUserId() + "\t" + user.getUserName());
        }
    }
}

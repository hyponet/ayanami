package xyz.acmer.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.user.User;
import xyz.acmer.repository.user.UserRepository;

import java.util.List;

/**
 * Created by hypo on 16-2-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class UserDaoTest {

    @Autowired
    private UserRepository dao;

    @Test
    public void save(){
        User user = new User("hypo", "hypo", "i@ihypo.net", "123");
        dao.save(user);
        System.out.println(user.getUserId());
    }

    @Test
    @Transactional
    public void getUserById(){

        User user = dao.getOne(4l);

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

        List<User> list = dao.findAll();

        for(User user: list){
            System.out.println(user.getUserId() + "\t" + user.getUserName());
        }
    }
}

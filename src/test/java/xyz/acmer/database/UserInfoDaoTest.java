package xyz.acmer.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.entity.user.User;
import xyz.acmer.entity.user.UserInfo;
import xyz.acmer.repository.user.UserInfoRepository;
import xyz.acmer.repository.user.UserRepository;

import java.util.Date;

/**
 * Created by hypo on 16-2-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class UserInfoDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    private User user;
    private UserInfo userInfo;

    @Before
    public void getUser(){
        this.user = userRepository.getUserByUsername("hypo");
        System.out.println(user.getUserId());
    }

    @After
    public void getUserInfoId(){
        System.out.println(userInfo.getInfoId());
    }

    @Test
    public void save(){
        userInfo = new UserInfo(user);
        userInfoRepository.save(userInfo);
    }

    @Test
    public void update(){
        this.userInfo = userInfoRepository.getUserInfoByUser(user);

        userInfo.setBirthday(new Date());

        userInfoRepository.save(userInfo);
        userInfoRepository.flush();
    }
}

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
import xyz.acmer.entity.user.UserAccount;
import xyz.acmer.repository.user.UserAccountRepository;
import xyz.acmer.repository.user.UserRepository;

/**
 * Created by hypo on 16-2-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class UserAccountTest {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private UserAccount userAccount;

    @Before
    public void getUser(){
        this.user = userRepository.getUserByUsername("hypo");
    }

    @After
    public void printInfo(){
        System.out.println(userAccount.getAccountId() + " " +
                userAccount.getUser().getUserName() + userAccount.getLoginName());
    }

    @Test
    public void addAccount(){

        this.userAccount = new UserAccount(user, "poj", "sdutacm1", "sdutacm", 10, 20);
        userAccountRepository.save(userAccount);
    }

    @Test
    public void updateAccount(){
        this.userAccount = userAccountRepository.getAcciuntByUserAndOj(user, "poj");

        userAccount.setSubmit(50);
        userAccountRepository.save(userAccount);
    }
}

package xyz.acmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.dao.impl.UserDaoImpl;
import xyz.acmer.entity.user.User;
import xyz.acmer.util.EncryptHelper;

/**
 * Created by hypo on 16-2-19.
 */
@Service
public class UserServiceImpl {

    @Autowired
    private IUserDao dao;

    public User addNewUser(String username, String nickname, String email, String password){

        User newUser = new User(username, nickname, email, EncryptHelper.getPassword(password));
        dao.save(newUser);

        return newUser;
    }
}

package xyz.acmer.service.impl;

import org.springframework.stereotype.Service;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.dao.impl.UserDaoImpl;
import xyz.acmer.entity.user.User;
import xyz.acmer.util.EncryptHelper;

/**
 * Created by hypo on 16-2-19.
 */
@Service
public class UserServiceImpl {

    public User addNewUser(String username, String nickname, String email, String password){

        IUserDao dao = new UserDaoImpl();
        User newUser = new User(username, nickname, email, EncryptHelper.getPassword(password));
        dao.save(newUser);

        return newUser;
    }
}

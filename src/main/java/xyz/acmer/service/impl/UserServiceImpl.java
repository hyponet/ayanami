package xyz.acmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.entity.user.User;
import xyz.acmer.service.IUserService;
import xyz.acmer.util.EncryptHelper;

/**
 * Created by hypo on 16-2-19.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao dao;

    public Boolean login(String email, String password){

        if(password == null || email == null || password.length() == 0){
            return false;
        }

        User user = dao.getUserByEmail(email);

        if (user == null || user.getPassword() == null){
            return false;
        }

        password = EncryptHelper.getPassword(password);

        return password.equals(user.getPassword());
    }

    public User addNewUser(String username, String nickname, String email, String password){

        if(dao.getUserByEmail(email) == null && dao.getUserByUsername(username) == null){

            User newUser = new User(username, nickname, email, EncryptHelper.getPassword(password));
            dao.save(newUser);

            return newUser;
        }

        return null;
    }
}

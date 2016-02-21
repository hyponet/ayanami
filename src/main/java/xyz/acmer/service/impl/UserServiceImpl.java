package xyz.acmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.user.User;
import xyz.acmer.entity.user.UserInfo;
import xyz.acmer.repository.user.UserInfoRepository;
import xyz.acmer.repository.user.UserRepository;
import xyz.acmer.service.IUserService;
import xyz.acmer.util.EncryptHelper;

/**
 * Created by hypo on 16-2-19.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public Boolean login(String email, String password){

        if(password == null || email == null || password.length() == 0){
            return false;
        }

        User user = userRepository.getUserByEmail(email);

        if (user == null || user.getPassword() == null){
            return false;
        }

        password = EncryptHelper.getPassword(password);

        return password.equals(user.getPassword());
    }

    public User addNewUser(String username, String nickname, String email, String password){

        if(userRepository.getUserByEmail(email) == null && userRepository.getUserByUsername(username) == null){

            User newUser = new User(username, nickname, email, EncryptHelper.getPassword(password));
            userRepository.save(newUser);

            UserInfo userInfo = new UserInfo(newUser);

            userInfoRepository.save(userInfo);

            return newUser;
        }

        return null;
    }
}

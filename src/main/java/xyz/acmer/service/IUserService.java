package xyz.acmer.service;

import xyz.acmer.entity.user.User;

/**
 * Created by hypo on 16-2-19.
 */
public interface IUserService {

    User addNewUser(String username, String nickname, String email, String password);
}

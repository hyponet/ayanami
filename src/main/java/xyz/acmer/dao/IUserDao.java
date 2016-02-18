package xyz.acmer.dao;

import xyz.acmer.entity.user.User;

/**
 * Created by hypo on 16-2-15.
 */
public interface IUserDao {

    User get(Integer id);
    Boolean update(User user);
    Boolean save(User user);
    Boolean delete(User user);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}

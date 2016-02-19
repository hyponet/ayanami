package xyz.acmer.dao;

import xyz.acmer.entity.user.User;

import java.util.List;
import java.util.Map;

/**
 * UserDAO接口，约定User的数据持久化方法
 * Created by hypo on 16-2-15.
 */
public interface IUserDao {

    User get(Long id);
    Boolean update(User user);
    Boolean save(User user);
    Boolean delete(User user);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUser();
}

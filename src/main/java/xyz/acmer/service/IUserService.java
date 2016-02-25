package xyz.acmer.service;

import xyz.acmer.entity.system.OjCode;
import xyz.acmer.entity.user.User;

import java.util.Map;

/**
 * 用户Service层 完成用户基础操作
 * Created by hypo on 16-2-19.
 */
public interface IUserService {

    Boolean login(String email, String password);

    User addNewUser(String username, String nickname, String email, String password);

    Boolean updateUserInfo(Map<String, String> map, User user);

    Boolean addUserAccount(String loginname, String password, OjCode ojCode, User user);

    Boolean updateUserAccount(String loginname, String password, OjCode ojCode, User user);
}

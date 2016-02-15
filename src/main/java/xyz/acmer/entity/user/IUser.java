package xyz.acmer.entity.user;

/**
 * Created by hypo on 16-2-2.
 */
public interface IUser {
    Integer getUserId();

    String getUserName();

    String getNickName();

    void setNickName(String nickName);

    String getEmail();

    String getPassword();

    void setPassword(String password);

    Integer getBalance();

    void setBalance(Integer balance);
}


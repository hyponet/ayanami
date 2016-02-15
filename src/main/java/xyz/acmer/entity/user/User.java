package xyz.acmer.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 用户基本信息表
 * Created by hypo on 16-2-2.
 */
@Entity(name = "user")
public class User{
    private Integer userId;
    private String userName;
    private String nickName;
    private String email;
    private String password;
    private Integer balance;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String userName, String nickName, String email, String password) {
        this.userName = userName;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "user_name", nullable = false, unique = true, length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_nickname", length = 30)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "balance", nullable = false)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}

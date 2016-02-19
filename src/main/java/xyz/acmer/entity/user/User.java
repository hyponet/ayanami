package xyz.acmer.entity.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    /**
     * 账户铜币数
     */
    private Integer balance;

    public User() {
    }

    public User(String userName, String nickName, String email, String password) {
        this.userName = userName;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    private Set<UserAccount> userAccounts = new HashSet<UserAccount>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId.equals(user.userId);

    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}

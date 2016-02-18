package xyz.acmer.entity.user;

import javax.persistence.*;

/**
 * 用户OJ账号信息表
 * Created by hypo on 16-2-11.
 */
@Entity(name = "user_account")
public class UserAccount {

    private Integer accountId;
    private User user;

    /**
     * OJ代号
     */
    private String ojCode;

    /**
     * OJ登陆信息
     * VJ使用用户自己的账户提交程序
     */
    private String loginName;
    private String password;

    /**
     * 该OJ下的AC数目
     */
    private Integer accepted;

    /**
     * 该OJ下的提交数目
     */
    private Integer submit;

    public UserAccount() {
    }

    public UserAccount(User user, String ojCode, String loginName,
                       String password,Integer accepted, Integer submit) {
        this.user = user;
        this.ojCode = ojCode;
        this.loginName = loginName;
        this.password = password;
        this.accepted = accepted;
        this.submit = submit;
    }

    @Id
    @GeneratedValue
    public Integer getAccountId() {
        return accountId;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "oj_code", nullable = false, length = 10)
    public String getOjCode() {
        return ojCode;
    }

    public void setOjCode(String ojCode) {
        this.ojCode = ojCode;
    }

    @Column(name = "login_name", nullable = false, unique = true, length = 20)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "accepted", nullable = false)
    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    @Column(name = "submit", nullable = false)
    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (!user.equals(that.user)) return false;
        return ojCode.equals(that.ojCode);

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + ojCode.hashCode();
        return result;
    }
}

package xyz.acmer.entity.user;

/**
 * Created by hypo on 16-2-11.
 */
public class UserAccount {
    private Integer accountId;
    private Integer userId;
    private String ojCode;
    private String loginName;
    private String password;
    private Integer accepted;
    private Integer submit;

    public UserAccount(Integer userId, String ojCode, String loginName, String password, Integer accepted, Integer submit) {
        this.userId = userId;
        this.ojCode = ojCode;
        this.loginName = loginName;
        this.password = password;
        this.accepted = accepted;
        this.submit = submit;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOjCode() {
        return ojCode;
    }

    public void setOjCode(String ojCode) {
        this.ojCode = ojCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }
}

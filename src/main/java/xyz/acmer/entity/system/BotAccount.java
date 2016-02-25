package xyz.acmer.entity.system;

import javax.persistence.*;

/**
 * 机器人用户表
 * 为没有绑定相关OJ账号的用户提供提交代码服务
 * Created by hypo on 16-2-26.
 */
@Entity
@Table(name = "bot_account")
public class BotAccount {

    private Integer id;
    private String loginName;
    private String password;
    private OjCode ojCode;

    public BotAccount() {
    }

    public BotAccount(String loginName, String password, OjCode ojCode) {
        this.loginName = loginName;
        this.password = password;
        this.ojCode = ojCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "loginname", nullable = false, length = 20)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    @JoinColumn(name = "oj_id")
    public OjCode getOjCode() {
        return ojCode;
    }

    public void setOjCode(OjCode ojCode) {
        this.ojCode = ojCode;
    }
}

package xyz.acmer.entity.user;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hypo on 16-2-27.
 */
@Entity
@Table(name = "balance")
public class Balance {

    private Long id;

    /**
     * 增减积分
     */
    private Integer num;

    /**
     * 操作后余额
     */
    private Integer newbalance;

    /**
     * 增减原因
     */
    private String reason;

    /**
     * 操作时间
     */
    private Date time;

    /**
     * 涉及用户
     */
    private User user;

    public Balance() {
    }

    public Balance(Integer num, Integer newbalance, String reason, User user) {
        this.num = num;
        this.newbalance = newbalance;
        this.reason = reason;
        this.time = new Date();
        this.user = user;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "number", nullable = false)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Column(name = "new_balance", nullable = false)
    public Integer getNewbalance() {
        return newbalance;
    }

    public void setNewbalance(Integer newbalance) {
        this.newbalance = newbalance;
    }

    @Column(name = "reason", nullable = false, length = 100)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

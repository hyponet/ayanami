package xyz.acmer.entity.problem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 题目信息表
 * Created by hypo on 16-2-2.
 */
@Entity
public class Problem {

    /**
     * 站内统一 pid
     */
    private Integer problemId;

    /**
     * OJ代号
     */
    private String ojCode;

    /**
     * 该OJ下的pid
     * 之所以使用String是因为部分OJ的pid包含字母
     */
    private String pid;

    /**
     * 题目标题
     * 在ayanami下，只存储标题，而内容等由makinami负责存储以及维护
     */
    private String title;

    /**
     * 在该OJ下AC数
     */
    private Integer acceptedNumber;

    /**
     * 在该OJ下提交数
     */
    private Integer submitNumber;

    /**
     * 抓取该题目的更新时间
     */
    private Date updateDate;

    public Problem(String ojCode, String pid, String title, Integer acceptedNumber, Integer submitNumber) {
        this.ojCode = ojCode;
        this.pid = pid;
        this.title = title;
        this.acceptedNumber = acceptedNumber;
        this.submitNumber = submitNumber;
        this.updateDate = new Date();
    }

    @Id
    @GeneratedValue
    @Column(name = "problem_id")
    public Integer getProblemId() {
        return problemId;
    }

    @Column(name = "oj_code", nullable = false, length = 10)
    public String getOjCode() {
        return ojCode;
    }

    public void setOjCode(String ojCode) {
        this.ojCode = ojCode;
    }

    @Column(name = "pid", nullable = false, length = 20)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "accepted", nullable = false)
    public Integer getAcceptedNumber() {
        return acceptedNumber;
    }

    public void setAcceptedNumber(Integer acceptedNumber) {
        this.acceptedNumber = acceptedNumber;
    }

    @Column(name = "submit", nullable = false)
    public Integer getSubmitNumber() {
        return submitNumber;
    }

    public void setSubmitNumber(Integer submitNumber) {
        this.submitNumber = submitNumber;
    }

    @Column(name = "update_date", nullable = false, columnDefinition = "TIMESTAMP")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate() {
        this.updateDate = new Date();
    }
}

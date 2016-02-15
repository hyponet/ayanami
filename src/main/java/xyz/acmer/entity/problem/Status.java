package xyz.acmer.entity.problem;

import xyz.acmer.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * 提交状态表
 * Created by hypo on 16-2-11.
 */
@Entity
public class Status {

    /**
     * 站内runid
     */
    private Long runId;

    /**
     * 提交者
     */
    private User submiter;

    /**
     * 题目
     */
    private Problem problem;

    /**
     * 返回状态
     */
    private String result;

    /**
     * 消耗内存
     */
    private String memory;

    /**
     * 运行时间
     */
    private String time;

    /**
     * 提交语言
     */
    private String language;

    /**
     * 代码长度
     */
    private String length;

    /**
     * 提交时间
     */
    private Date submitTime;

    public Status(User submiter, Problem problem, String result, String memory,
                  String time, String language, String length, Date submitTime) {

        this.submiter = submiter;
        this.problem = problem;
        this.result = result;
        this.memory = memory;
        this.time = time;
        this.language = language;
        this.length = length;
        this.submitTime = submitTime;
    }

    @Id
    @GeneratedValue
    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "submiter")
    public User getSubmiter() {
        return submiter;
    }

    public void setSubmiter(User submiter) {
        this.submiter = submiter;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "problem")
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @Column(name = "result", length = 40)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Column(name = "memory", length = 100)
    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Column(name = "time", length = 100)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "language", length = 20)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "length", length = 50)
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Column(name = "submit_time", nullable = false, columnDefinition = "TIMESTAMP")
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}

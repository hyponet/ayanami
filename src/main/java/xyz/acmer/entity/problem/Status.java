package xyz.acmer.entity.problem;

import xyz.acmer.entity.contest.ContestInfo;
import xyz.acmer.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * 提交状态表
 * Created by hypo on 16-2-11.
 */
@Entity
@Table(name = "status")
public class Status {

    /**
     * 站内runid
     */
    private Long runId;

    /**
     * 相关OJ的RunId
     */
    private Integer ojRunId;

    /**
     * 提交者
     */
    private User submiter;

    /**
     * 题目
     */
    private Problem problem;

    /**
     * 如果属于某场比赛，包含比赛信息
     * 可以为null
     */
    private ContestInfo contestInfo;

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

    /**
     * 只有默认构造函数
     * 但是默认构造后不能直接持久化需要把信息补全
     * 用户提交（new Status(),获得提交时间）=> makinami判题 => 补全结果 => 持久化
     */
    public Status(){

        this.submitTime = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "run_id")
    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long runId) {
        this.runId = runId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getSubmiter() {
        return submiter;
    }

    public void setSubmiter(User submiter) {
        this.submiter = submiter;
    }

    @Column(name = "oj_run_id", nullable = false)
    public Integer getOjRunId() {
        return ojRunId;
    }

    public void setOjRunId(Integer ojRunId) {
        this.ojRunId = ojRunId;
    }

    @ManyToOne
    @JoinColumn(name = "problem_id")
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "contest_id", nullable = true)
    public ContestInfo getContestInfo() {
        return contestInfo;
    }

    public void setContestInfo(ContestInfo contestInfo) {
        this.contestInfo = contestInfo;
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

    @Override
    public String toString() {
        return "Status{" +
                "runId=" + runId +
                ", ojRunId=" + ojRunId +
                ", submiter=" + submiter +
                ", problem=" + problem +
                ", result='" + result + '\'' +
                ", memory='" + memory + '\'' +
                ", time='" + time + '\'' +
                ", language='" + language + '\'' +
                ", length='" + length + '\'' +
                ", submitTime=" + submitTime +
                '}';
    }
}

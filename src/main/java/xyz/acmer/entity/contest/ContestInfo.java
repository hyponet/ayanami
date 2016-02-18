package xyz.acmer.entity.contest;

import xyz.acmer.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 比赛信息表
 * Created by hypo on 16-2-11.
 */
@Entity(name = "contest")
public class ContestInfo {

    private Integer contestId;
    private User creater;
    private String title;

    /**
     * 比赛开始时间
     * 比赛长度
     * 比赛密码（如果公开则留空）
     */
    private Date beginTime;
    private Integer length;
    private String password;

    /**
     * 比赛描述
     */
    private String description;

    /**
     * 比赛类型
     * 根据比赛类型的不同划分奖励规则
     */
    private ContestType contestType;

    /***
     * 比赛题目
     */
    private Set<ContestProblem> problems = new HashSet<ContestProblem>();

    /**
     * 比赛通知
     */
    private Set<ContestAnnouncement> announcements = new HashSet<ContestAnnouncement>();

    public ContestInfo(User creater, String title, Date beginTime, Integer length,
                       String password, String description, ContestType contestType) {
        this.creater = creater;
        this.title = title;
        this.beginTime = beginTime;
        this.length = length;
        this.password = password;
        this.description = description;
        this.contestType = contestType;
    }

    @Id
    @GeneratedValue
    @Column(name = "contest_id")
    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "begin_time", nullable = false, columnDefinition = "TIMESTAMP")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Column(name = "length", nullable = false)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contest_type")
    public ContestType getContestType() {
        return contestType;
    }

    public void setContestType(ContestType contestType) {
        this.contestType = contestType;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contest")
    public Set<ContestProblem> getProblems() {
        return problems;
    }

    public void setProblems(Set<ContestProblem> problems) {
        this.problems = problems;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contest")
    public Set<ContestAnnouncement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<ContestAnnouncement> announcements) {
        this.announcements = announcements;
    }
}

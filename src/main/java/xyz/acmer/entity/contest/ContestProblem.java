package xyz.acmer.entity.contest;

import xyz.acmer.entity.problem.Problem;

import javax.persistence.*;

/**
 * 比赛题目表
 * Created by hypo on 16-2-11.
 */
@Entity
public class ContestProblem {

    private Long problemId;
    private Problem problem;
    private ContestInfo contest;

    /**
     * 在比赛中题目使用自定义标题
     */
    private String title;

    /**
     * 本场比赛中AC数目
     */
    private Integer accepted;

    /**
     * 本场比赛中的提交数目
     */
    private Integer submit;

    public ContestProblem(Problem problem, ContestInfo contestId, String title) {
        this.problem = problem;
        this.contest = contestId;
        this.title = title;
        this.accepted = 0;
        this.submit = 0;
    }

    @Id
    @GeneratedValue
    public Long getProblemId() {
        return problemId;
    }

    @ManyToOne
    @JoinColumn(name = "problem")
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @ManyToOne
    @JoinColumn(name = "contest")
    public ContestInfo getContest() {
        return contest;
    }

    public void setContest(ContestInfo contest) {
        this.contest = contest;
    }

    @Column(name = "title", length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "accepted")
    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    @Column(name = "submit")
    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }
}

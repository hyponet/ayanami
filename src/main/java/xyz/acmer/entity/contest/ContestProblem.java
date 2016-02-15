package xyz.acmer.entity.contest;

/**
 * Created by hypo on 16-2-11.
 */
public class ContestProblem {
    private Long cpid;
    private Integer problemId;
    private Integer contestId;
    private String title;
    private Integer accepted;
    private Integer submit;

    public ContestProblem(Integer problemId, Integer contestId, String title) {
        this.problemId = problemId;
        this.contestId = contestId;
        this.title = title;
        this.accepted = 0;
        this.submit = 0;
    }

    public Long getCpid() {
        return cpid;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

package xyz.acmer.service.problems;

import java.util.Date;

/**
 * Created by hypo on 16-2-2.
 */
public class Problem {
    private Integer problemId;
    private String ojCode;
    private String pid;
    private String title;
    private Integer acceptedNumber;
    private Integer submitNumber;
    private Date updateDate;

    public Problem(String ojCode, String pid, String title) {
        this.ojCode = ojCode;
        this.pid = pid;
        this.title = title;
        this.acceptedNumber = 0;
        this.submitNumber = 0;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public String getOjCode() {
        return ojCode;
    }

    public void setOjCode(String ojCode) {
        this.ojCode = ojCode;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAcceptedNumber() {
        return acceptedNumber;
    }

    public void setAcceptedNumber(Integer acceptedNumber) {
        this.acceptedNumber = acceptedNumber;
    }

    public Integer getSubmitNumber() {
        return submitNumber;
    }

    public void setSubmitNumber(Integer submitNumber) {
        this.submitNumber = submitNumber;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate() {
        this.updateDate = new Date();
    }
}

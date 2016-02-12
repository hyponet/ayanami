package xyz.acmer.service.problems;

import java.util.Date;

/**
 * Created by hypo on 16-2-11.
 */
public class Status {
    private Long runId;
    private Integer submiterId;
    private Integer problemId;
    private String result;
    private String memory;
    private String time;
    private String language;
    private String length;
    private Date submitTime;

    public Status(Integer submiterId, Integer problemId, String result, String memory, String time, String language, String length, Date submitTime) {
        this.submiterId = submiterId;
        this.problemId = problemId;
        this.result = result;
        this.memory = memory;
        this.time = time;
        this.language = language;
        this.length = length;
        this.submitTime = submitTime;
    }

    public Long getRunId() {
        return runId;
    }

    public Integer getSubmiterId() {
        return submiterId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public String getResult() {
        return result;
    }

    public String getMemory() {
        return memory;
    }

    public String getTime() {
        return time;
    }

    public String getLanguage() {
        return language;
    }

    public String getLength() {
        return length;
    }

    public Date getSubmitTime() {
        return submitTime;
    }
}

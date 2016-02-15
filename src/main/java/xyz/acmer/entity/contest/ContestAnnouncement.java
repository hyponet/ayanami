package xyz.acmer.entity.contest;

import java.util.Date;

/**
 * Created by hypo on 16-2-11.
 */
public class ContestAnnouncement {

    private Long announcementId;
    private Integer contestId;
    private String title;
    private String context;
    private String autherName;
    private Date submitTime;

    public ContestAnnouncement(Integer contestId, String title, String context, String autherName) {
        this.contestId = contestId;
        this.title = title;
        this.context = context;
        this.autherName = autherName;

        this.submitTime = new Date();
    }

    public Long getAnnouncementId() {
        return announcementId;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}

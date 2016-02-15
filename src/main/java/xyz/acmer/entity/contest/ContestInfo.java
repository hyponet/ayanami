package xyz.acmer.entity.contest;

import java.util.Date;

/**
 * Created by hypo on 16-2-11.
 */
public class ContestInfo {
    private Integer contestId;
    private Integer createrId;
    private String title;
    private Date beginTime;
    private Integer length;
    private String password;
    private String description;
    private String contestType;
    private Integer reward;

    public ContestInfo(Integer createrId, String title, Date beginTime, Integer length,
                       String password, String description, String contestType, Integer reward) {
        this.createrId = createrId;
        this.title = title;
        this.beginTime = beginTime;
        this.length = length;
        this.password = password;
        this.description = description;
        this.contestType = contestType;
        this.reward = reward;
    }

    public Integer getContestId() {
        return contestId;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }
}

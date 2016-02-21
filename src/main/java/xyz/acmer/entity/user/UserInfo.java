package xyz.acmer.entity.user;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户详细资料表
 * Created by hypo on 16-2-11.
 */
@Entity
@Table(name = "user_info")
public class UserInfo {

    private Long infoId;
    private User user;

    /**
     * 个人信息
     */
    private Date birthday;
    private String webSite;
    private String company;
    private String school;
    private Date joinTime;

    /**
     * tagLine：签名
     */
    private String tagLine;

    /**
     * 社交账号
     */
    private String github;
    private String psnId;
    private String steamId;
    private String telegram;
    private String twitter;
    private String codingNet;

    /**
     * info : 个人简介
     */
    private String info;

    /**
     * 提供订阅的feed
     */
    private String feedUrl;

    public UserInfo(){

    }

    public UserInfo(User user) {
        this.user = user;
        this.joinTime = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "birthday", columnDefinition = "DATE")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "url", length = 50)
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Column(name = "company", length = 60)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "school", length = 60)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Column(name = "tagline")
    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    @Column(name = "join_time", columnDefinition = "TIMESTAMP")
    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    @Column(name = "github", length = 50)
    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Column(name = "psn", length = 50)
    public String getPsnId() {
        return psnId;
    }

    public void setPsnId(String psnId) {
        this.psnId = psnId;
    }

    @Column(name = "steam", length = 50)
    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    @Column(name = "telegram", length = 50)
    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    @Column(name = "twitter", length = 50)
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Column(name = "coding", length = 50)
    public String getCodingNet() {
        return codingNet;
    }

    public void setCodingNet(String codingNet) {
        this.codingNet = codingNet;
    }

    public String getInfo() {
        return info;
    }

    @Column(name = "info", length = 500, columnDefinition = "TEXT")
    public void setInfo(String info) {
        this.info = info;
    }

    @Column(name = "feed")
    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }
}

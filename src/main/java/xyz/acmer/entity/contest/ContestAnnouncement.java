package xyz.acmer.entity.contest;

import javax.persistence.*;
import java.util.Date;

/**
 * 比赛公告表 保存比赛中的管理员发布的公告
 * Created by hypo on 16-2-11.
 */
@Entity
@Table(name = "contest_announcement")
public class ContestAnnouncement {

    private Long announcementId;

    /**
     * 比赛：属于哪场比赛的公告
     */
    private ContestInfo contest;

    /**
     * 标题
     * 会及时推送标题给用户，不可为空
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 署名
     * 就是留个梗，毕竟只有创建者才有权限发布公告
     */
    private String autherName;

    /**
     * 发布时间 自动生成
     */
    private Date submitTime;

    public ContestAnnouncement(ContestInfo contest, String title, String context, String autherName) {
        this.contest = contest;
        this.title = title;
        this.content = context;
        this.autherName = autherName;

        this.submitTime = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "contest_id")
    public ContestInfo getContest() {
        return contest;
    }

    public void setContest(ContestInfo contest) {
        this.contest = contest;
    }

    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String context) {
        this.content = context;
    }

    @Column(name = "auther", length = 50)
    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    @Column(name = "submit_time", columnDefinition = "TIMESTAMP")
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}

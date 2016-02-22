package xyz.acmer.entity.system;

import xyz.acmer.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统公告表
 * Created by hypo on 16-2-22.
 */
@Entity
@Table(name = "announcement")
public class SystemAnnouncement {

    private Long id;

    /**
     * 标题
     * 会及时推送标题到页面，不可为空
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 署名
     */
    private String autherName;

    /**
     * 发布时间 自动生成
     */
    private Date submitTime;

    public SystemAnnouncement() {
    }

    public SystemAnnouncement(String title, String content, User auther) {
        this.title = title;
        this.content = content;
        this.autherName = auther.getUserName();

        this.submitTime = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "auther", nullable = false, length = 20)
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

package xyz.acmer.entity.system;

import javax.persistence.*;

/**
 * Ayanami依赖的微服务系统Makinami列表
 * Created by hypo on 16-2-22.
 */
@Entity
@Table(name = "makinami_list")
public class MakinamiList {

    private Integer id;
    private String url;
    private String type;
    private Long useTimes = 0l;

    public MakinamiList(){

    }

    public MakinamiList(String url, String type) {
        this.url = url;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "url", nullable = false, length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "type", nullable = false, length = 15)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "use_times")
    public Long getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Long useTimes) {
        this.useTimes = useTimes;
    }
}

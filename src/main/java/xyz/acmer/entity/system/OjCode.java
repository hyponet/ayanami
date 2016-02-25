package xyz.acmer.entity.system;

import javax.persistence.*;

/**
 * OjCode表
 * 记录所支持OJ的信息
 * Created by hypo on 16-2-26.
 */
@Entity
@Table(name = "oj_code")
public class OjCode {

    private Integer id;
    private String ojCode;
    private String ojName;
    private String ojUrl;
    private String type;

    public OjCode(){

    }

    public OjCode(String ojCode, String ojName, String ojUrl, String type) {
        this.ojCode = ojCode;
        this.ojName = ojName;
        this.ojUrl = ojUrl;

        if(type.equals("external")){
            this.type = type;
        }else {
            this.type = "internal";
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oj_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "oj_code", nullable = false, length = 10)
    public String getOjCode() {
        return ojCode;
    }

    public void setOjCode(String ojCode) {
        this.ojCode = ojCode;
    }

    @Column(name = "oj_name", nullable = false, length = 50)
    public String getOjName() {
        return ojName;
    }

    public void setOjName(String ojName) {
        this.ojName = ojName;
    }

    @Column(name = "oj_url", length = 50)
    public String getOjUrl() {
        return ojUrl;
    }

    public void setOjUrl(String ojUrl) {
        this.ojUrl = ojUrl;
    }

    @Column(name = "type", nullable = false, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(type.equals("external")){
            this.type = type;
        }else {
            this.type = "internal";
        }
    }

    @Override
    public String toString() {
        return this.ojCode;
    }
}

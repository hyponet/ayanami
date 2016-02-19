package xyz.acmer.entity.contest;

import javax.persistence.*;

/**
 * 比赛类型表
 * Created by hypo on 16-2-16.
 */
@Entity
@Table(name = "contest_type")
public class ContestType {

    private Integer typeId;
    private String typeName;

    /**
     * 创建花费铜币数目
     */
    private Integer createSpend;

    /**
     * 第一滴血（每个题的第一个AC）的奖励铜币数
     */
    private Integer firstBlood;

    /**
     * 最终榜冠军奖励铜币数
     */
    private Integer champion;

    /**
     * 最终榜亚军奖励铜币数
     */
    private Integer second;

    /**
     * 最终榜季军奖励铜币数
     */
    private Integer third;

    /**
     * 每次提交成功后花费的铜币数
     */
    private Integer submitSpend;

    /**
     * 每次得到AC后奖励的铜币数
     */
    private Integer acceptedAward;

    public ContestType(String typeName, Integer createSpend, Integer firstBlood, Integer champion,
                       Integer second, Integer third, Integer submitSpend, Integer acceptedAward) {
        this.typeName = typeName;
        this.createSpend = Math.abs(createSpend);
        this.firstBlood = Math.abs(firstBlood);
        this.champion = Math.abs(champion);
        this.second = Math.abs(second);
        this.third = Math.abs(third);
        this.submitSpend = Math.abs(submitSpend);
        this.acceptedAward = Math.abs(acceptedAward);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Column(name = "type_name", nullable = false, length = 20)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(name = "create_spend", nullable = false)
    public Integer getCreateSpend() {
        return createSpend;
    }

    public void setCreateSpend(Integer createSpend) {
        this.createSpend = Math.abs(createSpend);
    }

    @Column(name = "first_blood", nullable = false)
    public Integer getFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(Integer firstBlood) {
        this.firstBlood = Math.abs(firstBlood);
    }

    @Column(name = "champion", nullable = false)
    public Integer getChampion() {
        return champion;
    }

    public void setChampion(Integer champion) {
        this.champion = Math.abs(champion);
    }

    @Column(name = "second", nullable = false)
    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = Math.abs(second);
    }

    @Column(name = "third", nullable = false)
    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = Math.abs(third);
    }

    @Column(name = "submit_spend", nullable = false)
    public Integer getSubmitSpend() {
        return submitSpend;
    }

    public void setSubmitSpend(Integer submitSpend) {
        this.submitSpend = Math.abs(submitSpend);
    }

    @Column(name = "accepted_award", nullable = false)
    public Integer getAcceptedAward() {
        return acceptedAward;
    }

    public void setAcceptedAward(Integer acceptedAward) {
        this.acceptedAward = Math.abs(acceptedAward);
    }
}

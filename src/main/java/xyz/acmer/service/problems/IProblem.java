package xyz.acmer.service.problems;

import java.util.Date;

/**
 * Created by hypo on 16-2-2.
 */
public interface IProblem {
    Integer getProblemId();
    String getOjCode();
    void setOjCode(String ojCode);
    String getPid();
    void setPid(String pid);
    String getTitle();
    void setTitle(String title);
    Integer getAcceptedNumber();
    void setAcceptedNumber(Integer acceptedNumber);
    Integer getSubmitNumber();
    void setSubmitNumber(Integer submitNumber);
    Date getUpdateDate();
}

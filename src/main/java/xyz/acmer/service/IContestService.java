package xyz.acmer.service;

import xyz.acmer.entity.contest.ContestAnnouncement;
import xyz.acmer.entity.contest.ContestInfo;
import xyz.acmer.entity.contest.ContestProblem;
import xyz.acmer.entity.contest.ContestType;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.problem.Status;
import xyz.acmer.entity.user.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by hypo on 16-2-26.
 */
public interface IContestService {

    /**
     * 新增比赛
     * @param title 比赛标题
     * @param beginTime 开始时间
     * @param length 比赛时间（长度）
     * @param password 比赛密码（可为空）
     * @param description 描述
     * @param contestType 比赛类型（奖罚与类型有关）
     * @param problems 添加的题目
     * @param user 创建者（对该比赛有绝对权限）
     * @return
     */
    ContestInfo addContest(String title, Date beginTime, Integer length, String password, String description,
                           ContestType contestType, Set<ContestProblem> problems, User user);

    /**
     * 更新比赛信息
     * #不允许删除比赛#，因为每次用户积分的变动需要有稽可查
     * @param title 更新的标题
     * @param beginTime 更新开始时间，如果比赛已经开始，这不能修改
     * @param length 比赛长度，可以延时
     * @param password 更新密码，可为空
     * @param description 更新描述
     * @param contestType 如果比赛开始，类型不能再修改
     * @param problems 更新题目， 如果比赛开始，题目不能被修改
     * @return
     */
    ContestInfo updateContest(ContestInfo contestInfo, String title, Date beginTime,
                              Integer length, String password, String description,
                              ContestType contestType, Set<ContestProblem> problems);

    /**
     * 调用ProblemService的方法提交程序并返回结果
     * 结果返回成功后根据比赛类型对用户积分进行更新
     * @param problem
     * @param code
     * @param language
     * @param user
     * @param contestInfo
     * @return
     */
    Status submitProblem(ContestProblem problem, String code,String language, User user, ContestInfo contestInfo);

    /**
     * 获得比赛列表（根据开始时间排序）
     * @return
     */
    List<ContestInfo> getAllContest();

    /**
     * 根据时间（未开始 进行中 已结束）获得比赛列表
     * @param time scheduled | running | ended
     * @return
     */
    List<ContestInfo> getContestByTime(String time);

    /**
     * 根据比赛类型获得比赛列表
     * @param contestType
     * @return
     */
    List<ContestInfo> getContestByType(ContestType contestType);

    /**
     * 新增比赛类型
     * @param typeName 类型名称
     * @param createSpend 创建该比赛的花费
     * @param firstBlood 一血的奖励
     * @param champion 冠军奖励
     * @param second 亚军奖励
     * @param third 季军奖励
     * @param submitSpend 每次提交的花费
     * @param acceptedAward 每次AC的奖励
     * @return
     */
    ContestType addContestType(String typeName, Integer createSpend, Integer firstBlood, Integer champion,
                               Integer second, Integer third, Integer submitSpend, Integer acceptedAward);

    /**
     * 更新比赛类型
     * @param typeName 类型名称
     * @param createSpend 创建该比赛的花费
     * @param firstBlood 一血的奖励
     * @param champion 冠军奖励
     * @param second 亚军奖励
     * @param third 季军奖励
     * @param submitSpend 每次提交的花费
     * @param acceptedAward 每次AC的奖励
     * @return
     */
    Boolean updateContestType(String typeName, Integer createSpend, Integer firstBlood,
                              Integer champion, Integer second, Integer third, Integer submitSpend,
                              Integer acceptedAward, ContestType contestType);

    /**
     * 删除比赛类型
     * 因为外键的关系，如果该类型已经被添加比赛，则不能删除
     * 建议更新比赛类型而不是删除
     * @param contestTypeId
     * @return
     */
    Boolean deleteContestType(Integer contestTypeId);

    /**
     * 获得所有的比赛类型
     * @return
     */
    List<ContestType> getAllContestType();

    /**
     * 新增比赛公告
     * @param title 公告标题
     * @param content 公告内容
     * @param autherName 作者名称
     * @param contest 所属比赛
     * @return
     */
    ContestAnnouncement addContestAnnouncement(User user, String title, String content, String autherName, ContestInfo contest);

    /**
     * 更新比赛公告内容
     * @param title 公告标题
     * @param content 公告内容
     * @param autherName 作者名称
     * @param contestAnnouncementId 所需更新的公告
     * @return
     */
    Boolean updateContestAnnouncement(String title, String content, String autherName,
                                      Long contestAnnouncementId, User user);

    /**
     * 删除公告
     * @param contestAnnouncementId 要删除的公告ID
     * @param user 操作用户（验证权限，只有比赛创建者有权限删除）
     * @return
     */
    Boolean deleteContestAnnouncement(Long contestAnnouncementId, User user);

}

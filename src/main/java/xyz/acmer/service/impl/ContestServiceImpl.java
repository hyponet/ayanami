package xyz.acmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.acmer.entity.contest.ContestAnnouncement;
import xyz.acmer.entity.contest.ContestInfo;
import xyz.acmer.entity.contest.ContestProblem;
import xyz.acmer.entity.contest.ContestType;
import xyz.acmer.entity.problem.Status;
import xyz.acmer.entity.user.User;
import xyz.acmer.repository.contest.ContestAnnouncementRepository;
import xyz.acmer.repository.contest.ContestInfoRepository;
import xyz.acmer.repository.contest.ContestProblemRepository;
import xyz.acmer.repository.contest.ContestTypeRepository;
import xyz.acmer.repository.problem.StatusRepository;
import xyz.acmer.service.IBalanceService;
import xyz.acmer.service.IContestService;
import xyz.acmer.service.IProblemService;
import xyz.acmer.util.StringHelper;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 比赛相关Service
 * Created by hypo on 16-2-26.
 */
@Service
public class ContestServiceImpl implements IContestService{

    @Autowired
    private ContestInfoRepository contestInfoRepository;

    @Autowired
    private ContestProblemRepository contestProblemRepository;

    @Autowired
    private ContestTypeRepository contestTypeRepository;

    @Autowired
    private ContestAnnouncementRepository contestAnnouncementRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private IProblemService problemService;

    @Autowired
    private IBalanceService balanceService;

    /**
     * 新增比赛
     *
     * @param title       比赛标题
     * @param beginTime   开始时间
     * @param length      比赛时间（长度）
     * @param password    比赛密码（可为空）
     * @param description 描述
     * @param contestType 比赛类型（奖罚与类型有关）
     * @param problems    添加的题目
     * @param user        创建者（对该比赛有绝对权限）
     * @return
     */
    @Override
    public ContestInfo addContest(String title, Date beginTime, Integer length, String password, String description,
                                  ContestType contestType, Set<ContestProblem> problems, User user) {

        title = StringHelper.getSafeString(title, 100);
        description = StringHelper.getSafeString(description, 255);
        if(password != null && password.length() == 0){
            password = null;
        }

        ContestInfo contestInfo = new ContestInfo(user, title, beginTime, length, password, description, contestType);
        contestInfo.setProblems(problems);

        // 扣取创建该比赛所需积分
        balanceService.spend(user, contestType.getCreateSpend(), "创建比赛 [" + title + "]");

        contestInfoRepository.save(contestInfo);

        return contestInfo;
    }

    /**
     * 更新比赛信息
     * #不允许删除比赛#，因为每次用户积分的变动需要有稽可查
     *
     * @param title       更新的标题
     * @param beginTime   更新开始时间，如果比赛已经开始，这不能修改
     * @param length      比赛长度，可以延时
     * @param password    更新密码，可为空
     * @param description 更新描述
     * @param contestType 如果比赛开始，类型不能再修改
     * @param problems    更新题目， 如果比赛开始，题目不能被修改
     * @return
     */
    @Override
    public ContestInfo updateContest(ContestInfo contestInfo, String title, Date beginTime,
                                     Integer length, String password, String description,
                                     ContestType contestType, Set<ContestProblem> problems) {

        Date nowDate = new Date();
        Date oldBeginTime = contestInfo.getBeginTime();

        title = StringHelper.getSafeString(title, 100);
        description = StringHelper.getSafeString(description, 255);
        if(password != null && password.length() == 0){
            password = null;
        }

        contestInfo.setTitle(title);
        contestInfo.setDescription(description);
        contestInfo.setPassword(password);
        contestInfo.setLength(length);

        if(oldBeginTime.after(nowDate)){

            contestInfo.setBeginTime(beginTime);
            contestInfo.setContestType(contestType);
            contestInfo.setProblems(problems);
        }

        contestInfoRepository.save(contestInfo);

        return contestInfo;
    }

    /**
     * 调用ProblemService的方法提交程序并返回结果
     * 结果返回成功后根据比赛类型对用户积分进行更新
     *
     * @param problem
     * @param code
     * @param language
     * @param user
     * @param contestInfo
     * @return
     */
    @Override
    public Status submitProblem(ContestProblem problem, String code, String language,
                                User user, ContestInfo contestInfo) {

        Status status = problemService.submitProblem(problem.getProblem(), code, language, user, contestInfo);

        if(status == null){
            return null;
        }

        ContestType contestType = contestInfo.getContestType();
        balanceService.spend(user, contestType.getSubmitSpend(), "在[" + contestInfo.getTitle()+ "]比赛中,向 " +
                 problem.getTitle() + " 提交代码");

        if(status.getResult().equals("Accepted")){

            balanceService.get(user, contestType.getAcceptedAward(), "在[" + contestInfo.getTitle()+ "]比赛中,手刃 " +
                    problem.getTitle());

            List<Status> statuses = statusRepository.getStatusByContestInfoAndProblem(contestInfo, problem.getProblem());

            if(statuses == null || statuses.size() == 1){
                // 一血
                balanceService.get(user, contestType.getAcceptedAward(), "在[" + contestInfo.getTitle()+ "]比赛中,获得题目 " +
                        problem.getTitle() + " 一血");
            }
        }

        return status;
    }

    /**
     * 获得比赛列表（根据开始时间排序）
     *
     * @return
     */
    @Override
    public List<ContestInfo> getAllContest() {
        return null;
    }

    /**
     * 根据时间（未开始 进行中 已结束）获得比赛列表
     *
     * @param time
     * @return
     */
    @Override
    public List<ContestInfo> getContestByTime(String time) {
        return null;
    }

    /**
     * 根据比赛类型获得比赛列表
     *
     * @param contestType
     * @return
     */
    @Override
    public List<ContestInfo> getContestByType(ContestType contestType) {
        return null;
    }

    /**
     * 新增比赛类型
     *
     * @param typeName      类型名称
     * @param createSpend   创建该比赛的花费
     * @param firstBlood    一血的奖励
     * @param champion      冠军奖励
     * @param second        亚军奖励
     * @param third         季军奖励
     * @param submitSpend   每次提交的花费
     * @param acceptedAward 每次AC的奖励
     * @return
     */
    @Override
    public ContestType addContestType(String typeName, Integer createSpend, Integer firstBlood,
                                      Integer champion, Integer second, Integer third,
                                      Integer submitSpend, Integer acceptedAward) {
        return null;
    }

    /**
     * 更新比赛类型
     *
     * @param typeName      类型名称
     * @param createSpend   创建该比赛的花费
     * @param firstBlood    一血的奖励
     * @param champion      冠军奖励
     * @param second        亚军奖励
     * @param third         季军奖励
     * @param submitSpend   每次提交的花费
     * @param acceptedAward 每次AC的奖励
     * @return
     */
    @Override
    public Boolean updateContestType(String typeName, Integer createSpend, Integer firstBlood,
                                     Integer champion, Integer second, Integer third,
                                     Integer submitSpend, Integer acceptedAward) {
        return null;
    }

    /**
     * 删除比赛类型
     * 因为外键的关系，如果该类型已经被添加比赛，则不能删除
     * 建议更新比赛类型而不是删除
     *
     * @param contestTypeId
     * @return
     */
    @Override
    public Boolean deleteContestType(Integer contestTypeId) {
        return null;
    }

    /**
     * 获得所有的比赛类型
     *
     * @return
     */
    @Override
    public List<ContestType> getAllContestType() {
        return null;
    }

    /**
     * 新增比赛公告
     *
     * @param title      公告标题
     * @param content    公告内容
     * @param autherName 作者名称
     * @param contest    所属比赛
     * @return
     */
    @Override
    public ContestAnnouncement addContestAnnouncement(String title, String content,
                                                      String autherName, ContestInfo contest) {
        return null;
    }

    /**
     * 更新比赛公告内容
     *
     * @param title               公告标题
     * @param content             公告内容
     * @param autherName          作者名称
     * @param contestAnnouncement 所需更新的公告
     * @return
     */
    @Override
    public Boolean updateContestAnnouncement(String title, String content, String autherName,
                                             ContestAnnouncement contestAnnouncement) {
        return null;
    }

    /**
     * 删除公告
     *
     * @param contestAnnouncementId 要删除的公告ID
     * @param user                  操作用户（验证权限，只有比赛创建者有权限删除）
     * @return
     */
    @Override
    public Boolean deleteContestAnnouncement(Long contestAnnouncementId, User user) {
        return null;
    }
}

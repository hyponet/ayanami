package xyz.acmer.service;

import xyz.acmer.entity.contest.ContestInfo;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.problem.Status;
import xyz.acmer.entity.system.OjCode;
import xyz.acmer.entity.user.User;

/**
 * 题目service接口
 * Created by hypo on 16-2-26.
 */
public interface IProblemService {

    /**
     * 添加新oj之后，获得oj中所有题目
     * @param ojCode
     */
    void getAllProblem(OjCode ojCode);

    /**
     * 定时任务 定时更新题库
     * @param ojCode
     */
    void updateAllProblem(OjCode ojCode);

    /**
     * 如果添加题目时题库中不存在，便向makinami请求爬取
     * @param ojCode
     * @param pid
     * @return
     */
    Problem getProblem(OjCode ojCode,String pid);

    /**
     * 提交题目并返回状态
     * @param problem
     * @param code 代码需要被base64加密
     * @param language
     * @param user
     * @param contestInfo 如果属于比赛的status，这注入比赛信息
     * @return
     */
    Status submitProblem(Problem problem, String code, String language, User user, ContestInfo contestInfo);

    /**
     * 如果获得的题目状态为Waiting等
     * 需要再次查询结果
     *
     * @param ojCode
     * @param runId
     * @return
     */
    Status updateStatus(OjCode ojCode, Long runId);
}

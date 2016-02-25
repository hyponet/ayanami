package xyz.acmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.problem.Status;
import xyz.acmer.entity.system.OjCode;
import xyz.acmer.entity.user.User;
import xyz.acmer.entity.user.UserAccount;
import xyz.acmer.repository.problem.ProblemRepository;
import xyz.acmer.repository.user.UserAccountRepository;
import xyz.acmer.service.IMakinamiService;
import xyz.acmer.service.IProblemService;
import xyz.acmer.util.EncryptHelper;

import java.util.List;

/**
 * Created by hypo on 16-2-26.
 */
@Service
@Transactional
public class ProblemServiceImpl implements IProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private IMakinamiService makinamiService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    /**
     * 添加新oj之后，获得oj中所有题目
     *
     * @param ojCode
     */
    @Override
    public void getAllProblem(OjCode ojCode) {
        List<Problem> problems = makinamiService.getAllProblem(ojCode);

        for(Problem problem : problems){
            if(problem != null){
                if(problem.getTitle().length() > 0){
                    problemRepository.save(problem);
                }
            }
        }
    }

    /**
     * 定时任务 定时更新题库
     *
     * @param ojCode
     */
    @Override
    public void updateAllProblem(OjCode ojCode) {

        List<Problem> problems = makinamiService.getAllProblem(ojCode);

        for(Problem newProblem : problems){
            if(newProblem != null){
                if(newProblem.getPid() != null && newProblem.getTitle().length() > 0){
                    Problem oldProblem = problemRepository.getProblemByOjCodeAndPid(ojCode, newProblem.getPid());

                    if(oldProblem == null){
                        problemRepository.save(newProblem);
                    }else {
                        oldProblem.setTitle(newProblem.getTitle());
                        oldProblem.setUpdateDate(newProblem.getUpdateDate());
                        problemRepository.save(oldProblem);
                    }
                }
            }
        }
    }

    /**
     * 如果添加题目时题库中不存在，便向makinami请求爬取
     *
     * @param ojCode
     * @param pid
     * @return
     */
    @Override
    public Problem getProblem(OjCode ojCode, String pid) {

        Problem problem = problemRepository.getProblemByOjCodeAndPid(ojCode, pid);

        if(problem != null){
            return problem;
        }

        problem = makinamiService.getProblem(ojCode, pid);

        if(problem != null){
            if(problem.getTitle().length() > 0){
                problemRepository.save(problem);
            }
        }
        return problem;
    }

    /**
     * 提交题目并返回状态
     *
     * @param problem
     * @param code     代码需要被base64加密
     * @param language
     * @param user
     * @return
     */
    @Override
    public Status submitProblem(Problem problem, String code,String language, User user) {

        UserAccount userAccount = userAccountRepository.getAcciuntByUserAndOj(user, problem.getOjCode());
        String loginname = userAccount.getLoginName();
        String password = userAccount.getPassword();

        Status status = makinamiService.submitProblem(problem.getOjCode(), problem.getPid(),
                EncryptHelper.getBase64(code), language, loginname, password);
        status.setSubmiter(user);
        status.setProblem(problem);

        // 更新提交/AC信息
        if(status.getResult().equals("")){
            userAccount.setAccepted(userAccount.getAccepted() + 1);
            problem.setAcceptedNumber(problem.getAcceptedNumber() + 1);
        }
        userAccount.setSubmit(userAccount.getSubmit() + 1);
        problem.setSubmitNumber(problem.getSubmitNumber() + 1);

        userAccountRepository.save(userAccount);
        problemRepository.save(problem);

        return status;
    }

    /**
     * 如果获得的题目状态为Waiting等
     * 需要再次查询结果
     *
     * @param ojCode
     * @param status
     * @return
     */
    @Override
    public Status updateStatus(OjCode ojCode, Status status) {

        status = makinamiService.updateStatus(ojCode, status);
        return null;
    }
}

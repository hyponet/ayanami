package xyz.acmer.service;

import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.problem.Status;
import xyz.acmer.entity.system.MakinamiList;
import xyz.acmer.entity.system.OjCode;
import xyz.acmer.entity.user.UserAccount;

import java.util.List;

/**
 * Makinami服务层接口 完成对makinami 的 管理 交互
 * Created by hypo on 16-2-22.
 */
public interface IMakinamiService {

    /**
     * ↓==== 对makinami的管理 ====↓
     */
    MakinamiList addInternal(String url);
    MakinamiList addExternal(String url);
    List<MakinamiList> getAll();
    MakinamiList update(Integer id, String url);
    Boolean delete(Integer id);

    /**
     * ↓==== 和makinami的交互 ====↓
     */

    /**
     * 获得用户账号
     * @param loginname
     * @param password
     * @param ojCode
     * @return
     */
    UserAccount getUserAccount(String loginname, String password, OjCode ojCode);

    /**
     * 获得一个OJ的所有题目
     * @param ojCode
     * @return
     */
    List<Problem> getAllProblem(OjCode ojCode);

    /**
     * 获得指定题目
     * @param ojCode
     * @param pid
     * @return
     */
    Problem getProblem(OjCode ojCode, String pid);

    /**
     * 提交代码并获得状态
     * @param ojCode 要提交的OJ
     * @param pid 题目ID
     * @param code 提交的代码 ！要base64加密！
     * @param language 使用的语言
     * @param loginname 登录名
     * @param password 登录密码
     * @return
     */
    Status submitProblem(OjCode ojCode, String pid, String code,
                         String language, String loginname, String password);

    /**
     * 更新OJ中制定runid的结果
     * @param ojCode
     * @param status
     * @return
     */
    Status updateStatus(OjCode ojCode, Status status);
}

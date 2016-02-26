package xyz.acmer.service;

import xyz.acmer.entity.user.User;

/**
 * 积分管理Service
 * 本来打算根据不同愿意划分函数，但是一个积分Service凭啥管理比赛和讨论呢？
 * 因此抽象通用方法，在调用时封装reason传入
 * Created by hypo on 16-2-27.
 */
public interface IBalanceService {

    /**
     * 增加积分
     * @param user
     * @param addNum
     * @param reason
     * @return
     */
    Boolean get(User user, Integer addNum, String reason);

    /**
     * 减少积分
     * @param user
     * @param subNum
     * @param reason
     * @return
     */
    Boolean spend(User user, Integer subNum, String reason);

    /**
     * 每日签到（10 - 100 随机）
     * 每10天连续签到额外增加200
     * @param user
     * @return
     */
    Boolean daily(User user);
}

package xyz.acmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.acmer.entity.user.Balance;
import xyz.acmer.entity.user.User;
import xyz.acmer.repository.user.BalanceRepository;
import xyz.acmer.repository.user.UserRepository;
import xyz.acmer.service.IBalanceService;

/**
 * Created by hypo on 16-2-27.
 */
@Service
public class BalanceServiceImpl implements IBalanceService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    /**
     * 增加积分
     *
     * @param user
     * @param addNum
     * @param reason
     * @return
     */
    @Override
    public Boolean get(User user, Integer addNum, String reason) {

        if(addNum <= 0){
            return false;
        }

        user.setBalance(user.getBalance() + addNum);
        userRepository.save(user);

        Balance balance = new Balance(addNum, user.getBalance(), reason, user);
        balanceRepository.save(balance);

        return true;
    }

    /**
     * 减少积分
     *
     * @param user
     * @param subNum
     * @param reason
     * @return
     */
    @Override
    public Boolean spend(User user, Integer subNum, String reason) {

        if(subNum <= 0){
            return false;
        }

        Integer newBalance = user.getBalance() - subNum;
        user.setBalance(newBalance < 0 ? 0 : newBalance);
        userRepository.save(user);

        Balance balance = new Balance(subNum * -1, user.getBalance(), reason, user);
        balanceRepository.save(balance);

        return true;
    }

    /**
     * 每日签到（10 - 100 随机）
     * 每10天连续签到额外增加200
     *
     * @param user
     * @return
     */
    @Override
    public Boolean daily(User user) {
        return null;
    }
}

package xyz.acmer.service;

import xyz.acmer.entity.system.MakinamiList;
import xyz.acmer.entity.user.UserAccount;

import java.util.List;

/**
 * Makinami服务层接口 完成对makinami 的 管理 交互
 * Created by hypo on 16-2-22.
 */
public interface IMakinamiService {

    /**
     * 对makinami的管理
     */
    MakinamiList addInternal(String url);
    MakinamiList addExternal(String url);
    List<MakinamiList> getAll();
    MakinamiList update(Integer id, String url);
    Boolean delete(Integer id);

    /**
     * 和makinami的交互
     */
    UserAccount getUserAccount(String loginname, String password, String ojCode);
}

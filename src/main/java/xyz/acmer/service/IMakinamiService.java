package xyz.acmer.service;

import xyz.acmer.entity.system.MakinamiList;

import java.util.List;

/**
 * Makinami服务层接口 完成对makinami url的管理，使用
 * Created by hypo on 16-2-22.
 */
public interface IMakinamiService {

    MakinamiList addInternal(String url);

    MakinamiList addExternal(String url);

    String getInternal();

    String getExternal();

    List<MakinamiList> getAll();

    MakinamiList update(Integer id, String url);

    Boolean delete(Integer id);
}

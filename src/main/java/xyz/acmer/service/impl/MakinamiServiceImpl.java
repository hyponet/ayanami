package xyz.acmer.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.system.MakinamiList;
import xyz.acmer.entity.user.UserAccount;
import xyz.acmer.repository.system.MakinamiRepository;
import xyz.acmer.service.IMakinamiService;
import xyz.acmer.util.JsonSender;

import java.util.List;

/**
 * Makinami 服务层的实现
 * Created by hypo on 16-2-22.
 */
@Service
@Transactional
public class MakinamiServiceImpl implements IMakinamiService {

    @Autowired
    private MakinamiRepository makinamiRepository;

    private  static Integer internalTime = 0;
    private  static Integer externalTime = 0;

    @Override
    public MakinamiList addInternal(String url) {

        MakinamiList makinamiList = new MakinamiList(url, "internal");

        makinamiRepository.save(makinamiList);

        return makinamiList;
    }

    @Override
    public MakinamiList addExternal(String url) {

        MakinamiList makinamiList = new MakinamiList(url, "external");

        makinamiRepository.save(makinamiList);

        return makinamiList;
    }

    /**
     * 获得墙内爬虫
     * @return
     */
    private String getInternal() {

        List<MakinamiList> lists = makinamiRepository.getMakinamiListByType("internal");

        if(lists == null || lists.size() == 0){

            // 如果连墙内爬虫都没有就直接玩完吧
            return null;
        }

        int len = lists.size();

        MakinamiList rnt = lists.get(internalTime % len);
        internalTime = (internalTime + 1) % len;

        rnt.setUseTimes(rnt.getUseTimes() + 1);
        makinamiRepository.save(rnt);

        return rnt.getUrl();
    }

    /**
     * 获得墙外爬虫
     * @return
     */
    private String getExternal() {

        List<MakinamiList> lists = makinamiRepository.getMakinamiListByType("external");

        if(lists == null || lists.size() == 0){

            // 如果没有墙外爬虫就尝试获取墙内爬虫
            return getInternal();
        }

        int len = lists.size();

        MakinamiList rnt = lists.get(externalTime % len);
        externalTime = (externalTime + 1) % len;

        rnt.setUseTimes(rnt.getUseTimes() + 1);
        makinamiRepository.save(rnt);

        return rnt.getUrl();
    }

    @Override
    public List<MakinamiList> getAll() {

        List<MakinamiList> lists = makinamiRepository.findAll();

        return lists;
    }

    @Override
    public MakinamiList update(Integer id, String url) {

        MakinamiList makinamiList = makinamiRepository.findOne(id);

        makinamiList.setUrl(url);

        makinamiRepository.save(makinamiList);
        return makinamiList;
    }

    @Override
    public Boolean delete(Integer id) {

        try{
            makinamiRepository.delete(id);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public UserAccount getUserAccount(String loginname, String password, String ojCode) {
        /**
         * 怎么选择爬虫是个问题，现在默认使用墙内爬虫
         * 考虑要不要写一个爬虫选择工具 ==>根据ojCode选择墙内墙外爬虫（暂时感觉没必要）
         */
        String url = getInternal() + "/" + ojCode + "/user/" + loginname;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", password);

        /**
         * 这个方法可能会奇慢无比（与用户AC数成正相关），单线程肯定会拖垮前端界面
         */
        jsonObject = JsonSender.post(url, jsonObject);

        if(jsonObject != null && jsonObject.getBoolean("username")){
            UserAccount account = new UserAccount(ojCode, loginname, password,
                    jsonObject.getInt("accept"), jsonObject.getInt("submit"));

            return account;
        }
        return null;
    }
}

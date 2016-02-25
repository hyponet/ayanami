package xyz.acmer.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.problem.Status;
import xyz.acmer.entity.system.MakinamiList;
import xyz.acmer.entity.system.OjCode;
import xyz.acmer.entity.user.UserAccount;
import xyz.acmer.repository.system.MakinamiRepository;
import xyz.acmer.service.IMakinamiService;
import xyz.acmer.util.JsonSender;

import java.util.ArrayList;
import java.util.Iterator;
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

        if(url.endsWith("/")){
            url = url.substring(0, url.length() - 1);
        }
        MakinamiList makinamiList = new MakinamiList(url, "internal");

        makinamiRepository.save(makinamiList);

        return makinamiList;
    }

    @Override
    public MakinamiList addExternal(String url) {

        if(url.endsWith("/")){
            url = url.substring(0, url.length() - 1);
        }
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

    /**
     * 根据ojcode来判断使用墙外还是墙内爬虫
     * @param ojCode
     * @return
     */
    private String getUrl(OjCode ojCode){

        if(ojCode.getType().equals("internal")){
            return getInternal();
        }else if(ojCode.getType().equals("external")){
            return getExternal();
        }else{
            return "";
        }
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

    /**
     * 获得用户账号
     * @param loginname
     * @param password
     * @param ojCode
     * @return
     */
    @Override
    public UserAccount getUserAccount(String loginname, String password, OjCode ojCode) {

        String url = getUrl(ojCode) + "/" + ojCode.getOjCode() + "/user/" + loginname;

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

    /**
     * 获得一个OJ的所有题目
     *
     * @param ojCode
     * @return
     */
    @Override
    public List<Problem> getAllProblem(OjCode ojCode) {

        String url = getUrl(ojCode) + "/" + ojCode.getOjCode() + "/problems";

        System.out.println(url);

        JSONObject jsonObject = JsonSender.get(url);

        if(jsonObject != null){
            JSONArray array = (JSONArray) jsonObject.get("problem_list");
            Iterator iterator = array.iterator();

            List<Problem> problems = new ArrayList<Problem>();

            while (iterator.hasNext()){
                JSONObject object = (JSONObject) iterator.next();
                problems.add(new Problem(ojCode, "" + object.get("problem_id"), object.getString("title"), 0, 0));
            }

            return problems;
        }
        return null;
    }

    /**
     * 获得指定题目
     *
     * @param ojCode
     * @param pid
     * @return
     */
    @Override
    public Problem getProblem(OjCode ojCode, String pid) {

        String url = getUrl(ojCode) + "/" + ojCode.getOjCode() + "/problem/" + pid;

        JSONObject jsonObject = JsonSender.get(url);
        Problem problem = new Problem(ojCode, pid, jsonObject.getString("title"), 0, 0);

        return problem;
    }

    /**
     * 提交代码并获得状态
     *
     * @param ojCode    要提交的OJ
     * @param pid       题目ID
     * @param code      提交的代码 ！要base64加密！
     * @param language  使用的语言
     * @param loginname 登录名
     * @param password  登录密码
     * @return
     */
    @Override
    public Status submitProblem(OjCode ojCode, String pid, String code,
                                String language, String loginname, String password) {

        Status status = new Status();
        String url = getUrl(ojCode) + "/" + ojCode.getOjCode() + "/problem/" + pid;
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username", loginname);
        jsonObject.put("password", password);
        jsonObject.put("language", language);
        jsonObject.put("code", code);

        jsonObject = JsonSender.post(url, jsonObject);

        if(jsonObject != null){

            Integer statusInt = null;
            if(jsonObject.has("status")){
                statusInt = jsonObject.getInt("status");
            }

            if(statusInt == null || statusInt == 200){
                // 补全结果相关信息
                status.setResult(jsonObject.getString("result"));
                status.setLength(jsonObject.getString("code_length"));
                status.setOjRunId(jsonObject.getInt("run_id"));
                status.setLanguage(jsonObject.getString("language"));
                if(jsonObject.has("memory")){
                    status.setMemory(jsonObject.getString("memory"));
                }
                if (jsonObject.has("time")){
                    status.setTime(jsonObject.getString("time"));
                }
                /**
                 * 返回的status还缺少submiter和problem
                 * 在持久化前,还需要注入以上这两个元素（外键）
                 */
                return status;
            }
        }
        return null;
    }

    /**
     * 获得OJ中制定runid的结果
     *
     * @param ojCode
     * @param status
     * @return
     */
    @Override
    public Status updateStatus(OjCode ojCode, Status status) {

        String url = getUrl(ojCode) + "/status/" + status.getOjRunId();

        JSONObject jsonObject = JsonSender.get(url);

        if(jsonObject != null){

            Integer statusInt = null;
            if(jsonObject.has("status")){
                statusInt = jsonObject.getInt("status");
            }

            if(statusInt == null || statusInt == 200){

                // 补全结果相关信息
                status.setResult(jsonObject.getString("result"));
                status.setLength(jsonObject.getString("code_length"));
                status.setOjRunId(jsonObject.getInt("run_id"));
                status.setLanguage(jsonObject.getString("language"));
                if(jsonObject.has("memory")){
                    status.setMemory(jsonObject.getString("memory"));
                }
                if (jsonObject.has("time")){
                    status.setTime(jsonObject.getString("time"));
                }
                /**
                 * 返回的status还缺少submiter和problem
                 * 在持久化前,还需要注入以上这两个元素（外键）
                 */
                return status;
            }
        }
        return null;
    }
}

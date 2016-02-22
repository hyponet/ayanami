package xyz.acmer.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.user.User;
import xyz.acmer.entity.user.UserAccount;
import xyz.acmer.entity.user.UserInfo;
import xyz.acmer.repository.user.UserAccountRepository;
import xyz.acmer.repository.user.UserInfoRepository;
import xyz.acmer.repository.user.UserRepository;
import xyz.acmer.service.IMakinamiService;
import xyz.acmer.service.IUserService;
import xyz.acmer.util.EncryptHelper;
import xyz.acmer.util.JsonSender;
import xyz.acmer.util.StringHelper;

import java.util.Date;
import java.util.Map;

/**
 * Created by hypo on 16-2-19.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Boolean login(String email, String password){

        if(password == null || email == null || password.length() == 0){
            return false;
        }

        User user = userRepository.getUserByEmail(email);

        if (user == null || user.getPassword() == null){
            return false;
        }

        password = EncryptHelper.getPassword(password);

        return password.equals(user.getPassword());
    }

    public User addNewUser(String username, String nickname, String email, String password){

        if(userRepository.getUserByEmail(email) == null && userRepository.getUserByUsername(username) == null){

            User newUser = new User(username, nickname, email, EncryptHelper.getPassword(password));
            userRepository.save(newUser);

            UserInfo userInfo = new UserInfo(newUser);

            userInfoRepository.save(userInfo);

            return newUser;
        }

        return null;
    }

    @Autowired
    private IMakinamiService makinamiService;

    /**
     * 这样做应该会拖累前台页面，最好用多线程，但是思路还不是很清晰
     * @param loginname
     * @param password
     * @param ojCode
     * @param user
     * @return
     */
    @Override
    public Boolean addUserAccount(String loginname, String password, String ojCode, User user) {

        /**
         * 怎么选择爬虫是个问题，现在默认使用墙内爬虫
         * 考虑要不要写一个爬虫选择工具 ==>根据ojCode选择墙内墙外爬虫（暂时感觉没必要）
         */
        String url = makinamiService.getInternal() + "/" + ojCode + "/user/" + loginname;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", password);

        jsonObject = JsonSender.post(url, jsonObject);

        if(jsonObject != null && jsonObject.getBoolean("username")){
            UserAccount account = new UserAccount(user, ojCode, loginname, password,
                    jsonObject.getInt("accept"), jsonObject.getInt("submit"));

            userAccountRepository.save(account);

            return true;
        }
        return false;
    }

    @Override
    public Boolean updateUserAccount(String loginname, String password, String ojCode, User user) {

        /**
         * 怎么选择爬虫是个问题，现在默认使用墙内爬虫
         * 考虑要不要写一个爬虫选择工具 ==>根据ojCode选择墙内墙外爬虫（暂时感觉没必要）
         */
        String url = makinamiService.getInternal() + "/" + ojCode + "/user/" + loginname;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", password);

        jsonObject = JsonSender.post(url, jsonObject);

        if(jsonObject != null && jsonObject.getBoolean("username")){
            UserAccount account = userAccountRepository.getAcciuntByUserAndOj(user, ojCode);

            account.setLoginName(loginname);
            account.setPassword(password);
            account.setAccepted(jsonObject.getInt("accept"));
            account.setSubmit(jsonObject.getInt("submit"));
            userAccountRepository.save(account);

            return true;
        }
        return false;
    }

    /**
     * 更新用户资料，用户名，邮箱不允许更改
     * @param map 对map的key要求很严格
     * @param user
     * @return
     */
    @Override
    public Boolean updateUserInfo(Map<String, String> map, User user) {

        if(map.containsKey("nickname")){

            String nickName = map.get("nickname");

            if (nickName.length() > 30){
                nickName = nickName.substring(0, 30);
            }
            user.setNickName(nickName.trim());
        }

        if(map.containsKey("password")){
            String password = map.get("password");

            if(password != null && password.length() > 3){
                user.setPassword(EncryptHelper.getPassword(password));
            }
        }

        userRepository.save(user);

        UserInfo userInfo = userInfoRepository.getUserInfoByUser(user);
        if(userInfo == null){
            return false;
        }

        /**
         * map["birthday"] 的格式为 yyyy-MM-dd
         */
        if(map.containsKey("birthday")){

            userInfo.setBirthday(StringHelper.stringToDate(map.get("birthday")));
        }

        // 个人主页
        if(map.containsKey("webSite")){

            String webSite = map.get("webSite");

            if(webSite.length() > 50){
                webSite = webSite.substring(0, 50);
            }
            userInfo.setWebSite(webSite.trim());
        }

        // 公司
        if(map.containsKey("company")){

            String company = map.get("company");

            if(company.length() > 60){
                company = company.substring(0, 60);
            }
            userInfo.setCompany(company.trim());
        }

        // 学校
        if(map.containsKey("school")){

            String school = map.get("school");

            if (school.length() > 60){
                school = school.substring(0, 60);
            }
            userInfo.setSchool(school.trim());
        }

        // 个性签名
        if(map.containsKey("tagLine")){

            String tagLine = map.get("tagLine");

            if (tagLine.length() > 255){
                tagLine = tagLine.substring(0, 255);
            }
            userInfo.setTagLine(tagLine.trim());
        }

        if(map.containsKey("github")){

            String github = map.get("github");

            if(github.length() > 50){
                github = github.substring(0, 50);
            }
            userInfo.setGithub(github.trim());
        }

        if(map.containsKey("psnId")){

            String psnId = map.get("psnId");

            if(psnId.length() > 50){
                psnId = psnId.substring(0, 50);
            }
            userInfo.setPsnId(psnId.trim());
        }

        if(map.containsKey("steamId")){

            String steamId = map.get("steamId");

            if(steamId.length() > 50){
                steamId = steamId.substring(0, 50);
            }
            userInfo.setSteamId(steamId.trim());
        }

        if(map.containsKey("telegram")){

            String telegram = map.get("telegram");

            if(telegram.length() > 50){
                telegram = telegram.substring(0, 50);
            }
            userInfo.setTelegram(telegram.trim());
        }

        if(map.containsKey("twitter")){

            String twitter = map.get("twitter");

            if(twitter.length() > 50){
                twitter = twitter.substring(0, 50);
            }
            userInfo.setTwitter(twitter.trim());
        }

        if(map.containsKey("codingNet")){

            String codingNet = map.get("codingNet");

            if(codingNet.length() > 50){
                codingNet = codingNet.substring(0, 50);
            }
            userInfo.setCodingNet(codingNet.trim());
        }

        // 个人简介
        if(map.containsKey("info")){

            String info = map.get("info");

            if(info.length() > 500){
                info = info.substring(0, 500);
            }
            userInfo.setInfo(info.trim());
        }

        // 提供feed订阅的url
        if(map.containsKey("feedUrl")){

            String feedUrl = map.get("feedUrl");

            if(feedUrl.length() > 100){
                feedUrl = feedUrl.substring(0, 100);
            }
            userInfo.setFeedUrl(feedUrl.trim());
        }

        userInfoRepository.save(userInfo);
        return true;
    }

}

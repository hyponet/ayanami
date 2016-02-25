package xyz.acmer.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.system.OjCode;
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
    public Boolean addUserAccount(String loginname, String password, OjCode ojCode, User user) {

        /*
         这个方法返回的account没有注入user信息
         */
        UserAccount account = makinamiService.getUserAccount(loginname, password, ojCode);

        if(account != null){
            /*
             注入user
             */
            account.setUser(user);
            userAccountRepository.save(account);

            return true;
        }

        return false;
    }

    @Override
    public Boolean updateUserAccount(String loginname, String password, OjCode ojCode, User user) {

        UserAccount account = makinamiService.getUserAccount(loginname, password, ojCode);

        if(account != null){
            int accept = account.getAccepted();
            int submit = account.getSubmit();
            account = userAccountRepository.getAcciuntByUserAndOj(user, ojCode);
            account.setLoginName(loginname);
            account.setPassword(password);
            account.setAccepted(accept);
            account.setSubmit(submit);
            userAccountRepository.save(account);

            return true;
        }
        return false;
    }

    /**
     * 更新用户资料，用户名，邮箱不允许更改
     * @param map 对map的key要求很严格,否则找不到指定项
     * @param user
     * @return
     */
    @Override
    public Boolean updateUserInfo(Map<String, String> map, User user) {

        if(map.containsKey("nickname")){

            String nickName = map.get("nickname");
            user.setNickName(StringHelper.getSafeString(nickName, 30));
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
            userInfo.setWebSite(StringHelper.getSafeString(webSite, 50));
        }

        // 公司
        if(map.containsKey("company")){

            String company = map.get("company");
            userInfo.setCompany(StringHelper.getSafeString(company, 60));
        }

        // 学校
        if(map.containsKey("school")){

            String school = map.get("school");
            userInfo.setSchool(StringHelper.getSafeString(school, 60));
        }

        // 个性签名
        if(map.containsKey("tagLine")){

            String tagLine = map.get("tagLine");
            userInfo.setTagLine(StringHelper.getSafeString(tagLine, 255));
        }

        if(map.containsKey("github")){

            String github = map.get("github");
            userInfo.setGithub(StringHelper.getSafeString(github, 50));
        }

        if(map.containsKey("psnId")){

            String psnId = map.get("psnId");
            userInfo.setPsnId(StringHelper.getSafeString(psnId, 50));
        }

        if(map.containsKey("steamId")){

            String steamId = map.get("steamId");
            userInfo.setSteamId(StringHelper.getSafeString(steamId, 50));
        }

        if(map.containsKey("telegram")){

            String telegram = map.get("telegram");
            userInfo.setTelegram(StringHelper.getSafeString(telegram, 50));
        }

        if(map.containsKey("twitter")){

            String twitter = map.get("twitter");
            userInfo.setTwitter(StringHelper.getSafeString(twitter, 50));
        }

        if(map.containsKey("codingNet")){

            String codingNet = map.get("codingNet");
            userInfo.setCodingNet(StringHelper.getSafeString(codingNet, 50));
        }

        // 个人简介
        if(map.containsKey("info")){

            String info = map.get("info");
            userInfo.setInfo(StringHelper.getSafeString(info, 500));
        }

        // 提供feed订阅的url
        if(map.containsKey("feedUrl")){

            String feedUrl = map.get("feedUrl");
            userInfo.setFeedUrl(StringHelper.getSafeString(feedUrl, 100));
        }

        userInfoRepository.save(userInfo);
        return true;
    }

}

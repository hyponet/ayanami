package xyz.acmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.system.MakinamiList;
import xyz.acmer.repository.system.MakinamiRepository;
import xyz.acmer.service.IMakinamiService;

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
    @Override
    public String getInternal() {

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
    @Override
    public String getExternal() {

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
}

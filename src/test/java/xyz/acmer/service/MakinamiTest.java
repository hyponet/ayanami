package xyz.acmer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.system.OjCode;

import java.util.List;

/**
 * Created by hypo on 16-2-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class MakinamiTest {

    @Autowired
    private IMakinamiService makinamiService;

    @Test
    public void addInternal(){

        makinamiService.addInternal("http://127.0.0.1:5000");

    }

    @Test
    public void addExternal(){

        makinamiService.addExternal("http://127.0.0.1:5000");
    }

    @Test
    public void getProblem(){
        Problem problem = makinamiService.getProblem(new OjCode("poj", "poj", "poj.org", "internal"), 1000 + "");

        System.out.println(problem.getTitle());
    }

    @Test
    public void getAllProblem(){
        List<Problem> list = makinamiService.getAllProblem(new OjCode("poj", "poj", "poj.org", "internal"));

        for(Problem problem : list){
            System.out.println(problem.getOjCode().getOjName() + " " + problem.getPid() + " = " + problem.getTitle());
        }
    }

    @Test
    public void submitProblem(){
        makinamiService.submitProblem(new OjCode("poj", "poj", "poj.org", "internal"), "" + 1000
                , "I2luY2x1ZGUgPHN0ZGlvLmg+CgppbnQgbWFpbigpCnsKICAgIGludCBhLGI7CiAgICBzY2FuZigiJ" +
                        "WQgJWQiLCZhLCAmYik7CiAgICBwcmludGYoIiVkXG4iLGErYik7CiAgICByZXR1cm4gMDsKfQ==",
                "gcc", "sdutacm1", "sdutacm");

    }

}

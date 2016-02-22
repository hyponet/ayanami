package xyz.acmer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
    public void getInternal(){

        System.out.println(makinamiService.getInternal());
    }

    @Test
    public void getExternal(){

        System.out.println(makinamiService.getExternal());
    }
}

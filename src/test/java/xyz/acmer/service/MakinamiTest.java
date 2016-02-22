package xyz.acmer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.repository.system.MakinamiRepository;
import xyz.acmer.service.IMakinamiService;

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

    }

    @Test
    public void addExternal(){

    }

    @Test
    public void getInternal(){

    }

    @Test
    public void getExternal(){

    }
}

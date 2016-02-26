package xyz.acmer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.entity.system.OjCode;
import xyz.acmer.repository.system.OjCodeRepository;

/**
 * Created by hypo on 16-2-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class SystemTest {

    @Autowired
    private OjCodeRepository ojCodeRepository;

    @Test
    public void addOjCode(){
        OjCode ojCode = new OjCode("poj", "poj", "poj.org", "internal");

        ojCodeRepository.save(ojCode);
    }

    @Test
    public void getOjCode(){
        OjCode ojCode = ojCodeRepository.getOjCodeByName("poj");

        System.out.println(ojCode.getId());
    }
}

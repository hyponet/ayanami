package xyz.acmer.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.system.OjCode;
import xyz.acmer.repository.problem.ProblemRepository;
import xyz.acmer.repository.system.OjCodeRepository;

/**
 * Created by hypo on 16-2-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class ProblemDaoTest {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private OjCodeRepository ojCodeRepository;

    private OjCode ojCode;

    @Before
    public void getOjCcode(){
        this.ojCode = ojCodeRepository.getOjCodeByName("poj");
    }

    @Test
    public void getProblemByPid(){
        Problem problem = problemRepository.getProblemByOjCodeAndPid(ojCode, "1001");

        System.out.println(problem.getTitle());
    }
}

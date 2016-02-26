package xyz.acmer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.system.OjCode;
import xyz.acmer.repository.system.OjCodeRepository;

/**
 * Created by hypo on 16-2-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class ProblemServiceTest {

    @Autowired
    private IProblemService problemService;

    @Autowired
    private OjCodeRepository ojCodeRepository;

    private OjCode ojCode;
    @Before
    public void getOjCode(){
        this.ojCode = ojCodeRepository.getOjCodeByName("poj");
    }

    @Test
    public void getAllProblem(){
        problemService.getAllProblem(ojCode);
    }

    @Test
    public void updateAllProblem(){
        problemService.updateAllProblem(ojCode);
    }

    @Test
    public void getProblem(){
        Problem problem = problemService.getProblem(ojCode, "2400");

        System.out.println(problem.getProblemId() + "---" + problem.getTitle());
    }
}

package xyz.acmer.repository.problem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.system.OjCode;

/**
 * Created by hypo on 16-2-21.
 */
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query("SELECT p FROM Problem p WHERE p.ojCode=:ojCode AND p.pid=:pid")
    Problem getProblemByOjCodeAndPid(@Param("ojCode")OjCode ojCode, @Param("pid")String pid);
}

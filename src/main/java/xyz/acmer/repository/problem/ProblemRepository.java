package xyz.acmer.repository.problem;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.acmer.entity.problem.Problem;

/**
 * Created by hypo on 16-2-21.
 */
public interface ProblemRepository extends JpaRepository<Problem, Long> {
}

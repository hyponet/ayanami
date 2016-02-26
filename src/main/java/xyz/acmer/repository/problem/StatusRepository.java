package xyz.acmer.repository.problem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.contest.ContestInfo;
import xyz.acmer.entity.problem.Problem;
import xyz.acmer.entity.problem.Status;

import java.util.List;

/**
 * 状态（Status）DAO
 * Created by hypo on 16-2-26.
 */
@Repository
@Transactional
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query("SELECT s FROM Status s WHERE s.contestInfo=:contest AND s.problem=:problem")
    List<Status> getStatusByContestInfoAndProblem(@Param("contest")ContestInfo contest, @Param("problem")Problem problem);
}

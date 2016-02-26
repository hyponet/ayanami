package xyz.acmer.repository.problem;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.acmer.entity.problem.Status;

/**
 * 状态（Status）DAO
 * Created by hypo on 16-2-26.
 */
public interface StatusRepository extends JpaRepository<Status, Long> {
}

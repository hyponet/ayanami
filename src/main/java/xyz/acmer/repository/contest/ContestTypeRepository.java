package xyz.acmer.repository.contest;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.acmer.entity.contest.ContestType;

/**
 * 比赛类型DAO
 * Created by hypo on 16-2-26.
 */
public interface ContestTypeRepository extends JpaRepository<ContestType, Integer> {
}

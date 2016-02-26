package xyz.acmer.repository.contest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.contest.ContestType;

/**
 * 比赛类型DAO
 * Created by hypo on 16-2-26.
 */
@Repository
@Transactional
public interface ContestTypeRepository extends JpaRepository<ContestType, Integer> {
}

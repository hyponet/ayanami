package xyz.acmer.repository.contest;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.acmer.entity.contest.ContestAnnouncement;

/**
 * 比赛通知DAO
 * Created by hypo on 16-2-26.
 */
public interface ContestAnnouncementRepository extends JpaRepository<ContestAnnouncement, Long> {
}

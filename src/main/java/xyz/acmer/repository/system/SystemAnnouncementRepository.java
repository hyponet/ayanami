package xyz.acmer.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.system.SystemAnnouncement;

/**
 * 系统公告DAO
 * Created by hypo on 16-2-24.
 */
@Repository
@Transactional
public interface SystemAnnouncementRepository extends JpaRepository<SystemAnnouncement, Long>{
}

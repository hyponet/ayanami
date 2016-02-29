package xyz.acmer.repository.contest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.contest.ContestInfo;
import xyz.acmer.entity.contest.ContestType;

import java.util.Date;
import java.util.List;

/**
 * 比赛信息DAO
 * Created by hypo on 16-2-26.
 */
@Repository
@Transactional
public interface ContestInfoRepository extends JpaRepository<ContestInfo, Long> {

    @Query("SELECT c FROM ContestInfo c ORDER BY c.beginTime DESC")
    List<ContestInfo> getAllContestByBeginTime();

    @Query("SELECT c FROM ContestInfo c WHERE c.contestType=:contestType ORDER BY c.beginTime DESC")
    List<ContestInfo> getContestByContestType(@Param("contestType")ContestType contestType);

    @Query("SELECT c FROM ContestInfo c WHERE c.beginTime>:date ORDER BY c.beginTime DESC")
    List<ContestInfo> getContestScheduled(@Param("date")Date now);

    @Query("SELECT c FROM ContestInfo c WHERE c.endTime>:date AND c.beginTime<:date ORDER BY c.beginTime DESC")
    List<ContestInfo> getContestRunning(@Param("date")Date now);

    @Query("SELECT c FROM ContestInfo c WHERE c.endTime<:date ORDER BY c.beginTime DESC")
    List<ContestInfo> getContestEnded(@Param("date")Date now);
}

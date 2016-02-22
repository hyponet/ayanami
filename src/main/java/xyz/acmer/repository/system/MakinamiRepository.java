package xyz.acmer.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.system.MakinamiList;

import java.util.List;

/**
 * 获得提供微服务的Makinami的地址
 * Created by hypo on 16-2-22.
 */
@Repository
@Transactional
public interface MakinamiRepository extends JpaRepository<MakinamiList, Integer>{

    @Query("SELECT m FROM MakinamiList m WHERE m.type=:typestring")
    List<MakinamiList> getMakinamiListByType(@Param("typestring")String type);
}

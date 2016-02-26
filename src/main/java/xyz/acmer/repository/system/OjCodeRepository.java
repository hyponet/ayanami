package xyz.acmer.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.system.OjCode;

/**
 * Created by hypo on 16-2-27.
 */
@Repository
@Transactional
public interface OjCodeRepository extends JpaRepository<OjCode, Integer> {

    @Query("SELECT o FROM OjCode o WHERE o.ojName=:name")
    OjCode getOjCodeByName(@Param("name")String name);
}

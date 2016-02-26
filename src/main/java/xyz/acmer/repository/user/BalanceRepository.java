package xyz.acmer.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.entity.user.Balance;
import xyz.acmer.entity.user.User;

import java.util.List;

/**
 * Created by hypo on 16-2-26.
 */
@Repository
@Transactional
public interface BalanceRepository extends JpaRepository<Balance, Long>{

    @Query("SELECT b FROM Balance b WHERE b.user=:user")
    List<Balance> getBalanceByUser(@Param("user")User user);
}

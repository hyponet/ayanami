package xyz.acmer.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.entity.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by hypo on 16-2-8.
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User get(Long id) {

        User user = entityManager.find(User.class, id);

        return user;
    }

    @Override
    public Boolean update(User user) {
        entityManager.merge(user);
        return true;
    }

    @Override
    public Boolean save(User user) {
        entityManager.persist(user);
        return true;
    }

    @Override
    public Boolean delete(User user) {
        entityManager.remove(user);
        return true;
    }

    @Override
    public User getUserByUsername(String username) {

        String HQL = "SELECT u FROM User u WHERE u.userName = ?1";
        Query query = entityManager.createQuery(HQL);
        query.setParameter(1, username);
        User user = (User) query.getSingleResult();

        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        String HQL = "SELECT u FROM User u WHERE u.email = ?1";
        Query query = entityManager.createQuery(HQL);
        query.setParameter(1, email);
        User user = (User) query.getSingleResult();

        return user;
    }

    @Override
    public List<User> getAllUser() {

        String HQL = "SELECT u FROM User u ORDER BY u.balance DESC";
        Query query = entityManager.createQuery(HQL);

        List<User> users = query.getResultList();
        return users;
    }
}

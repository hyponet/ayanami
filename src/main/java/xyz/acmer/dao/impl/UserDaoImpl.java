package xyz.acmer.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.entity.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by hypo on 16-2-8.
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User get(Integer id) {

        return entityManager.find(User.class, id);
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
        return null;
    }

    @Override
    public User getUserByUsername(String username) {

        final String GET_USER_BY_USERNAME =
                "SELECT u FROM User u WHERE u.user_name = " + username;

        Query query = entityManager.createQuery(GET_USER_BY_USERNAME);
        User user = (User) query.getSingleResult();

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        final String GET_USER_BY_USERNAME =
                "SELECT u FROM User u WHERE u.email = " + email;

        Query query = entityManager.createQuery(GET_USER_BY_USERNAME);
        User user = (User) query.getSingleResult();

        return user;
    }
}

package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import web.models.User;

import java.util.List;

@Repository
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> index() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    @Transactional
    public User show(int id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updatedUser.getName());
        entityManager.merge(userToBeUpdated);
    }
    @Transactional
    public void delete(int id) {
        User user = show(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
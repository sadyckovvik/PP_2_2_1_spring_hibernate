package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked") //подавление предупреждения, возникающего при создании TypedQuery<User>
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUser(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u from User u JOIN FETCH u.userCar c WHERE c.model = :model AND c.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }

   @Override
   public <T> void addList(List<T> entities) {
      Session session = sessionFactory.getCurrentSession();
      for (T entity : entities) {
         session.save(entity);
      }
   }

   @Override
   public <T> List<T> getList(Class<T> entityClass) {
      return sessionFactory.getCurrentSession()
              .createQuery("FROM " + entityClass.getSimpleName(), entityClass)
              .getResultList();
   }

   @Override
   public <T> void updateList(List<T> entities) {
      Session session = sessionFactory.getCurrentSession();
      for (T entity : entities) {
         session.update(entity);
      }
   }
}

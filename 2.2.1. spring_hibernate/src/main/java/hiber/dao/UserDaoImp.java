package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
   public void addListUser(List<User> userList) {
      Session session = sessionFactory.getCurrentSession();
      for (User user : userList) {
         session.save(user);
      }
   }

   @Override
   public void updateList(List<User> userList) {
      Session session = sessionFactory.getCurrentSession();
      for (User user : userList) {
         session.update(user);
      }
   }
}

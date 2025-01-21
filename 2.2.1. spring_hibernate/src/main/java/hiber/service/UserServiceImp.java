package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional //позволяет Spring автоматически фиксировать или откатывать транзакцию
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public List<User> getUser(String model, int series) {
      return userDao.getUser(model,series);
   }

   @Transactional
   @Override
   public <T> void addList(List<T> entities) {
      userDao.addList(entities);
   }

   @Transactional (readOnly = true)
   @Override
   public <T> List<T> getList(Class<T> entityClass) {
      return userDao.getList(entityClass);
   }

   @Transactional
   @Override
   public <T> void updateList(List<T> entities) {
      userDao.updateList(entities);
   }
}

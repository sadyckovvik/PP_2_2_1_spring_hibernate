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

   @Transactional(readOnly = true) //метод должен выполняться в транзакции, доступной только для чтения
   // не будет выполняться никаких операций записи (INSERT, UPDATE, DELETE)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getUser(String model, int series) {
      return userDao.getUser(model,series);
   }

}

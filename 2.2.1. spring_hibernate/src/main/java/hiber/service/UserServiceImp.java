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

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }


   @Transactional(readOnly = true)     // readOnly: Указывает, что транзакция предназначена только для чтения
   @Override
   public User getUserByCar(String model, int series) {
      User user = userDao.getUserByCar(model, series);
      if (user==null){
         System.out.printf("Не найден пользователь с именем - model %s, series %s",model,series);
         try {
            throw new Exception();
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
      return user;
   }
}

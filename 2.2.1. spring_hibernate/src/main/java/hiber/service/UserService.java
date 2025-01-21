package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    List<User> getUser(String model, int series);
    <T> void addList(List<T> entities);
    <T> List<T> getList(Class<T> entityClass);
    <T> void updateList(List<T> entities);
}

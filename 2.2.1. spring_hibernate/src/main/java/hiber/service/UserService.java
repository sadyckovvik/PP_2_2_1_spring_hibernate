package hiber.service;

import hiber.model.User;
import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    List<User> getUser(String model, int series);
    void addListUser(List <User> userList);
    void updateList(List<User> userList);
}

package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        List<User> users = new ArrayList<>(Arrays.asList(
                new User("User1", "Lastname1", "user1@mail.ru"),
                new User("User2", "Lastname2", "user2@mail.ru"),
                new User("User3", "Lastname3", "user3@mail.ru"),
                new User("User4", "Lastname4", "user4@mail.ru")));

        List<Car> cars = new ArrayList<>(Arrays.asList(
                new Car("BMW", 5),
                new Car("Kia", 3),
                new Car("Volvo", 2),
                new Car("Toyota", 4)));

        try {
            userService.addListUser(users);
            carService.addListCar(cars);

            List<User> userList = userService.listUsers();
            List<Car> carList = carService.listCar();
            int count = 0;
            for (User user : userList) {
                if(count < carList.size()) {
                    user.setUserCar(carList.get(count++));
                }
            }

            userService.updateList(userList);

//         for (User user : users) {
//            System.out.println("Id = " + user.getId());
//            System.out.println("First Name = " + user.getFirstName());
//            System.out.println("Last Name = " + user.getLastName());
//            System.out.println("Email = " + user.getEmail());
//            System.out.println("Car = " + user.getUserCar());
//            System.out.println();
//         }
//
//         List<User> usersList = userService.getUser("BMW", 5);
//         for (User user : userList) {
//            System.out.println("Id = " + user.getId());
//            System.out.println("First Name = " + user.getFirstName());
//            System.out.println("Last Name = " + user.getLastName());
//            System.out.println("Email = " + user.getEmail());
//         }
        } finally {
            context.close();
        }
    }
}

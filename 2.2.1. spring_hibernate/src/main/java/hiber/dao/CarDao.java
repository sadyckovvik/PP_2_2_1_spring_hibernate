package hiber.dao;

import hiber.model.Car;
import java.util.List;

public interface CarDao {
    void addListCar(List <Car> carList);
    List<Car> listCar();
}

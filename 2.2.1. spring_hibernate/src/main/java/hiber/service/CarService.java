package hiber.service;

import hiber.model.Car;
import java.util.List;

public interface CarService {
    void addListCar(List<Car> carList);
    List<Car> listCar();
}

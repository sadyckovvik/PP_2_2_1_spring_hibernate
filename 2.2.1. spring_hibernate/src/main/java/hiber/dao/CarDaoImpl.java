package hiber.dao;

import hiber.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> listCar() {
        return sessionFactory.getCurrentSession().createQuery("FROM Car", Car.class).getResultList();
    }

    @Override
    public void addListCar(List<Car> carList) {
        Session session = sessionFactory.getCurrentSession();
        for (Car car : carList) {
            session.save(car);
        }
    }
}

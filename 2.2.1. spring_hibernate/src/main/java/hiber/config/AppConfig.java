package hiber.config;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration //конфигурационный класс Spring
@PropertySource("classpath:db.properties") //указание Spring загрузить свойства из файла
@EnableTransactionManagement //поддержка управления транзакциями в Spring. Позволяет использовать аннотацию @Transactional для управления транзакциями
@ComponentScan(value = "hiber") //указание Spring сканировать пакет и его подпакеты на наличие @Component, @Service, @Repository, @Controller
public class AppConfig {

   @Autowired
   private Environment env; //Свойства становятся доступны через методы интерфейса Environment, такие как getProperty()

   @Bean //бин типа DataSource
   public DataSource getDataSource() { //подключение к базе данных
      DriverManagerDataSource dataSource = new DriverManagerDataSource(); //создания простого DataSource, который управляет подключениями к базе данных
      dataSource.setDriverClassName(env.getProperty("db.driver")); //получение свойств из db.propertis и установка их
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalSessionFactoryBean getSessionFactory() { //получение настроек SessionFactory для Hibernate
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean(); //настройка объекта для работы с Hibernate
      factoryBean.setDataSource(getDataSource()); //устаналивает подключение к базе данных
      
      Properties props=new Properties(); //хранение настроек хайбирнейт
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql")); // добавление свойства из настроек для работы с хайбирнейт
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto")); // добавление свойства из настроек для работы с хайбирнейт

      factoryBean.setHibernateProperties(props); // установка свойст хайбирнейт для SessionFactory
      factoryBean.setAnnotatedClasses(User.class, Car.class); //Это классы, которые Hibernate будет маппить на таблицы в базе данных
      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() { //получение объекта для управления транзакциями в Hibernate
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject()); // настройка SessionFactory для transactionManager
      return transactionManager;
   }
}

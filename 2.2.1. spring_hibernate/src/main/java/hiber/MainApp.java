package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Иван", "Васильевич", "VasianIV@mail.ru"));
      userService.add(new User("Александр", "Павлович", "SaniaI@mail.ru"));
      userService.add(new User("Петр", "Алексеевич", "PetrI@mail.ru"));
      userService.add(new User("Николай", "Павлович", "KolianI@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      Car car1 = new Car("Шестерка", 1);
      Car car2 = new Car("Семерка", 2);
      Car car3 = new Car("Пятерка", 3);
      Car car4 = new Car("Девятка", 4);


      System.out.println("\n=== Добавление пользователей с машинами в БД");
      userService.add(new User("Александр", "Николаевич", "KolianII@mail.ru", car1));
      userService.add(new User("Александр", "Александрович", "SaniaIII@mail.ru", car2));
      userService.add(new User("Федор", "Иванович", "FediaI@mail.ru", car3));
      userService.add(new User("Иван", "Васильевич", "VaniaIV@mail.ru", car4));
      System.out.println("\n=== press F to pay respects");

      System.out.println("\n=== Select пользователя с машиной");
      User user = userService.getByCarModelAndCarSeries("Пятерка", 3);
      if (user == null) {
         System.out.println("Пользователь с таким автомобилем не найден");
      } else {
         System.out.println(user);
      }
      System.out.println("\n=== press F to pay respects");

      context.close();
   }
}


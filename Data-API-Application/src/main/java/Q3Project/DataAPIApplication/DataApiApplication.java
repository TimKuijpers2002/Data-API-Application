package Q3Project.DataAPIApplication;

import Q3Project.DataAPIApplication.Controller.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataApiApplication.class, args);
	}

}

package insa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	/*
	Pour lancer la base : java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:tpBDD --dbname.0 tpBDD
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

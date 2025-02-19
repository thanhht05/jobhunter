package vn.thanh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class JonhunterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JonhunterApplication.class, args);
	}

}

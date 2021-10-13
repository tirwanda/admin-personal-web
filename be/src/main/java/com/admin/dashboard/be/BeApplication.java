package com.admin.dashboard.be;

import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.saveUser(new User(
					"Edho Dwi Tirwanda",
					"tirwanda",
					"password",
					"edhodwitirwanda@gmail.com",
					new ArrayList<>()));

//			userService.saveUser(new User(
//					null,
//					"admin",
//					"admin",
//					"password",
//					"admin@gmail.com",
//					new ArrayList<>()));

			userService.addRoleToUser("tirwanda", "ROLE_USER");
//			userService.addRoleToUser("admin", "ROLE_ADMIN");
		};
	}
}
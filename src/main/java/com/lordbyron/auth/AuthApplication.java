package com.lordbyron.auth;

import com.lordbyron.auth.model.Employee;
import com.lordbyron.auth.model.Role;
import com.lordbyron.auth.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthApplication {
	@CrossOrigin("*")
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_USER"));
//			userService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//			userService.saveUser(new Employee(null, "Johson Rodriguez", "jrodriguez", "asdf1234", new ArrayList<>(),true));
//			userService.saveUser(new Employee(null, "Luis Flores", "lflores", "asdf1234", new ArrayList<>(),true));
//
//			userService.addRoleToUser("jrodriguez", "ROLE_ADMIN");
//			userService.addRoleToUser("jrodriguez", "ROLE_USER");
//			userService.addRoleToUser("lflores", "ROLE_USER");
//		};
//	}

}

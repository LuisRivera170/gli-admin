package com.applab.gli;

import com.applab.gli.domain.Admin;
import com.applab.gli.domain.Area;
import com.applab.gli.domain.Role;
import com.applab.gli.domain.User;
import com.applab.gli.repository.AdminRepository;
import com.applab.gli.repository.AreaRepository;
import com.applab.gli.repository.RoleRepository;
import com.applab.gli.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

import static com.applab.gli.enumeration.Status.ACTIVE;
import static com.applab.gli.enumeration.Status.INACTIVE;
import static java.time.LocalDateTime.now;

@SpringBootApplication
public class GliApplication {

	public static void main(String[] args) {
		SpringApplication.run(GliApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AreaRepository areaRepository, AdminRepository adminRepository, RoleRepository roleRepository, UserService userService) {
		return args -> {
			// Areas
			final Area direccion = areaRepository.save(new Area("Dirección"));
			final Area recursosHumanos = areaRepository.save(new Area("Recursos Humanos"));
			final Area finanzas = areaRepository.save(new Area("Finanzas"));
			final Area marketing = areaRepository.save(new Area("Marketing"));
			final Area diseño = areaRepository.save(new Area("Diseño"));

			// Admins
			adminRepository.save(new Admin("Gustavo", "Korsgaard", "gustavo.korsgaard@gli.com", ACTIVE, direccion, now()));
			adminRepository.save(new Admin("Maria", "Dias", "maria.dias@gli.com", INACTIVE, recursosHumanos, now()));
			adminRepository.save(new Admin("Maren", "Stanton", "maren.stanton@gli.com", ACTIVE, finanzas, now()));
			adminRepository.save(new Admin("Paityn", "Ekstrom", "paityn.ekstrom@gli.com", INACTIVE, finanzas, now()));
			adminRepository.save(new Admin("Kierra", "Bergston", "kierra.bergston@gli.com", INACTIVE, marketing, now()));

			// Roles
			final Role superAdminRole = roleRepository.save(new Role("ROLE_SUPER_ADMIN", ACTIVE));
			final Role adminRole = roleRepository.save(new Role("ROLE_ADMIN", ACTIVE));
		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

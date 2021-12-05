package com.applab.gli;

import com.applab.gli.domain.Admin;
import com.applab.gli.domain.Area;
import com.applab.gli.enumeration.Status;
import com.applab.gli.repository.AdminRepository;
import com.applab.gli.repository.AreaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

import static com.applab.gli.enumeration.Status.*;
import static java.time.LocalDateTime.*;

@SpringBootApplication
public class GliApplication {

	public static void main(String[] args) {
		SpringApplication.run(GliApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AreaRepository areaRepository, AdminRepository adminRepository) {
		return args -> {
			// Areas
			final Area dirección = areaRepository.save(new Area("Dirección"));
			final Area recursosHumanos = areaRepository.save(new Area("Recursos Humanos"));
			final Area finanzas = areaRepository.save(new Area("Finanzas"));
			final Area marketing = areaRepository.save(new Area("Marketing"));
			final Area diseño = areaRepository.save(new Area("Diseño"));

			// Admins
			adminRepository.save(new Admin("Gustavo", "Korsgaard", "gustavo.korsgaard@gli.com", ACTIVE, dirección, now()));
			adminRepository.save(new Admin("Maria", "Dias", "maria.dias@gli.com", INACTIVE, recursosHumanos, now()));
			adminRepository.save(new Admin("Maren", "Stanton", "maren.stanton@gli.com", ACTIVE, finanzas, now()));
			adminRepository.save(new Admin("Paityn", "Ekstrom", "paityn.ekstrom@gli.com", INACTIVE, finanzas, now()));
			adminRepository.save(new Admin("Kierra", "Bergston", "kierra.bergston@gli.com", INACTIVE, marketing, now()));
		};
	}

}

package syksy25.card;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import syksy25.card.domain.Address;
import syksy25.card.domain.AddressRepository;
import syksy25.card.domain.Category;
import syksy25.card.domain.CategoryRepository;
import syksy25.card.domain.AppUser;
import syksy25.card.domain.AppUserRepository;

@SpringBootApplication
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner initDatabase(
			AddressRepository addressRepository,
			CategoryRepository categoryRepository,
			AppUserRepository appUserRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			Category family = categoryRepository.save(new Category("Family"));
			Category friends = categoryRepository.save(new Category("Friends"));
			Category others = categoryRepository.save(new Category("Others"));

			addressRepository.save(new Address(
				"Eetu",
				"Hellberg",
				"Osoite 1",
				"01700",
				"Vantaa",
				"Finland",
				family
			));

			addressRepository.save(new Address(
				"Zakaria",
				"Lamri",
				"Street 2",
				"10100",
				"A big city",
				"Netherlands",
				friends
			));

			addressRepository.save(new Address(
				"Joku",
				"Random",
				"Randomkuja 1",
				"00001",
				"Random city",
				"Finland",
				others
			));

			if (appUserRepository.findByUsername("admin").isEmpty()) {
				appUserRepository.save(new AppUser(
					"admin",
					passwordEncoder.encode("admin"),
					"admin@admin.com",
					"ROLE_ADMIN"
				));
			}
			if (appUserRepository.findByUsername("user").isEmpty()) {
				appUserRepository.save(new AppUser(
					"user",
					passwordEncoder.encode("user"),
					"user@user.com",
					"ROLE_USER"
				));
			}
		};
	}

}

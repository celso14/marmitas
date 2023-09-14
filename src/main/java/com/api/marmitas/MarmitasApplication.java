package com.api.marmitas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.entities.Address;
import com.api.marmitas.entities.Costumer;
import com.api.marmitas.repositories.CostumerRepository;

@SpringBootApplication
@RestController
public class MarmitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarmitasApplication.class, args);
	}

	@GetMapping("/api/hello")
	public String sayHello() {
		return "Olá Mundo!";
	}

	@Bean
	CommandLineRunner initDatabase(CostumerRepository costumerRepository) {
		return args -> {
			costumerRepository.deleteAll();

			String[] firstNames = { "João", "Maria", "Pedro" };
			String[] lastNames = { "Silva", "Santos", "Oliveira" };
			String[] nickNames = { "Jo", "Mari", "Pedrinho" };
			String[] phoneNumbers = { "123-456-7890", "987-654-3210", "555-123-4567" };

			for (int i = 0; i < 3; i++) {

				Costumer c = new Costumer();
				c.setFirstName(firstNames[i]);
				c.setLastName(lastNames[i]);
				c.setNickName(nickNames[i]);
				c.setPhoneNumber(phoneNumbers[i]);

				Address a = new Address();
				a.setName("São Miguel");
				a.setAddressLocation("Rua");
				a.setNumber("573");
				a.setNeighborhood("Jurunas");
				a.setAddressType("Casa");
				a.setReference("Entre Apinagés e Tupinanbás, apt 1501");
				a.setCostumer(c);

				c.getAdresses().add(a);

				Address a1 = new Address();
				a1.setName("Perimetral");
				a1.setAddressLocation("Av.");
				a1.setNumber("1074");
				a1.setNeighborhood("Guamá");
				a1.setAddressType("Trabalho");
				a1.setReference("Espaço Inovação, Portão 5, 1º andar, Solved");
				a1.setCostumer(c);

				c.getAdresses().add(a1);

				costumerRepository.save(c);
			}
		};
	}

}

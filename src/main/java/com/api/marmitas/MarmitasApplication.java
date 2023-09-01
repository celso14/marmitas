package com.api.marmitas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.marmitas.entities.Costumer;
import com.api.marmitas.repositories.CostumerRepository;

@SpringBootApplication
@RestController
public class MarmitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarmitasApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Olá Mundo!";
	}

	@Bean
	CommandLineRunner initDatabase(CostumerRepository costumerRepository) {
		return args -> {
			costumerRepository.deleteAll();

			String[] firstNames = {"João", "Maria", "Pedro"};
			String[] lastNames = {"Silva", "Santos", "Oliveira"};
			String[] nickNames = {"Jo", "Mari", "Pedrinho"};
			String[] phoneNumbers = {"123-456-7890", "987-654-3210", "555-123-4567"};

			for(int i = 0; i<3; i++){

				Costumer c = new Costumer();
				c.setFirstName(firstNames[i]);
				c.setLastName(lastNames[i]);
				c.setNickName(nickNames[i]);
				c.setPhoneNumber(phoneNumbers[i]);

				costumerRepository.save(c);
			}
		};
	}	

}
package com.jimenahernando.entrega_1;

import com.jimenahernando.entrega_1.entities.Laptop;
import com.jimenahernando.entrega_1.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Entrega1Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Entrega1Application.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);

		Laptop macPro = new Laptop(null, "Apple", "MacBook Pro", LocalDate.of(2021, 12, 10));
		Laptop dynaBook = new Laptop(null, "Toshiba", "DynaBook", LocalDate.of(2020, 1, 1));

		repository.save(macPro);
		repository.save(dynaBook);

		System.out.println("Cantidad de libros en memoria: " + repository.count());
	}

}

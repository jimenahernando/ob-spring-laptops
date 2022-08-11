package com.jimenahernando.entrega_1.controller;

import com.jimenahernando.entrega_1.entities.Laptop;
import com.jimenahernando.entrega_1.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri(("http://localhost:" + port));
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        //pasar de array a arrayList
        List<Laptop> books = Arrays.asList(response.getBody());
        System.out.println(books.size());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops/5", Laptop[].class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //BLOQUE TEXTO LITERAL SE HACE CON """
        String json = """
            {
                "brand": "Apple",
                "model": "MacBook Air",
                "dateProduction": "2015-06-15"
            }
        """;
        //creo la request con el json que envio y las cabeceras
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        //metodo que sirve apra ejecutar la request
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Apple", result.getBrand());
    }
}
package nz.co.spark.springrestsecurity;

import nz.co.spark.springrestsecurity.model.Book;
import nz.co.spark.springrestsecurity.model.BookRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class StartBookApplication {

    public static void main(final String[] args) {
        SpringApplication.run(StartBookApplication.class);
    }

    @Bean
    CommandLineRunner initDatabase(final BookRepository repository) {
        return args -> {
            repository.saveAndFlush(new Book("A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41")));
            repository.saveAndFlush(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
            repository.saveAndFlush(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));
        };
    }
}

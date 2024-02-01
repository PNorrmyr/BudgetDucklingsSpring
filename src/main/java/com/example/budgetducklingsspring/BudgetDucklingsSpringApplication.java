package com.example.budgetducklingsspring;

import com.example.budgetducklingsspring.model.Category;
import com.example.budgetducklingsspring.model.Invoice;
import com.example.budgetducklingsspring.model.User;
import com.example.budgetducklingsspring.repository.InvoiceRepository;
import com.example.budgetducklingsspring.repository.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BudgetDucklingsSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetDucklingsSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner runLogin(LoginRepository loginRepository) {
        return args -> {
            loginRepository.save(new User(1l, "Phil", "123"));
            loginRepository.save(new User(2l, "bob", "abc"));
            loginRepository.save(new User(3l, "Yves", "abc"));
        };
    }

    @Bean
    public CommandLineRunner runInvoice(InvoiceRepository invoiceRepository){
    return args -> {
        invoiceRepository.save(new Invoice(1l, "Food", 100, "2024-01-21",
                "lunch", Category.FOOD, "Phil"));
        invoiceRepository.save(new Invoice(2l, "Taxi", 300, "2024-01-24",
                "Taxi to work", Category.TRAVEL_TO_FROM_WORK, "Phil"));
        invoiceRepository.save(new Invoice(3l, "Taxi", 500, "2024-02-24",
                "Taxi from work", Category.TRAVEL_TO_FROM_WORK, "Bob"));
         };
    }
}

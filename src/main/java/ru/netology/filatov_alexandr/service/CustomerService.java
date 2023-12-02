package ru.netology.filatov_alexandr.service;

import org.springframework.stereotype.Component;
import ru.netology.filatov_alexandr.domain.Customer;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class CustomerService {
    private final List<Customer> storage;

    public CustomerService(List<Customer> storage) {
        this.storage = storage;
    }

    public Customer getCustomer(int customerId) {
        return storage.get(customerId);
    }

    public List<Customer> getCustomers() { return storage; }

    public void addUser(int id, String name){
        storage.add(new Customer(id, name));
    }

    @PostConstruct
    public void init(){
        storage.add(new Customer(1, "Spring"));
        storage.add(new Customer(2, "Boot"));
    }
}
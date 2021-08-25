package com.example.ebean;

import com.example.ebean.model.Customer;
import io.ebean.Ebean;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Objects;

@RestController
public class Controller {

    @GetMapping("/api/1/costumer/")
    public Mono<ResponseEntity<Collection<Customer>>> findAllCostumers() {
        return getAllCustomers().map(customers -> new ResponseEntity<>(customers, HttpStatus.OK));
    }

    @PostMapping("/api/1/costumer/")
    public Mono<ResponseEntity<Customer>> addCustomer(@RequestBody Customer customer) {
        return saveCustomer(customer).map(customerResponse -> new ResponseEntity<>(customerResponse, HttpStatus.OK));
    }

    @PutMapping("/api/1/costumer/{identifier}")
    public Mono<ResponseEntity<Customer>> updateCustomer(@PathVariable String identifier, @RequestBody Customer customer) {
        return changeCustomer(identifier, customer).map(customerResponse -> new ResponseEntity<>(customerResponse, HttpStatus.OK));
    }

    private Mono<Collection<Customer>> getAllCustomers() {
        return Mono.just(Ebean.find(Customer.class).findList());
    }

    private Mono<Customer> findCustomerByIdentifier(Customer customer) {
        return Mono.just(Objects.requireNonNull(Ebean.getDefaultServer()
                                                     .find(Customer.class, customer.getId())));
    }

    private Mono<Customer> saveCustomer(Customer customer) {
        Ebean.getDefaultServer().save(customer);
        return findCustomerByIdentifier(customer);
    }

    private Mono<Customer> changeCustomer(String identifier, Customer customer) {

        Customer customerFound = Ebean.getDefaultServer()
                                      .find(Customer.class)
                                      .fetch("address")
                                      .where()
                                      .eq("identifier", identifier)
                                      .findOne();

        BeanUtils.copyProperties(customer, customerFound);

        return Mono.just(customerFound);
    }

    /*private Collection<Customer> steps() {

        Address a1 = new Address("5, Wide Street", null, "New York");
        Customer c1 = new Customer("John Wide", a1);

        EbeanServer server = Ebean.getDefaultServer();
        server.save(c1);

        //Ebean.delete(foundC1);

        Query<Customer> customerQuery = Ebean.find(Customer.class);

        return customerQuery.findList();
    }*/
}

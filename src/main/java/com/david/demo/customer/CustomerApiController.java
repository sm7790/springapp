package com.david.demo.customer;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.demo.logs.LogThis;

/**
 * API controller
 */
@RequestMapping("/api")
@RestController
@CrossOrigin // for test purposes allow FE deployed on localhost
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}" )
    public List<Customer> getCustomers(@PathVariable("lastName")String lastName) {

        return customerService.getByName(lastName);
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}/asc" )
    public List<Customer> getCustomersAsc(@PathVariable("lastName")String lastName) {

        return customerService.getByAgeAsc(lastName);
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}/desc" )
    public List<Customer> getCustomersDesc(@PathVariable("lastName")String lastName) {

        return customerService.getByAgeDesc(lastName);
    }

    @LogThis
    @GetMapping(value = "/group/{groupName}" )
    public List<Customer> getGroup(@PathVariable("groupName")String groupName) {

        return customerService.getGroup(groupName);
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}/delete" )
    public void deleteCustomer(@PathVariable("lastName")String lastName) {

        customerService.deleteByLastName(lastName);
    }

    @LogThis
    @GetMapping(value = "/customers" )
    public List<Customer> getAll() {

        return customerService.getAll();
    }

    @PostMapping(value = "/customer")
    public void createCustomer(@RequestBody CustomerTO customerTO) {
        customerService.addNew(CustomerMapper.INSTANCE.customerTOToCustomer(customerTO));
    }
}

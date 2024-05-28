package gr.aueb.cf.recipesapp.service;

import gr.aueb.cf.recipesapp.dto.CustomerDTO;
import gr.aueb.cf.recipesapp.model.Customer;
import gr.aueb.cf.recipesapp.model.Favorite;
import gr.aueb.cf.recipesapp.model.Post;

import gr.aueb.cf.recipesapp.repository.CustomerRepository;
import gr.aueb.cf.recipesapp.repository.FavoriteRepository;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, FavoriteRepository favoriteRepository) {
        this.customerRepository = customerRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional
    @Override
    public List<CustomerDTO> getAllCustomers() throws EntityNotFoundException {
        List<Customer> customers;
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customers =  customerRepository.findAll();
        if (customers.size() == 0) throw new EntityNotFoundException(Customer.class);

        for (Customer customer : customers) {
            CustomerDTO customerDTO;
            customerDTO = convertToCustomerDTO(customer);
            customerDTOS.add(customerDTO);
        }

        return customerDTOS;
    }

    @Transactional
    @Override
    public CustomerDTO getCustomerByEmail(String email) throws EntityNotFoundException {
        Customer customer;
        CustomerDTO customerDTO;
        customer = customerRepository.findByEmail(email);
        if (customer == null) throw new  EntityNotFoundException(Customer.class);

        customerDTO = convertToCustomerDTO(customer);
        return customerDTO;
    }

    @Transactional
    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer;
        customer = convertToCustomer(customerDTO);
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void updateCustomer(String email, CustomerDTO customerDTO) throws EntityNotFoundException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) throw new EntityNotFoundException(Customer.class);

        customer.setPassword(customerDTO.getPassword());
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void deleteCustomerByEmail(String email) throws EntityNotFoundException {
        Customer customer = customerRepository.findByEmail(email);

        if (customer == null) throw new EntityNotFoundException(Customer.class);


        favoriteRepository.deleteById_CustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
    }

    private static Customer convertToCustomer(CustomerDTO dto) {
        return new Customer(dto.getCustomerId(), dto.getFirstname(), dto.getLastname(), dto.getEmail(), dto.getPassword());
    }

    private static CustomerDTO convertToCustomerDTO(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getPassword());
    }
}

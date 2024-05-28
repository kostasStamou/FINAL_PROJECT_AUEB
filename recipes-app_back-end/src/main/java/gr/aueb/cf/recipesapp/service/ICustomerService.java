package gr.aueb.cf.recipesapp.service;

import gr.aueb.cf.recipesapp.dto.CustomerDTO;
import gr.aueb.cf.recipesapp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getAllCustomers() throws EntityNotFoundException;
    CustomerDTO getCustomerByEmail(String email) throws EntityNotFoundException;
    void addCustomer(CustomerDTO customerDTO);
    void updateCustomer(String email, CustomerDTO customerDTO) throws EntityNotFoundException;
    void deleteCustomerByEmail(String email) throws EntityNotFoundException;
}


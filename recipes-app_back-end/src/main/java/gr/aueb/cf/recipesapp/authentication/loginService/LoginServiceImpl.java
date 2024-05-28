package gr.aueb.cf.recipesapp.authentication.loginService;

import gr.aueb.cf.recipesapp.authentication.loginDto.LoginDTO;
import gr.aueb.cf.recipesapp.model.Customer;
import gr.aueb.cf.recipesapp.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {
    private final CustomerRepository customerRepository;

    @Autowired
    public LoginServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Transactional
    @Override
    public String checkForUserOrAdmin(LoginDTO loginDTO) {
        String role = "NONE";
        List<Customer> customers;
        customers = customerRepository.findAll();
        for (Customer customer : customers) {
            if ((customer.getEmail().equals(loginDTO.getEmail())) && (customer.getPassword().equals(loginDTO.getPassword()))){
                role = "USER";
                break;
            }
        }

        if ((loginDTO.getEmail().equals("admin@gmail.com")) && (loginDTO.getPassword().equals("admin"))){
            role = "ADMIN";
        }

        return role;
    }
}

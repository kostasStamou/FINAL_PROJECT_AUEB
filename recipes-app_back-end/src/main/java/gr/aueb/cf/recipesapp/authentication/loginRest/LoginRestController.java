package gr.aueb.cf.recipesapp.authentication.loginRest;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.aueb.cf.recipesapp.authentication.loginDto.LoginDTO;
import gr.aueb.cf.recipesapp.authentication.loginService.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    private final ILoginService loginService;

    @Autowired
    public LoginRestController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) throws JsonProcessingException {
        Object response = loginService.checkForUserOrAdmin(loginDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(Collections.singletonMap("ROLE", response));

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}

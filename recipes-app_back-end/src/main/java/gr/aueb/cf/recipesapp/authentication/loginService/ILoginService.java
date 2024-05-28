package gr.aueb.cf.recipesapp.authentication.loginService;

import gr.aueb.cf.recipesapp.authentication.loginDto.LoginDTO;

public interface ILoginService {
    String checkForUserOrAdmin(LoginDTO loginDTO);
}

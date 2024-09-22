package pe.edu.cibertec.patitas_backend_b.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LoginResponseDTO;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @PostMapping("/login")
    public LoginResponseDTO login (@RequestBody LoginRequestDTO loginRequestDTO){

        return new LoginResponseDTO("00","","Julio Chavez","Julio.chavez@gmail.com");

    }

}

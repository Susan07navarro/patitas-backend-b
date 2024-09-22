package pe.edu.cibertec.patitas_backend_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend_b.service.AutenticacionService;


@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;


    @PostMapping("/login")
    public LoginResponseDTO login (@RequestBody LoginRequestDTO loginRequestDTO){

        try {

            String[] datosUsuario = autenticacionService.validarUsuario(loginRequestDTO);
            if(datosUsuario == null){
                return new LoginResponseDTO("01","Error: Usuario no encontrado","","");
            }
            return new LoginResponseDTO("00","",datosUsuario[0],datosUsuario[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LoginResponseDTO("99","Error: Ocurrio un problema","","");
        }


    }

}

package pe.edu.cibertec.patitas_backend_b.service;

import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;

import java.io.IOException;

public interface AutenticacionService  {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;

}

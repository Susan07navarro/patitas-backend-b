package pe.edu.cibertec.patitas_backend_b.service;

import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LogoutRequestDTO;

import java.io.IOException;
import java.util.Date;

public interface AutenticacionService {

    String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException;

    boolean cerrarSesionUsuario(String nombreUsuario) throws IOException;

}

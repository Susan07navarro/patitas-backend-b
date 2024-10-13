package pe.edu.cibertec.patitas_backend_b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_backend_b.service.AutenticacionService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarUsuario(LoginRequestDTO loginRequestDTO) throws IOException {

        String[] datosUsuario = null;
        Resource resource = resourceLoader.getResource("classpath:usuarios.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {

            String linea;
            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");
                if (loginRequestDTO.tipoDocumento().equals(datos[0]) &&
                    loginRequestDTO.numeroDocumento().equals(datos[1]) &&
                    loginRequestDTO.password().equals(datos[2])) {

                    datosUsuario = new String[2];
                    datosUsuario[0] = datos[3]; // Recuperar nombre
                    datosUsuario[1] = datos[4]; // Recuperar email

                }

            }

        } catch (IOException e) {
            datosUsuario = null;
            throw new IOException(e);
        }

        return datosUsuario;
    }

    @Override
    public boolean cerrarSesionUsuario(String nombreUsuario) throws IOException {

        Date fechaLogout = null;
        Resource resource = resourceLoader.getResource("classpath:auditoria.txt");
        Path rutaArchivo = Paths.get(resource.getURI());

        LogoutRequestDTO logoutRequestDTO = buscarUsuarioPorNombre(nombreUsuario);

        try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.APPEND)) {

            // definir fecha
            fechaLogout = new Date();

            // preparar linea
            StringBuilder sb = new StringBuilder();
            sb.append(logoutRequestDTO.tipoDocumento());
            sb.append(";");
            sb.append(logoutRequestDTO.numeroDocumento());
            sb.append(";");
            sb.append(fechaLogout);
            // escribir linea
            bw.write(sb.toString());
            bw.newLine();
            return true;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private LogoutRequestDTO buscarUsuarioPorNombre (String nombreuUsuario) throws IOException {

        LogoutRequestDTO requestDTO = null;
        Resource resource = resourceLoader.getResource("classpath:usuarios.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (nombreuUsuario.equals(datos[3])){
                    requestDTO = new LogoutRequestDTO(datos[0], datos[1]);
                    return requestDTO;
                }
            }
        }catch (IOException e) {
            throw new IOException(e);
        }
        return null;
    }

}

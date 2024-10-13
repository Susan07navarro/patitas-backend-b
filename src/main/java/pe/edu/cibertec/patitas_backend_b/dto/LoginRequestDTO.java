package pe.edu.cibertec.patitas_backend_b.dto;

public record LoginRequestDTO(String tipoDocumento, String numeroDocumento, String password) {
}

package adm.tareas.adm_tareas.auth.dto;

public record RegisterRequest (
        String username,
        String email,
        String password
){}


package adm.tareas.adm_tareas.users.dto;

import adm.tareas.adm_tareas.users.entity.User;

public record UserDto (
        Long id,
        String username,
        String email,
        String role
){
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}

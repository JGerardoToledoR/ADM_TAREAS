package adm.tareas.adm_tareas.users.service;

import adm.tareas.adm_tareas.users.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUser(Long id);
}

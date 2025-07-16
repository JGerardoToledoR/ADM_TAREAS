package adm.tareas.adm_tareas.auth.service;

import adm.tareas.adm_tareas.auth.dto.AuthRequest;
import adm.tareas.adm_tareas.auth.dto.AuthResponse;
import adm.tareas.adm_tareas.auth.dto.RegisterRequest;
import adm.tareas.adm_tareas.auth.security.JwtService;
import adm.tareas.adm_tareas.users.entity.Role;
import adm.tareas.adm_tareas.users.entity.User;
import adm.tareas.adm_tareas.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = new User(
                request.username(),
                request.email(),
                passwordEncoder.encode(request.password()),
                Role.USER // Asignar rol por defecto
        );
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();
        var token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}

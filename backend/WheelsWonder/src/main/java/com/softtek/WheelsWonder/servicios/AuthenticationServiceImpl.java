package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.dao.request.SignInRequest;
import com.softtek.WheelsWonder.dao.request.SignUpRequest;
import com.softtek.WheelsWonder.dao.response.JwtAuthenticationResponse;
import com.softtek.WheelsWonder.modelo.Role;
import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.repositorios.IUsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final IUsuarioRepositorio userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = Usuario.builder()
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));
        var user = userRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}


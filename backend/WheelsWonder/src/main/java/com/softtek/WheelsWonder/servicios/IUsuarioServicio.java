package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUsuarioServicio extends ICRUD<Usuario,Integer>{
    UserDetailsService userDetailsService();
    List<Usuario> findByDni(String dni);
    Optional<Usuario> findByCorreo(String correo);
}

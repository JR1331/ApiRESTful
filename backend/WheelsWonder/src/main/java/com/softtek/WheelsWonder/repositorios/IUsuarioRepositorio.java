package com.softtek.WheelsWonder.repositorios;

import com.softtek.WheelsWonder.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepositorio extends IGenericoRepositorio<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByDni(String dni);
}

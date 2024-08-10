package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.repositorios.IGenericoRepositorio;
import com.softtek.WheelsWonder.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl extends CRUDImpl<Usuario,Integer> implements IUsuarioServicio{

    @Autowired
    private IUsuarioRepositorio repo;

    @Override
    protected IGenericoRepositorio<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)  {
                return repo.findByCorreo(username)
                        .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }

    @Override
    public List<Usuario> findByDni(String dni) {
        return repo.findByDni(dni);
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return repo.findByCorreo(correo);
    }
}

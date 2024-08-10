package com.softtek.WheelsWonder;

import com.softtek.WheelsWonder.modelo.Usuario;
import com.softtek.WheelsWonder.repositorios.IUsuarioRepositorio;
import com.softtek.WheelsWonder.servicios.UsuarioServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioTest {


    @Mock
    private IUsuarioRepositorio repo;

    @InjectMocks
    private UsuarioServicioImpl usuarioServicio;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setDni("12345678J");
        usuario.setNombre("Test");
        usuario.setApellido("User");
        usuario.setCorreo("test@example.com");
        usuario.setTelefono("123456789");
        usuario.setContrasena("password");
        usuario.setImagenURL("http://example.com/image.jpg");
    }

    @Test
    void testCrear() {
        when(repo.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioServicio.crear(usuario);

        assertEquals(usuario, resultado);
        verify(repo, times(1)).save(usuario);
    }

    @Test
    void testModificar() {
        when(repo.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioServicio.modificar(usuario);

        assertEquals(usuario, resultado);
        verify(repo, times(1)).save(usuario);
    }

    @Test
    void testEliminar() {
        doNothing().when(repo).deleteById(usuario.getId());

        usuarioServicio.eliminar(usuario.getId());

        verify(repo, times(1)).deleteById(usuario.getId());
    }

    @Test
    void testConsultaUno() {
        when(repo.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioServicio.consultaUno(usuario.getId());

        assertEquals(usuario, resultado);
        verify(repo, times(1)).findById(usuario.getId());
    }

    @Test
    void testConsultarTodos() {
        List<Usuario> usuarios = Arrays.asList(usuario, new Usuario());
        when(repo.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioServicio.consultarTodos();

        assertEquals(usuarios, resultado);
        verify(repo, times(1)).findAll();
    }
}
